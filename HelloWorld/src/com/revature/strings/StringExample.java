package com.revature.strings;

import java.util.Arrays;

public class StringExample {
	public static void main(String[] args) {
		// Strings
		/*
		 * A representation of a list of characters.
		 * In Java, Strings are:
		 * Immutable
		 * final class
		 * Objects in Heap memory that get reused.
		 */
		//stringPool();
		//mutability();
		//stringAPI();
		stringBuilder();
	}

	// StringBuilder and String Buffer have identical APIs
	/*
	 * String			|	StringBuilder		|	StringBuffer
	 * --------------------------------------------------------------
	 * final			|	final				|	final				cannot extend these classes
	 * immutable		|	mutable				|	mutable				StringBuilder and StringBuffer do not use the String Pool
	 * thread-safe		|	not thread-safe		|	thread-safe			StringBuffer sacrifices speed for thread-safety.
	 * fast				|	fast				|	slow
	 */
	private static void stringBuilder() {
		// StringBuilder (and StringBuffer) are mutable String APIs
		StringBuilder sb = new StringBuilder("Hello");
		StringBuilder sbCopy = sb;
		System.out.println(sbCopy == sb);
		
		sb.append(" World");
		System.out.println(sb);
		System.out.println(sbCopy == sb);
		// We can modify our string values without creating a whole bunch of strings in the string pool.
		System.out.println(sbCopy);
		sb.reverse();
		System.out.println(sb);
		
		String s = sb.toString(); // doesn't return a canonical representation. It isn't in the string pool.
		
		System.out.println(s);
		String s1 = "dlroW olleH";
		System.out.println(s == s1);
		
	}

	private static void stringAPI() {
		// When we talk about the "string api" we are referring to the methods on the String object.
		String s = "      Hello World  ";
		System.out.println(s+".contains(\"World\"): "+s.contains("World"));
		System.out.println(s.toLowerCase());
		System.out.println(s);
		// all of the String API methods that modify a string, return a new string with the modifications.
		System.out.println(s.trim());
		System.out.println(s.replace("Worl", "da").trim().toUpperCase());
		s = "Hello World, My name is Richard";
		System.out.println(Arrays.toString(s.split(" ")));
		String b = "Bean";
		System.out.println(Arrays.toString(b.split("")));
		
		
		s = "http://mywebsite.com/cats/1";
		s = s.substring(s.indexOf('/')+1);
		System.out.println(s);
		s = s.substring(s.indexOf('/')+1);
		System.out.println(s);
		s = s.substring(s.indexOf('/')+1);
		System.out.println(s);
		
		s = "http://mywebsite.com/cats/1";
		System.out.println(s.replace("http://mywebsite.com/", ""));
	}

	private static void mutability() {
		// Strings in Java are immutable
		String s1 = "Hello";
		String s2 = s1;
		System.out.println(s1 == s2);
		s2 = s2 + "Goodbye";
		System.out.println(s1 == s2);
		System.out.println(s1);
		System.out.println(s2);
		
		char[] arr = s1.toCharArray();
		System.out.println(Arrays.toString(arr));
		arr[0] = 'o';
		System.out.println(Arrays.toString(arr));
		System.out.println(s1);
		
	}

	private static void stringPool() {
		// The string pool is a group of strings that have been allocated previously and can be reused by Java.
		// The String pool starts empty (or close to it, at least) and as strings are created by the program,
		// they begin to fill the pool.
		
		String s1 = "Hello"; // A string literal.
		// When I use a String literal, the JVM checks the String Pool to see if it has created that String.
		// If it has, it returns that object. If it has not, it creates a new object, puts it in the pool, and returns that object.
		// We refer to a string from the string pool as being a "canonical" string.
		String s2 = "Hello";
		System.out.println("s1==s2: "+(s1==s2));
		
		String s3 = new String("Hello"); // I just called the String constructor.
		// Since we called the constructor directly, the JVM didn't get a chance to intercede.
		// It didn't get to check the string pool for a string that already existed.
		// If we want a "canonical" string, we cannot use the constructor.
		
		System.out.println("s1==s3: "+(s1==s3));

		System.out.println("s1.equals(s3): "+(s1.equals(s3)));
		
		String s4 = s3.intern(); // returns a canonical representation of the string object.
		System.out.println("s1==s4: "+(s1==s4));
		
		
	}
}
