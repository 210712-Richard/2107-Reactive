package com.revature.auto;

public class Automobile {
	private int wheels;
	private String make;
	private String model;
	private String color;
	private int year;
	
	public static void main(String[] args) {
		Automobile a = new Automobile();
		System.out.println(a);
		System.out.println(new Automobile("Chevy", "Corolla"));
	}
	
	public Automobile() {
		this("Kia", "Rio", "Blue", 2014, 4);
	}
	
	public Automobile(String make, String model) {
		this(make, model, "Blue", 2014, 4);
	}
	
	public Automobile(String make, String model, String color, int year) {
		this(make, model, color, year, 4);
	}
	
	public Automobile(String make, String model, String color, int year, int wheels) {
		//super();
		this.make = make;
		this.model = model;
		this.color = color;
		this.year = year;
		this.wheels = wheels;
	}

	@Override
	public String toString() {
		return "Automobile [wheels=" + wheels + ", make=" + make + ", model=" + model + ", color=" + color + ", year="
				+ year + "]";
	}
	
	
}
