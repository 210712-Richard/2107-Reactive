package com.revature.factory;

public class SpreadableFactory {
	// A Factory is an object that can return an instance of an interface
	// so we can abstract the usage of that object away from it's implementation
	public Spreadable getSpreadable() {
		return new Jam();
	}
	
	public Spreadable getSpreadable(String type) {
		switch(type) {
		case "Cheese": return new Cheese();
		default: return new Jam();
		}
	}

	public static void main(String[] args) {
		SpreadableFactory factory = new SpreadableFactory();
		Spreadable spread = factory.getSpreadable();
		// I have toast and wish to spread something on it.
		spread.spread();
		factory.getSpreadable("Cheese").spread();
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