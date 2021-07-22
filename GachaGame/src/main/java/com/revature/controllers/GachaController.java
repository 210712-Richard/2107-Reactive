package com.revature.controllers;

import com.revature.services.GachaService;

import io.javalin.http.Context;

public class GachaController {
	private GachaService gachaService = new GachaService();

	// Group 3 - branch: new-gacha
	public void addGacha(Context ctx) {
		// TODO: Check that we're an admin

		// TODO: Get the gacha out of the body of the request

		// TODO: Add the gacha to the database

		// TODO: send back the newly saved gacha object with appropriate status code
	}
}
