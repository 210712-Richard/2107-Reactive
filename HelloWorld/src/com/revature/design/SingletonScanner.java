package com.revature.design;

import java.util.Scanner;

public class SingletonScanner {
	// Singleton: An object that can only be instantiated once.
	// It is a design pattern and in Java to implement the Singleton design pattern
	// we need 3 things:

	// 2. A private static member of the type of the class
	private static SingletonScanner instance;

	private Scanner scan;

	// 1. A private constructor
	private SingletonScanner() {
		// if the constructor is private than nothing outside of the class can call the
		// constructor.
		scan = new Scanner(System.in);
	}

	// 3. A public static synchronized method to get the object
	public static synchronized SingletonScanner getScanner() {
		// checks to see if one exists
		if (instance == null) {
			instance = new SingletonScanner();
		}
		return instance;
	}

	public Scanner getScan() {
		return scan;
	}
}
