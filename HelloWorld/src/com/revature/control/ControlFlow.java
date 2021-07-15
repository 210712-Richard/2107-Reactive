package com.revature.control;

import java.util.Scanner;

public class ControlFlow {
	public static void main(String[] args) {
		/*
		 * When we talk about the flow of control through an application we are referring to 
		 * the entity that is currently executing on a thread of processing.
		 * 
		 * We might be talking about the flow of control through a block of code, through a method,
		 * through a structure, through an object, or through a class.
		 * 
		 * What line of code is executing and which line is next?
		 */
		
		System.out.println("Flow starts in Main");
		
		//ifStatement();
		//ternary();
		//loops();
		//enhancedFor();
		//shortCircuit();
		//switchStatement();
		breakControl();
		//endless();
		System.out.println("Flow will eventually pass back to the calling entity.");
	}

	private static void breakControl() {
		// breaks, labels, and continues
		for(int i = 0; i < 10; i++) {
			if(i==5) {
				System.out.println();
				continue; // jumps to the next iteration of the loop
			}
			if(i==7) {
				break; // terminates the loop
			}
			System.out.println(i);
		}
		
		System.out.println("labels");
		loop1: for(int i = 0; i < 4; i++) {
			bob: for(int j = 0; j < 4; j++) {
				if(i == 1 && j>1) {
					continue loop1;
				}
				if(i==2 && j == 0) {
					continue;
				}
				if(i==0 && j==1) {
					break bob;
				}
				System.out.println(i+" "+j);
			}
		}
	}

	private static void switchStatement() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Input a value: ");
		int x = Integer.parseInt(scan.nextLine());
		
		// in Java, Switch statements take an argument of the following types:
		// convertible int types, Strings, and Enums
		switch(x) {
		case 0:
			System.out.println("x was 0");
			break;
		case 1: 
			System.out.println("x was 1");
			break;
		case 5: 
			System.out.println("x was 5");
			System.out.println("So many lines.");
			break;
		default: System.out.println("x unrecognized"); break;
		case 3: System.out.println("x was 3"); break;
		case 57: System.out.println("x was 57"); break;
		}
	}

	private static void shortCircuit() {
		// precrement- ++x will increment and then evaluate the expression
		// postcrement - x++ will evaluate the expression and then increment
		int x = 6;
		int y = 6;
		int z = 6;
		System.out.println("x = " + (++x) + ", y = " + (y++) + ", z = "+ z);

		if(x++ == ++y && ++x == z++) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}
		
		System.out.println("x = " + x +", y = " + y + ", z = " + z);
		
		/*
		 * short circuit operators: && and ||
		 * 	Do not evaluate the second half if they aren't necessary to determine truth.
		 * 		true && true = true
		 * 		false && doesn't matter = false
		 * 
		 * 		false || true = true
		 * 		true || doesn't matter = true
		 * non-short circuit: & and |
		 * 	evaluate both sides regardless of truth
		 */
		
		//bitwise operations
		System.out.println(5 | 3); // 0101 | 0011 = 0111
	}

	private static void enhancedFor() {
		String[] animals = { "Horse", "Dog", "Bat", "Iguana"};
		for(int i = 0; i< animals.length; i++) {
			System.out.println(animals[i]);
		}
		System.out.println();
		// Enhanced for loop, for each loop, iterates through an iterable object
		for(String s: animals) {
			System.out.println(s);
		}
	}

	private static void loops() {
//		// for loop
//		for (int i = 0; i<10; i++) {
//			System.out.println(i);
//		}
		
		// indentical while loop
//		{
//			int i = 0;
//			while(i<10) {
//				System.out.println(i);
//				i++;
//			}
//		}
		
		{
			int i = 6;
			int j = 1;
			while(j<i) {
				System.out.println(j);
				j++;
				i--;
			}
		}
		
		System.out.println("same but for for loop");
		for(int i = 6, j = 1; j<i; j++,i--) {
			System.out.println(j);
		}
		
		// bads
//		for(;;) {
//			System.out.println(5.0/0.0);
//		}
//		while(true) {
//			System.out.println(5.0/0.0);
//		}
		
		System.out.println("do while");
		int i = 14;
		do {
			System.out.println(i);
			i++;
		} while(i < 10);
		
		i = 14;
		{
			System.out.println(i);
			i++;
			while(i<10) {
				System.out.println(i);
				i++;
			}
		}
		
	}

	private static void ternary() {
		// Ternary: an operator that returns values conditionally.
		// 		An if statement that returns things.
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Input a value: ");
		int x = Integer.parseInt(scan.nextLine());
		
		String s = (x>3) ? "x is greater than 3" : "x is not greater than 3";
		System.out.println(s);
		
		// (boolean expression) ? [value if true] : [value if false]
		
		// please don't nest ternaries
		System.out.println((x>3)?"x>3":(x<3)?"x<3":"x is 3");
	}

	private static void endless() {
		while(true) {
			
		}
	}

	private static void ifStatement() {
		System.out.println("Calling a method results in flow passing to that method.");
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Input a value: ");
		int x = Integer.parseInt(scan.nextLine());
		System.out.println(x);
		
		// the humble if statement
//		if(x>3) {
//			System.out.println("x is greater than 3");
//		}
		
		// simple if-else
//		if(x > 3) {
//			System.out.println("x is greater than 3");
//		} else {
//			System.out.println("x is not greater than 3");
//		}
		
		// nested if-else
		if (x > 3) {
			System.out.println("x is greater than 3");
		} else {
			if(x <3) {
				System.out.println("x is less than 3");
			} else {
				System.out.println("x is 3");
			}
		}
		
		
		if (x>3)
			System.out.println("hi");
		else
			if(x<3)
				System.out.println("bye");
			else
				System.out.println("3");
		//return; // allows us to return the flow of control back to the calling method. Implicit final line in a void method.
	}
}
