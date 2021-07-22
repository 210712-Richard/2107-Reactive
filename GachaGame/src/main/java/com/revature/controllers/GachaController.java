package com.revature.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.beans.GachaObject;
import com.revature.beans.User;
import com.revature.beans.UserType;
import com.revature.services.GachaService;

import io.javalin.http.Context;

public class GachaController {
	private GachaService gachaService = new GachaService();
	private static Logger log = LogManager.getLogger(UserController.class);
	
	
	// Group 3 - branch: new-gacha
	public GachaObject addGacha(Context ctx) {
		
		User loggedUser = ctx.sessionAttribute("loggedUser");
		String username = ctx.pathParam("username");
		//Checking if logged in
		if(loggedUser == null || !loggedUser.getUsername().equals(username)) {
			ctx.status(401);
			return null;
		}
		// TODO: Check that we're an admin
		if(!loggedUser.getType().equals(UserType.GAME_MASTER)) {
			ctx.status(403);
			return null;
		}
		// TODO: Get the gacha out of the body of the request
		GachaObject ga = ctx.bodyAsClass(GachaObject.class);
		log.debug(ga);
		
		// TODO: Add the gacha to the database
		gachaService.createGacha(ga);
		// TODO: send back the newly saved gacha object with appropriate status code
		return ga;
	}
}
