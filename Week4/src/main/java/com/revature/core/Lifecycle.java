package com.revature.core;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.revature.beans.Bean;

public class Lifecycle {
	public static void main(String[] args) {
		// A sequence of steps that all Spring Beans undergo
		// Instantiation - Constructor Injection
		// Setter Injection
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(AppConfig.class);
		ctx.refresh();
		Bean b = ctx.getBean(Bean.class);
		System.out.println("Phase Four: Actually using it");
		System.out.println("In Use: "+b);
		ctx.close();
	}
}
