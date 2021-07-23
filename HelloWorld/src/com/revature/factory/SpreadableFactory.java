package com.revature.factory;

public class SpreadableFactory {
	// A Factory is an object that can return an instance of an interface
	// so we can abstract the usage of that object away from it's implementation
	public Spreadable getSpreadable() {
		return new Cheese();
	}

	public static void main(String[] args) {
		SpreadableFactory factory = new SpreadableFactory();
		Spreadable spread = factory.getSpreadable();
		// I have toast and wish to spread something on it.
		spread.spread();
	}

}

interface Spreadable{
	void spread();
}

class Cheese implements Spreadable {
	@Override
	public void spread() {
		System.out.println("mmm cheese");
	}
}

class Jam implements Spreadable {
	@Override
	public void spread() {
		System.out.println("yay jam");
	}
}