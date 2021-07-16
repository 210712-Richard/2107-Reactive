package com.revature.except;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class ExceptionExample {
	public static void main(String[] args) {
		System.out.println("Starting Main");
//		try {
//			throwingMethod();
//		} catch(Exception e) {
//			// I would log the exception here.
//			e.printStackTrace();
//		}
		
		//checkedUnchecked();
		
		//throwKeyword();
		
		try {
			method1();
			System.out.println("No exception occurred.");
		} catch (TooMuchPieException e) {
			e.printStackTrace();
			method2();
			//System.exit(1);
		} finally {
			System.out.println("Finally always runs");
		}
		
		System.out.println("Finishing Main");
	}
	
	private static void method1() throws TooMuchPieException{
		System.out.println("Eating pie in Method 1");
		Random rand = new Random();
		int i = rand.nextInt(2);
		if(i == 0) {
			throw new TooMuchPieException();
		}
		System.out.println("Safe");
	}
	private static void method2() {
		System.out.println("Eating too much pie in Method 2");
		throw new RuntimeException();
	}
	
	private static void throwKeyword() {
		System.out.println(convertToInt("100"));
		convertToInt("Hello");
	}

	private static int convertToInt(String string) {
		for(int i = 0; i<string.length(); i++) {
			if(string.charAt(i) <= 47 || string.charAt(i) >= 58) {
				throw new StringContainsCharactersException();
			}
		}
		return Integer.parseInt(string);
	}

	public static void checkedUnchecked() {
		System.out.println(5.0/0.0);
		//int i = 5/0;
		int i = 5;
		int j = 0;
		System.out.println((j==0)? "Infinity" : (i/j));
		
		String s = null;
		//System.out.println(s.substring(1));
		myMethod(s);
		
		// An unchecked exception can just be solved by being a little bit better
		
		FileReader f = null;
		try {
			f = new FileReader("C:\\Users\\RichardOrr\\GitRepos\\2107-Reactive\\HelloWorld\\src\\com\\revature\\except\\ExceptionExample.java");
			while(f.ready()) {
				System.out.print((char) f.read());
			}
		} catch (Error e) {
			System.out.println("This happens if an error occurs");
		} catch (FileNotFoundException | ArithmeticException e) {
			System.out.println("If A FileNotFound occurs, we're ok.");
		} catch (Exception e) {
			System.out.println("An exception occurred, what now?");
			e.printStackTrace();
		} finally {
			// This happens no matter what
			try {
				f.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	private static boolean myMethod(String string) {
//		if(string == null) {
//			return false;
//		}
		//return string.equals("Hello");
		return "Hello".equals(string);
	}

	public static void throwingMethod() {
		System.out.println("Starting the throwing method.");
		int i = 0;
		
		if(i == 0) {
			throw new RuntimeException();
		}
		System.out.println("Finishing the throwing method.");
		return;
	}
}

class StringContainsCharactersException extends RuntimeException {
	
}

class TooMuchPieException extends Exception {
	public TooMuchPieException() {
		super("I ate too much pie.");
	}
}