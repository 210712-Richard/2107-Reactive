package com.revature.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.revature.beans.Ball;
import com.revature.beans.Player;

public class Beans {
	public static void main(String[] args) {
		//tradition();
		spring();
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
