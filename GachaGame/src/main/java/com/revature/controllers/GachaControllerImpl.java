package com.revature.controllers;

import java.io.InputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.beans.GachaObject;
import com.revature.beans.HistoricalCat;
import com.revature.beans.Rarity;
import com.revature.beans.User;
import com.revature.beans.UserType;
import com.revature.services.GachaService;
import com.revature.services.S3Service;

import io.javalin.http.Context;

@Component
public class GachaControllerImpl implements GachaController {
	@Autowired
	private GachaService gachaService;
	@Autowired
	private S3Service s3;
	private static Logger log = LogManager.getLogger(GachaControllerImpl.class);

	// Group 3 - branch: new-gacha
	public void addGacha(Context ctx) {

		User loggedUser = ctx.sessionAttribute("loggedUser");
		// Checking if logged in
		if (loggedUser == null) {
			ctx.status(401);
			return;
		}
		// Check that we're an admin
		if (!loggedUser.getType().equals(UserType.GAME_MASTER)) {
			ctx.status(403);
			return;
		}
		// Get the gacha out of the body of the request
		GachaObject ga = ctx.bodyAsClass(HistoricalCat.class);
		log.debug(ga);

		// Add the gacha to the database
		gachaService.createGacha(ga);
		// send back the newly saved gacha object with appropriate status code
		ctx.json(ga);
	}

	public void updateGacha(Context ctx) {
		User loggedUser = ctx.sessionAttribute("loggedUser");
		// Checking if logged in
		if (loggedUser == null) {
			ctx.status(401);
			return;
		}
		// Check that we're an admin
		if (!loggedUser.getType().equals(UserType.GAME_MASTER)) {
			ctx.status(403);
			return;
		}

		// Get the gacha out of the body of the request
		GachaObject ga = ctx.bodyAsClass(HistoricalCat.class);
		log.debug(ga);
		
		// update the gacha
		gachaService.updateGacha(ga);
		
		// send it back
		ctx.json(ga);

	}

	@Override
	public void uploadPicture(Context ctx) {
		User loggedUser = ctx.sessionAttribute("loggedUser");
		// Checking if logged in
		if (loggedUser == null) {
			ctx.status(401);
			return;
		}
		// Check that we're an admin
		if (!loggedUser.getType().equals(UserType.GAME_MASTER)) {
			ctx.status(403);
			return;
		}
		Rarity rarity = Rarity.valueOf(ctx.pathParam("gachaRarity"));
		if(rarity == null) {
			ctx.status(400);
			return;
		}
		String gachaName = ctx.pathParam("gachaName");
		GachaObject gacha = gachaService.getGacha(rarity, gachaName);
		if(gacha == null) {
			ctx.status(404);
			return;
		}
		// How are we going to get the filetype?
		String filetype = ctx.header("extension");
		if(filetype == null) {
			ctx.status(400); // bad request, expected the filetype
			return;
		}
		String key = gachaName+"."+filetype;
		s3.uploadToBucket(key, ctx.bodyAsBytes());
		gacha.setPictureUrl(key);
		gachaService.updateGacha(gacha);
		ctx.json(gacha);
	}

	@Override
	public void getPicture(Context ctx) {
		User loggedUser = ctx.sessionAttribute("loggedUser");
		// Checking if logged in
		if (loggedUser == null) {
			ctx.status(401);
			return;
		}
		Rarity rarity = Rarity.valueOf(ctx.pathParam("gachaRarity"));
		if(rarity == null) {
			ctx.status(400);
			return;
		}
		String gachaName = ctx.pathParam("gachaName");
		GachaObject gacha = gachaService.getGacha(rarity, gachaName);
		if(gacha == null) {
			ctx.status(404);
			return;
		}
		try {
			InputStream picture = s3.getObject(gacha.getPictureUrl());
			ctx.result(picture);
		} catch (Exception e) {
			ctx.status(500);
		}
	}
}
