package com.revature.beans;

/*
 * Abstract class is a class that cannot be instantiated and can have abstract methods.
 */
public abstract class Animal implements Breathable {
	// on an object or class field, final means that it cannot be reassigned.
	// static makes something into a class member rather than an object member.
	public static final String STRUCTURE = "cells";
	
	public String name;
	private Boolean adorable = false;
	private Boolean spooky;
	
	public Animal() {
		super();
		this.spooky = false;
	}
	
	public Animal(boolean spooky) {
		super();
		this.spooky = spooky;
	}
	
	public void speak() {
		System.out.println("Hello, I am "+ name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if("Milton".equals(name)) {
			return;
		}
		this.name = name;
	}
	// An abstract method is one you do not provide any implementation for.
	// abstract methods must be implemented by any concrete class that extends it.
	public abstract void walk();

	public Boolean getAdorable() {
		return adorable;
	}

	public void setAdorable(Boolean adorable) {
		this.adorable = adorable;
	}

	public Boolean getSpooky() {
		return spooky;
	}

	public void setSpooky(Boolean spooky) {
		this.spooky = spooky;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adorable == null) ? 0 : adorable.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((spooky == null) ? 0 : spooky.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal other = (Animal) obj;
		if (adorable == null) {
			if (other.adorable != null)
				return false;
		} else if (!adorable.equals(other.adorable))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (spooky == null) {
			if (other.spooky != null)
				return false;
		} else if (!spooky.equals(other.spooky))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Animal [name=" + name + ", adorable=" + adorable + ", spooky=" + spooky + "]";
	}
}
