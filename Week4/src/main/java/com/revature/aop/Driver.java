package com.revature.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Driver {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(AppConfig.class);
		ctx.refresh();
		
		Digimon d = ctx.getBean(Digimon.class);
		
		System.out.println(d);
		
		d.speak("Hi I am "+d.getName());
		
		System.out.println(d.eat("pie"));
		
		try {
			d.logicPuzzle("How much wood could a wood chuck chuck if the wood chuck digivolved to ultimate?");
		} catch (Exception e) {
			System.out.println("An Exception!");
		}
		
		Digimon d2 = d.digivolve("Greymon");
		d2.count();
		System.out.println(d2);
		
		d.speak("Hi I am "+d2.getName());
		d2.eat("whatever");
		
	}

}
