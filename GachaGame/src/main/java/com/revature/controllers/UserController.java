package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;

import com.revature.beans.GachaObject;
import com.revature.beans.User;
import com.revature.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;

	// In Spring Web, controller methods return a value that will be sent to the
	// client.
	// We can either send a String, or a String representation of an object.
	// That would require an object mapper or we could ask Spring to do it.
	// @ResponseBody // Automatically turn the return value of the method into JSON.

	// As a user, I can log in.
	@PostMapping // ("/users")
	public ResponseEntity<User> login(@RequestBody User u, WebSession session) {
		System.out.println(u);
		User loggedUser = userService.login(u.getUsername());
		if (loggedUser == null) {
			return ResponseEntity.notFound().build();
		}
		session.getAttributes().put("loggedUser", u);
		return ResponseEntity.ok(loggedUser);
	}

	// As a user, I can log out.
	@DeleteMapping
	public ResponseEntity<Void> logout(WebSession session) {
		session.invalidate();
		return ResponseEntity.noContent().build();
	}

	// As a user, I can register for a player account.
	@PutMapping("{username}")
	public ResponseEntity<User> register(@RequestBody User u, @PathVariable("username") String name) {
		// check to see if that username is available
		if (userService.checkAvailability(name)) {
			// actually register the user
			User created = userService.register(name, u.getEmail(), u.getBirthday());
			return ResponseEntity.ok(created);
		} else {
			return ResponseEntity.status(409).build();
		}
	}

	// As a player, I can view my gachas
	@GetMapping("{username}/inventory")
	public ResponseEntity<List<GachaObject>> getInventory(@PathVariable("username") String name, WebSession session) {
		User loggedUser = (User) session.getAttribute("loggedUser");
		if (loggedUser == null) {
			return ResponseEntity.status(401).build();
		}
		if (!loggedUser.getUsername().equals(name)) {
			return ResponseEntity.status(403).build();
		}

		User user = userService.login(name);

		return ResponseEntity.ok(user.getInventory());
	}

	// As a player, I can summon a hero
	@PostMapping("{username}/inventory")
	public ResponseEntity<GachaObject> summon(@PathVariable("username") String name, WebSession session) {
		User loggedUser = (User) session.getAttribute("loggedUser");
		if (loggedUser == null) {
			return ResponseEntity.status(401).build();
		}
		if (!loggedUser.getUsername().equals(name)) {
			return ResponseEntity.status(403).build();
		}
		GachaObject g = userService.summon(userService.login(name));

		if (g == null) {
			// not enough moneys
			return ResponseEntity.status(402).build();
		}

		return ResponseEntity.ok(g);
	}

	// ROOM 1
	// As a player, I can obtain currency.
	// app.put("/users/:username/lastCheckIn", userController::dailyCheckIn);

	// ROOM 2
	// As a player, I can view my currency.
	// app.get("/users/:username/currency", userController::getCurrency);
	@GetMapping("{username}/currency")
	public ResponseEntity<Long> getCurrency(@PathVariable("username") String name, WebSession session) {
		User loggedUser = (User) session.getAttribute("loggedUser");
		if (loggedUser == null) {
			return ResponseEntity.status(401).build();
		}
		if (!loggedUser.getUsername().equals(name)) {
			return ResponseEntity.status(403).build();
		}
		User current = userService.login(name);
		return ResponseEntity.ok(current.getCurrency());
	}

	// ROOM 3
	// As a player, I can level up my gacha
	// app.put("/users/:username/inventory/:gachaId", userController::level);

	// ROOM 4
	// As a player, I can send my gacha on a mission
	// app.put("/users/:username/inventory/:gachaId/quest",
	// userController::sendOnMission);

}