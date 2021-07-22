package com.revature;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.revature.controllers.GachaController;
import com.revature.controllers.UserController;

import io.javalin.Javalin;
import io.javalin.plugin.json.JavalinJackson;

public class Driver {
	public static void main(String[] args) {
		// Set up Jackson to serialize LocalDates and LocalDateTimes
		ObjectMapper jackson = new ObjectMapper();
		jackson.registerModule(new JavaTimeModule());
		jackson.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		JavalinJackson.configure(jackson);
		
		// Starts the Javalin Framework
		Javalin app = Javalin.create().start(8080);
		
		UserController uc = new UserController();
		GachaController gachaController = new GachaController();
		
		// Javalin has created a web server for us and we have
		// to tell Javalin how to handle the requests it receives.
		
		// app.METHOD("URN", CALLBACK_FUNCTION);
		// The Javalin CALLBACK_FUNCTION takes an argument ctx which 
		// represents the request and the response to the request.
		// ctx.body() - The body of the request
		// ctx.html() - Sends html as the response
		// ctx.status() - changes the status of the response
		app.get("/", (ctx)->ctx.html("Hello World"));
		
		// object::method <- Reference to a method as a function we can pass to a method
		
		// As a user, I can log in.
		app.post("/users", uc::login);
		// As a user, I can register for a player account.
		app.put("/users/:username", uc::register);
		// As a user, I can log out.
		app.delete("/users", uc::logout);
		// As a player, I can obtain currency.
		app.put("/users/:username/lastCheckIn", uc::dailyCheckIn);
		// As a player, I can view my currency.
		app.get("/users/:username/currency", uc::getCurrency);
		
		// As a player, I can summon a hero
		app.post("/users/:username/inventory", uc::summon);
		
		// As a player, I can level up my gacha
		app.put("/users/:username/inventory/:gachaId", uc::level);
		
		// As a player, I can view my gachas
		app.get("/users/:username/inventory", gachaController::viewGachas);
		
		//As an admin, I van add an Gacha object
		//app.post("/users/:username/")
	}
}
