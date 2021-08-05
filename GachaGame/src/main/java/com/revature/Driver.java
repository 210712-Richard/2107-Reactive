package com.revature;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.revature.controllers.GachaController;
import com.revature.controllers.GachaControllerImpl;
import com.revature.controllers.UserController;
import com.revature.controllers.UserControllerImpl;

import io.javalin.Javalin;
import io.javalin.plugin.json.JavalinJackson;

public class Driver {
	private static Logger log = LogManager.getLogger(Driver.class);
	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(AppConfig.class);
		ctx.refresh();
	}
}
