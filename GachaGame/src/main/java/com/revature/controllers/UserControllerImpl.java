package com.revature.controllers;

import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.beans.GachaObject;
import com.revature.beans.HistoricalCat;
import com.revature.beans.User;
import com.revature.services.UserService;

import io.javalin.http.Context;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@Component
public class UserControllerImpl implements UserController {
	private static Logger log = LogManager.getLogger(UserControllerImpl.class);
	@Autowired
	private UserService us;
	
	@Override
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
	
	@Override
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
	
	@Override
	public void logout(Context ctx) {
		ctx.req.getSession().invalidate();
		ctx.status(204);
	}
	
	@Override
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
	
	@Override
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
	
	@Override
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
	@Override
	public void level(Context ctx) {
		// Level up the gacha specified in the path parameter
		User loggedUser = ctx.sessionAttribute("loggedUser");
		String username = ctx.pathParam("username");
		if(loggedUser == null || !loggedUser.getUsername().equals(username)) {
			ctx.status(403);
			return;
		}
		UUID predatorId = UUID.fromString(ctx.pathParam("gachaId"));
		GachaObject predator = loggedUser.getInventory()
			.stream()
			.filter((gacha)->gacha.getId().equals(predatorId))
			.findFirst()
			.orElse(null);
		if(predator == null) {
			ctx.status(404); // Couldn't find the predator
			return;
		}
		GachaObject food = ctx.bodyAsClass(HistoricalCat.class);
		
		us.levelGacha(loggedUser, predator, food);
		
		ctx.json(predator);
	}
	
	// Group 2 - branch: view-gacha
	@Override
	public void viewGachas(Context ctx) {
		
		
		//Check that the user is logged in
		User loggedUser = ctx.sessionAttribute("loggedUser");
		String username = ctx.pathParam("username");
		if(loggedUser == null || !loggedUser.getUsername().equals(username)) {
			ctx.status(403);
			return;
		}
		
		// send back the loggedin User's inventory.
		ctx.json(loggedUser.getInventory());
	}

	@Override
	public void sendOnMission(Context ctx) {
		User loggedUser = ctx.sessionAttribute("loggedUser");
		String username = ctx.pathParam("username");
		if(loggedUser == null || !loggedUser.getUsername().equals(username)) {
			ctx.status(403);
			return;
		}
		GachaObject questant = loggedUser
				.getInventory()
				.stream()
				.filter((g)->g.getId().equals(UUID.fromString(ctx.pathParam("gachaId"))))
				.findFirst()
				.orElse(null);
		if(questant == null) {
			ctx.status(400);
			return;
		}
		Future<Long> receivedCurrency = Executors.newCachedThreadPool().submit(questant.getAbility());
		Observable.fromFuture(receivedCurrency).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe((currency)->{
			loggedUser.setCurrency(loggedUser.getCurrency()+currency);
			us.updateUser(loggedUser);
		});
		
		ctx.status(200);
		return;
	}
}
