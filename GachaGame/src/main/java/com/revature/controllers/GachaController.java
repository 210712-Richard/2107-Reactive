package com.revature.controllers;

import io.javalin.http.Context;

public interface GachaController {
	void addGacha(Context ctx);
	void updateGacha(Context ctx);
	void uploadPicture(Context ctx);
	void getPicture(Context ctx);
}