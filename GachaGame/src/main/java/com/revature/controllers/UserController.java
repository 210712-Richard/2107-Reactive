package com.revature.controllers;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import com.revature.beans.HistoricalCat;
import com.revature.beans.User;
import com.revature.services.UserService;


@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	private static final Logger log = LogManager.getLogger(UserController.class);

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
	@PutMapping(value="{username}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> register(@RequestBody User u, @PathVariable("username") String name) {
		// check to see if that username is available
		if (userService.checkAvailability(name)) {
			// actually register the user
			User created = userService.register(name, u.getEmail(), u.getBirthday());
			return ResponseEntity.ok(created);
		} else {
			return ResponseEntity.status(409).contentType(MediaType.TEXT_HTML).body("<html><body><div>CONFLICT</div></body></html>");
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
	//app.put("/users/:username/lastCheckIn", userController::dailyCheckIn);
	@PutMapping("{username}/lastCheckIn")
	public ResponseEntity<Long> dailyCheckIn(@PathVariable("username") String name, WebSession session) {
		User loggedUser = (User) session.getAttribute("loggedUser");
		if(loggedUser == null) {
			return ResponseEntity.status(401).build();
		}
		if(!loggedUser.getUsername().equals(name)) {
			return ResponseEntity.status(403).build();
		}
		
		// actual implementation
		
		// check if already checked in today
		Boolean checkedIn = userService.hasCheckedIn(loggedUser);
		if (checkedIn == true) {
			return ResponseEntity.status(403).build();
		}
		
		// do check in
		User user = userService.login(name);
		userService.doCheckIn(user);
		return ResponseEntity.ok(loggedUser.getCurrency());
		
	}
	
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
	@PutMapping("{username}/inventory/{gachaId}")
	public ResponseEntity<GachaObject> level(
			@PathVariable("username") String name,
			WebSession session,
			@PathVariable("gachaId") String gachaId,
			@RequestBody HistoricalCat food){
		User loggedUser = (User) session.getAttribute("loggedUser");
		if (loggedUser == null) {
			return ResponseEntity.status(401).build();
		}
		if (!loggedUser.getUsername().equals(name)) {
			return ResponseEntity.status(403).build();
		}
		User user = userService.login(name);
		
		HistoricalCat cat = (HistoricalCat) user.getInventory().stream()
				.filter((gacha) -> gacha.getId().equals(UUID.fromString(gachaId)))
				.findFirst()
				.orElse(null);
		
		if (cat == null) {
			return ResponseEntity.status(404).build();
		}
		
		userService.levelGacha(user, cat, food);
		return ResponseEntity.ok(cat);
	}

	// ROOM 4
	// As a player, I can send my gacha on a mission
	//app.put("/users/:username/inventory/:gachaId/quest", userController::sendOnMission);
	
	@PutMapping("{username}/inventory/{gachaId}/quest")
	public ResponseEntity<Long> sendOnMission(@PathVariable("username") String name, @PathVariable("gachaId") String id, WebSession session){
		User loggedUser = (User) session.getAttribute("loggedUser");
		if(loggedUser == null) {
			return ResponseEntity.status(401).build();
		}
		if(!loggedUser.getUsername().equals(name)) {
			return ResponseEntity.status(403).build();
		}
		User user = userService.login(name);
		GachaObject go = user.getInventory().stream().filter(p-> p.getId().toString().equals(id)).findFirst().orElse(null);
		if(go == null) {
			return ResponseEntity.status(400).build();
		}
		ExecutorService pool = Executors.newCachedThreadPool();
		
		Future<Long> receivedCurrency =pool.submit(go.getAbility());

		Runnable r = ()->{
			try {
				user.setCurrency(user.getCurrency()+receivedCurrency.get());
			} catch (InterruptedException | ExecutionException e) {
				log.error(e.getMessage());
				for(StackTraceElement s: e.getStackTrace()) {
					log.error(s);
				}
			}
			userService.updateUser(user);
		};
		pool.execute(r);
		return ResponseEntity.status(200).build();
	}

}