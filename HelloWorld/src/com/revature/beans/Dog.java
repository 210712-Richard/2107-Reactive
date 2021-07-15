package com.revature.beans;

public class Dog extends Animal{

	/* Overriding:
	 * When a child method has the same signature as a method in the parent entity, it overrides or replaces
	 * that functionality in the created object. You *cannot* call the parent method once it has been overridden.
	 */
	public Dog() {
		this(true);
	}
	
	protected Dog(Boolean adorable) {
		super();
		super.setAdorable(adorable);
	}
	
	@Override
	public void speak() {
		System.out.println("BARK");
	}
	
	@Override
	public void setAdorable(Boolean adorable) {
		super.setAdorable(true);
	}

	@Override
	public void walk() {
		System.out.println("Going for walkies!");
	}

	@Override
	public void breathes() {
		System.out.println("Pants");
	}
}
