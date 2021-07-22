package com.revature.function;

import java.util.function.Function;

public class FirstFunction {
	public static void main(String[] args) {
									  //parameters	skinny arrow	return value
		Function<String, Integer> f = (string) 		-> 				string.length();
		
		System.out.println(f.apply("Hello"));
		
		myThing(f, "Goodbye");
		
		myThing((s)-> {
			int a = 0;
			for(char c : s.toCharArray()) {
				a += c;
			}
			return a;
		}, "Goodbye");
	}
	
	public static void myThing(Function<String, Integer> f, String myString) {
		System.out.println(f.apply(myString));
	}
}
