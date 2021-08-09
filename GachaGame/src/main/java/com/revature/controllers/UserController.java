package com.revature.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.User;
import com.revature.services.UserService;

@RestController
public class UserController {
	//@Autowired
	private UserService userService;
	
	// In Spring Web, controller methods return a value that will be sent to the client.
	// We can either send a String, or a String representation of an object.
	// That would require an object mapper or we could ask Spring to do it.
	//@ResponseBody // Automatically turn the return value of the method into JSON.
	@PostMapping("/users")
	public User login(@RequestBody User u) {
		System.out.println(u);
		return null;
	}
//
//	void getCurrency(Context ctx);
//
//	void logout(Context ctx);
//
//	void dailyCheckIn(Context ctx);
//
//	void register(Context ctx);
//
//	void summon(Context ctx);
//
//	// Group 1 - branch: level-up
//	void level(Context ctx);
//
//	// Group 2 - branch: view-gacha
//	void viewGachas(Context ctx);
//	
//	void sendOnMission(Context ctx);
}