package com.revature.controllers;

import java.io.InputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebSession;

import com.revature.beans.HistoricalCat;
import com.revature.beans.Rarity;
import com.revature.beans.User;
import com.revature.beans.UserType;
import com.revature.services.S3Service;
import com.revature.services.UserService;
import com.revature.services.reactive.ReactiveGachaService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/gachas")
@CrossOrigin
public class GachaController {
	private static final Logger log = LogManager.getLogger(GachaController.class);

	@Autowired
	private ReactiveGachaService gachaService;

	@Autowired
	private UserService userService;

	@Autowired
	private S3Service s3Service;

	// As an admin, I can add an Gacha object
	@PostMapping
	public Mono<ResponseEntity<HistoricalCat>> createGacha(@RequestBody HistoricalCat gacha, WebSession session) {
		// check for admin powers
		User loggedUser = (User) session.getAttribute("loggedUser");
		if (loggedUser == null || !UserType.GAME_MASTER.equals(loggedUser.getType())) {
			return Mono.just(ResponseEntity.status(403).build());
		}
		return gachaService.createGacha(gacha).map((g) -> {
			if (g == null) {
				return ResponseEntity.status(409).build();
			} else {
				return ResponseEntity.ok(g);
			}
		});
	}

	// As an admin, I can update a Gacha in the pool
	@PutMapping("/{rarity}/{name}")
	public ResponseEntity<Mono<HistoricalCat>> updateGacha(@RequestBody HistoricalCat gacha,
			@PathVariable("rarity") String rarity, @PathVariable("name") String name, WebSession session) {
		// check for admin powers
		User loggedUser = (User) session.getAttribute("loggedUser");
		loggedUser = userService.login(loggedUser.getUsername());
		if (loggedUser == null || !UserType.GAME_MASTER.equals(loggedUser.getType())) {
			return ResponseEntity.status(403).build();
		}

		// check to see if gacha exists
		Mono<HistoricalCat> retrieved = gachaService.getGacha(Rarity.valueOf(rarity), name);
		if (retrieved == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(gachaService.updateGacha(gacha));
	}

	// As an admin, I can update a Gacha in the pool
	@GetMapping("/{rarity}/{name}")
	public ResponseEntity<Mono<HistoricalCat>> getGacha(@RequestBody HistoricalCat gacha,
			@PathVariable("rarity") String rarity, @PathVariable("name") String name, WebSession session) {

		return ResponseEntity.ok(gachaService.getGacha(Rarity.valueOf(rarity), name));
	}

	// As an admin, I can upload a picture for a Gacha
	@PutMapping("{rarity}/{name}/pictureUrl")
	public Mono<ResponseEntity<Object>> uploadPicture(@PathVariable("rarity") String rarity,
			@PathVariable("name") String name, WebSession session, ServerWebExchange exchange) {

		// check for admin powers
		User loggedUser = (User) session.getAttribute("loggedUser");
		loggedUser = userService.login(loggedUser.getUsername());
		log.debug(loggedUser);
		if (loggedUser == null || !UserType.GAME_MASTER.equals(loggedUser.getType())) {
			return Mono.just(ResponseEntity.status(403).build());
		}
		String fileExtension = exchange.getRequest().getHeaders().getFirst("extension");
		if (fileExtension == null) {
			return Mono.just(ResponseEntity.badRequest().build());
		}

		// REACTIVE
		String key = name + "." + fileExtension;

		// check to see if gacha exists
		return gachaService.getGacha(Rarity.valueOf(rarity), name).single().map((cat) -> {
			exchange.getRequest().getBody().subscribe((data) -> {
				s3Service.uploadToBucket(key, data.asByteBuffer().array());
			});

			cat.setPictureUrl(key);

			return gachaService.updateGacha(cat); // this will not fire unless we subscribe to it
		}).map(cat -> {
			return ResponseEntity.noContent().build();
		}).onErrorResume(e -> Mono.just(ResponseEntity.notFound().build()));
	}

	// As a user, I can download a picture for a Gacha
	@GetMapping("{rarity}/{name}/pictureUrl")
	public Mono<ResponseEntity<InputStream>> downloadPicture(@PathVariable("rarity") String rarity,
			@PathVariable("name") String name) {
		// check to see if gacha exists
		return gachaService.getGacha(Rarity.valueOf(rarity), name).single().map(retrieved -> {
			return ResponseEntity.ok(s3Service.getObject(retrieved.getPictureUrl()));
		}).onErrorResume(e -> Mono.just(ResponseEntity.notFound().build()));
	}

	@GetMapping(produces = MediaType.APPLICATION_NDJSON_VALUE)
	public ResponseEntity<Flux<HistoricalCat>> getGachase() {
		return ResponseEntity.ok(gachaService.getGachas()/* .delayElements(Duration.ofSeconds(1)) */);
	}

}