package com.revature.beans;

public class Chihuahua extends Dog {

	public Chihuahua() {
		super(false);
		super.setSpooky(true);
	}
	
	public void quiver() {
		System.out.println("quivers");
	}
	
	@Override
	public void speak() {
		System.out.println("squeals");
	}
	
	@Override
	public void setAdorable(Boolean adorable) {
		//this method intentionally left blank to preserve data integrity.
	}
}
