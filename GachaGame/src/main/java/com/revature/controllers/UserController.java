package com.revature.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.beans.GachaObject;
import com.revature.beans.User;
import com.revature.services.UserService;

import io.javalin.http.Context;

public class UserController {
	private static Logger log = LogManager.getLogger(UserController.class);
	private UserService us = new UserService();
	
	public void login(Context ctx) {
		log.trace("Login method called");
		log.debug(ctx.body());
		// Try to use a JSON Marshaller to create an object of this type.
		// Javalin does not come with a JSON Marshaller but prefers Jackson. You could also use GSON
		User u = ctx.bodyAsClass(User.class);
		log.debug(u);
		
		// Use the request data to obtain the data requested
		u = us.login(u.getUsername());
		log.debug(u);
		
		// Create a session if the login was successful
		if(u != null) {
			// Save the user object as loggedUser in the session
			ctx.sessionAttribute("loggedUser", u);
			
			// Try to use the JSON Marshaller to send a JSON string of this object back to the client
			ctx.json(u);
			return;
		}
		
		// Send a 401 is the login was not successful
		ctx.status(401);
	}
	
	public void getCurrency(Context ctx) {
		String username = ctx.pathParam("username");
		User loggedUser = (User) ctx.sessionAttribute("loggedUser");
		// if we aren't logged in or our username is different than the logged in username
		if(loggedUser == null || !loggedUser.getUsername().equals(username)) {
			ctx.status(403);
			return;
		}
		// otherwise we're golden
		ctx.json(loggedUser.getCurrency());
	}
	
	public void logout(Context ctx) {
		ctx.req.getSession().invalidate();
		ctx.status(204);
	}
	
	public void dailyCheckIn(Context ctx) {
		// if we aren't logged in, how can we check in?
		User loggedUser = ctx.sessionAttribute("loggedUser");
		log.trace("daily check in to "+loggedUser);
		String username = ctx.pathParam("username");
		if(loggedUser == null || !loggedUser.getUsername().equals(username)) {
			ctx.status(403);
			return;
		}
		if(us.hasCheckedIn(loggedUser)) {
			// if we have already checked in, we can't check in again
			ctx.status(429);
			ctx.html("Already checked in today.");
			// if we don't return here, then the 204 below will override the 429 above.
			return;
		}
		us.doCheckIn(loggedUser);
		ctx.status(204);
	}
	
	public void register(Context ctx) {
		User u = ctx.bodyAsClass(User.class);

		if(us.checkAvailability(u.getUsername())) {
			User newUser = us.register(u.getUsername(), u.getEmail(), u.getBirthday());
			ctx.status(201);
			ctx.json(newUser);
		} else {
			ctx.status(409);
			ctx.html("Username already taken.");
		}
		
	}
	
	public void summon(Context ctx) {
		// if we aren't logged in, how can we summon?
		User loggedUser = ctx.sessionAttribute("loggedUser");
		String username = ctx.pathParam("username");
		if(loggedUser == null || !loggedUser.getUsername().equals(username)) {
			ctx.status(403);
			return;
		}
		GachaObject summoned = us.summon(loggedUser);
		if(summoned == null) {
			ctx.status(402);
		} else {
			ctx.json(summoned);
		}
		
	}
	
	// Group 1 - branch: level-up
	public void level(Context ctx) {
		// TODO: Level up the gacha specified in the path parameter
		
		// TODO: Remove the gacha specified in the body of the request from the inventory
		
		// TODO: Send back the newly leveled up gacha
	}
	
	// Group 2 - branch: view-gacha
	public void viewGachas(Context ctx) {
		// TODO: send back the loggedin User's inventory.
	}
}
