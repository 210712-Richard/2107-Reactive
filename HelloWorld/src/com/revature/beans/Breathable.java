package com.revature.beans;

public interface Breathable {
	/*
	 * Interface is an abstract entity that defines a contract that implementing classes must fulfill.
	 * Interfaces are not classes and cannot be instantiated (except in Java 8 under special circumstances)
	 * Interfaces cannot have non-public members (except in Java 9 and above which broke everything)
	 * Interface methods are implicitly abstract
	 * Interface methods muse be abstract (except in Java 8 and above)
	 * Interface members are implicitly public
	 * Interfaces cannot have fields (except in Java 8 and above which added constants)
	 * Interfaces are implicitly final and static.
	 */
	/*public static final*/ String AIR = "Oxygen";
	
	/*public abstract*/ void breathes();
	
	// As of Java 8: the default keyword allows us to add default implementation to an interface method
	/*public*/ default void hold() {
		System.out.println("Holding my breath");
	}

}
