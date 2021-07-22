package com.revature;

import com.revature.controllers.UserController;

import io.javalin.Javalin;

public class Driver {
	public static void main(String[] args) {
		// Starts the Javalin Framework
		Javalin app = Javalin.create().start(8080);
		
		UserController uc = new UserController();
		
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
		app.post("/users", uc::login);
		
		app.get("/users/:username/currency", uc::getCurrency);
	}
}
