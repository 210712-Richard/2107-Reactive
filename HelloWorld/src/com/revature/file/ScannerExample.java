package com.revature.file;

import java.util.Scanner;

public class ScannerExample {
	public static void main(String[] args) {
		//generalScannerUsage();
		thePerilsOfClosingTheScanner();
	}

	private static void thePerilsOfClosingTheScanner() {
		// The scanner takes in an input Stream. Such as System.in
		// When you close the scanner, you also close whatever input stream the scanner has.
		// If you don't want to close the inputstream, you can't close the scanner.
		// In Java, it is either very hard or impossible to reopen System.in
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter a thing: ");
		System.out.println(scan.nextLine());
		
		scan.close();
		Scanner scan2 = new Scanner(System.in);
		System.out.println("Enter a thing: ");
		System.out.println(scan2.nextLine());
		System.out.println("Hello?");
	}

	private static void generalScannerUsage() {
		Scanner scan = new Scanner(System.in);
		String s = scan.nextLine();
		System.out.println(s);
		System.out.println("Input your age: ");
		//Integer i = scan.nextInt();
		//scan.nextLine();
		Integer i = Integer.parseInt(scan.nextLine());
		System.out.println("Input your name: ");
		String name = scan.nextLine();
		System.out.println(name + " is "+ i + " years old");
	}
}
