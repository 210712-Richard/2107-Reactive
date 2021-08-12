package com.revature.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.revature.beans.User;

import reactor.core.publisher.Flux;


@Service
public class SynchUserService {
	/*
	 * RestTemplate - An entity in Spring that is meant to send http requests to a REST API
	 * and handle responses from said API.
	 */	
	public void userCheckIn() {
		RestTemplate restTemplate = new RestTemplate();
		
		// log into the app
		User u = new User();
		u.setUsername("Michael");
		ResponseEntity<User> userResponse = restTemplate.postForEntity("http://localhost:8080/users", u, User.class);
		User loggedInUser = userResponse.getBody();
		System.out.println(loggedInUser);
		System.out.println(userResponse.getHeaders());
		System.out.println(userResponse.getHeaders().keySet());
		String SESSION = userResponse.getHeaders().getFirst("Set-Cookie").split(";")[0];
		System.out.println(SESSION);
		// check our currency
		
		// create the headers
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.COOKIE, SESSION);
		
		// create the request
		HttpEntity<Void> getRequest = new HttpEntity<>(null, headers);
		
		// send the request
		ResponseEntity<Long> currencyResponse = restTemplate.exchange(
				"http://localhost:8080/users/"+loggedInUser.getUsername()+"/currency",
				HttpMethod.GET,
				getRequest,
				Long.class);
		System.out.println("Our currency is currently: "+currencyResponse.getBody());
		
		// daily check-in
		
		// create the request
		HttpEntity<Void> putRequest = new HttpEntity<>(null, headers);
		currencyResponse = restTemplate.exchange(
				"http://localhost:8080/users/"+loggedInUser.getUsername()+"/lastCheckIn",
				HttpMethod.PUT,
				putRequest,
				Long.class);

		System.out.println("Our currency is currently: "+currencyResponse.getBody());
		
		// check our currency
		currencyResponse = restTemplate.exchange(
				"http://localhost:8080/users/"+loggedInUser.getUsername()+"/currency",
				HttpMethod.GET,
				getRequest,
				Long.class);
		System.out.println("Our currency is currently: "+currencyResponse.getBody());
	}

	public void printAllGachas() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Flux> catResponse = restTemplate.getForEntity("http://localhost:8080/gachas", Flux.class);
		catResponse.getBody().subscribe((item) -> {
			System.out.println(item);
		});
	}
}
