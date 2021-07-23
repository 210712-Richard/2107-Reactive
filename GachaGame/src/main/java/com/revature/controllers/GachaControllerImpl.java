package com.revature.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.beans.GachaObject;
import com.revature.beans.HistoricalCat;
import com.revature.beans.User;
import com.revature.beans.UserType;
import com.revature.factory.BeanFactory;
import com.revature.factory.Log;
import com.revature.services.GachaService;
import com.revature.services.GachaServiceImpl;

import io.javalin.http.Context;

@Log
public class GachaControllerImpl implements GachaController {
	private GachaService gachaService = (GachaService) BeanFactory.getFactory().get(GachaService.class, GachaServiceImpl.class);
	private static Logger log = LogManager.getLogger(GachaControllerImpl.class);
	
	
	// Group 3 - branch: new-gacha
	public void addGacha(Context ctx) {
		
		User loggedUser = ctx.sessionAttribute("loggedUser");
		String username = ctx.pathParam("username");
		//Checking if logged in
		if(loggedUser == null || !loggedUser.getUsername().equals(username)) {
			ctx.status(401);
			return;
		}
		// TODO: Check that we're an admin
		if(!loggedUser.getType().equals(UserType.GAME_MASTER)) {
			ctx.status(403);
			return;
		}
		// TODO: Get the gacha out of the body of the request
		GachaObject ga = ctx.bodyAsClass(HistoricalCat.class);
		log.debug(ga);
		
		// TODO: Add the gacha to the database
		gachaService.createGacha(ga);
		// TODO: send back the newly saved gacha object with appropriate status code
		ctx.json(ga);
	}
}
