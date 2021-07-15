package com.revature.design;

import java.util.Scanner;

public class SingletonUsage {
	public static void main(String[] args) {
		Scanner scan= SingletonScanner.getScanner().getScan();
		System.out.println("Enter a thing: ");
		System.out.println(scan.nextLine());
		
		Scanner scan2 = SingletonScanner.getScanner().getScan();
		
		System.out.println(scan == scan2);
	}
}
