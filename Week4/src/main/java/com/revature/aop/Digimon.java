package com.revature.aop;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Digimon implements ApplicationContextAware {
	private String name = "Agumon";
	private ApplicationContext ac;

	public Digimon() {
		super();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void speak(String text) {
		System.out.println(text);
	}
	public int count() {
		return 2;
	}
	
	public String eat(String food) {
		System.out.println("I ate "+food);
		return "Waste";
	}
	
	public Digimon digivolve(String name) {
		Digimon next = ac.getBean(Digimon.class);
		next.setName(name);
		return next;
	}
	
	public void logicPuzzle(String puzzle) throws Exception {
		System.out.println(puzzle);
		throw new Exception("Very confused");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.ac = applicationContext;
	}

	@Override
	public String toString() {
		return "Digimon [name=" + name + "]";
	}
}
