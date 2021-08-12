package com.revature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.revature.services.AsynchUserService;
import com.revature.services.SynchUserService;

@SpringBootApplication
public class Driver {
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(Driver.class); // we don't want to start a web server
		application.setWebApplicationType(WebApplicationType.NONE);
		
		userCheckIn(application, args);
	}

	private static void userCheckIn(SpringApplication application, String[] args) {
		ApplicationContext ctx = application.run(args);
		SynchUserService us = ctx.getBean(SynchUserService.class);
		AsynchUserService as = ctx.getBean(AsynchUserService.class);
		//us.userCheckIn();
		//us.printAllGachas();
		as.printAllGachas();
	}
}
