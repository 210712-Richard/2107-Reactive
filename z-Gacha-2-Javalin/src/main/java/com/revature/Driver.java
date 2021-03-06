package com.revature;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.revature.controllers.GachaController;
import com.revature.controllers.GachaControllerImpl;
import com.revature.controllers.UserController;
import com.revature.controllers.UserControllerImpl;
import com.revature.factory.BeanFactory;
import com.revature.util.CassandraUtil;

import io.javalin.Javalin;
import io.javalin.plugin.json.JavalinJackson;

public class Driver {
	public static void main(String[] args) {
		//dbtest(); // create a table
		javalin();
	}
	
	private static void dbtest() {
		StringBuilder sb = new StringBuilder("DROP TABLE IF EXISTS User;");
		CassandraUtil.getInstance().getSession().execute(sb.toString());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		sb = new StringBuilder("CREATE TABLE IF NOT EXISTS User (")
				.append("username text PRIMARY KEY, type text, id int, currency bigint, ")
				.append("birthday date, lastCheckIn date, email text, inventory list<int> );");
		CassandraUtil.getInstance().getSession().execute(sb.toString());
		System.exit(0);
	}

	public static void javalin() {
		
		// Set up Jackson to serialize LocalDates and LocalDateTimes
		ObjectMapper jackson = new ObjectMapper();
		jackson.registerModule(new JavaTimeModule());
		jackson.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		JavalinJackson.configure(jackson);
		
		// Starts the Javalin Framework
		Javalin app = Javalin.create().start(8080);
		
		UserController uc = (UserController) BeanFactory.getFactory().get(UserController.class, UserControllerImpl.class);
		GachaController gachaController = (GachaController) BeanFactory.getFactory().get(GachaController.class, GachaControllerImpl.class);
		
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
		app.get("/users/:username/inventory", uc::viewGachas);
		
		//As an admin, I van add an Gacha object
		app.post("/users/:username/", gachaController::addGacha);
	}
}
