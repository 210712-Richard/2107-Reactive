package com.revature.test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
	public static void main(String[] args) {
		List<Animal> animals = new ArrayList<>();
		animals.add(new Bear());
		animals.add(new Dog());
		
		//((Bear)animals.get(1)).eat();
		animals.forEach((a)-> {
			System.out.println(a.getClass());
			switch(a.getClass().getSimpleName()) {
			case "Dog": ((Dog)a).wagTail(); break;
			case "Bear": ((Bear)a).eat(); break;
			default: System.out.println(a.getClass().getSimpleName());
			}
		});
		System.out.println();
		
		animals.stream()
			.filter(  (a) -> a.getClass().getSimpleName().equals("Dog")  )
			.collect(Collectors.toList())
			.forEach((a)->{
				System.out.println(a);
			});
	}
}

interface Animal {
	
}

class Bear implements Animal {
	public void eat() {
		System.out.println("I am bear.");
	}
}

class Dog implements Animal {
	public void wagTail() {
		System.out.println("I am dog.");
	}
}