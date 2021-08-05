package com.revature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.revature.controllers.GachaController;
import com.revature.controllers.UserController;

import io.javalin.Javalin;
import io.javalin.plugin.json.JavalinJackson;

@Configuration
public class JavalinConfig {
	@Autowired
	private GachaController gachaController;
	@Autowired
	private UserController uc;
	@Bean
	public Javalin javalin() {

		// Set up Jackson to serialize LocalDates and LocalDateTimes
		ObjectMapper jackson = new ObjectMapper();
		jackson.registerModule(new JavaTimeModule());
		jackson.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		JavalinJackson.configure(jackson);

		// Starts the Javalin Framework
		Javalin app = Javalin.create().start(8080);

		// Javalin has created a web server for us and we have
		// to tell Javalin how to handle the requests it receives.

		// app.METHOD("URN", CALLBACK_FUNCTION);
		// The Javalin CALLBACK_FUNCTION takes an argument ctx which
		// represents the request and the response to the request.
		// ctx.body() - The body of the request
		// ctx.html() - Sends html as the response
		// ctx.status() - changes the status of the response
		app.get("/", (ctx) -> ctx.html("Hello World"));

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

		// As a player, I can send my gacha on a mission
		app.put("/users/:username/inventory/:gachaId/quest", uc::sendOnMission);

		// As a player, I can view my gachas
		app.get("/users/:username/inventory", uc::viewGachas);

		// As an admin, I can add an Gacha object
		app.post("/gachas", gachaController::addGacha);

		// As an admin, I can update a Gacha in the pool
		app.put("/gachas/:gachaRarity/:gachaName", gachaController::updateGacha);

		// As an admin, I can upload a picture for a Gacha
		app.put("/gachas/:gachaRarity/:gachaName/pictureUrl", gachaController::uploadPicture);

		// As a user, I can download a picture for a Gacha
		app.get("/gachas/:gachaRarity/:gachaName/pictureUrl", gachaController::getPicture);
		return app;

	}
}
