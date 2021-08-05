package com.revature.core;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.revature.beans.Ball;
import com.revature.beans.Player;

public class Beans {
	public static void main(String[] args) {
		//tradition();
		//spring();
		scopes();
	}

	private static void scopes() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(AppConfig.class);
		ctx.refresh();
		Player p1 = ctx.getBean(Player.class);
		Player p2 = ctx.getBean(Player.class);
		System.out.println("p1==p2: "+(p1 == p2));
		
		p1.setName("Travis");
		p1.getBall().setType("Basketball");
		p1.getBall().setInflated(false);

		System.out.println("Player 1: " +p1);
		System.out.println("Player 2: " +p2);
		Player p3 = ctx.getBean(Player.class);
		System.out.println("Player 3: " +p3);
		System.out.println("P1 ball == p2 ball: "+(p1.getBall()==p2.getBall()));
	}

	private static void spring() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(AppConfig.class);
		ctx.refresh();
		Player p = ctx.getBean(Player.class);
		System.out.println(p);
	}

	private static void tradition() {
		Player p = new Player();
		System.out.println(p);
		Ball b = new Ball();
		p.setBall(b);
		System.out.println(p);
	}

}
