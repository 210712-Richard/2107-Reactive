package com.revature.util;

import java.util.Scanner;

public class SingletonScanner {
	private static SingletonScanner instance;

	private Scanner scan;

	private SingletonScanner() {
		scan = new Scanner(System.in);
	}

	public static synchronized SingletonScanner getScanner() {
		if (instance == null) {
			instance = new SingletonScanner();
		}
		return instance;
	}

	public Scanner getScan() {
		return scan;
	}
}
