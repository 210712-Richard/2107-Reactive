package com.revature.controllers;

import io.javalin.http.Context;

public interface UserController {

	void login(Context ctx);

	void getCurrency(Context ctx);

	void logout(Context ctx);

	void dailyCheckIn(Context ctx);

	void register(Context ctx);

	void summon(Context ctx);

	// Group 1 - branch: level-up
	void level(Context ctx);

	// Group 2 - branch: view-gacha
	void viewGachas(Context ctx);

}