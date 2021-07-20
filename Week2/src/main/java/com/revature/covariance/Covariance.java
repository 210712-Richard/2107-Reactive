package com.revature.covariance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Covariance {

	public static void main(String[] args) {
		/*
		 * Something that is Covariant is something that given the subtyping rules of Java,
		 * Type T extends S so T is an S.
		 * 
		 * Arrays are covariant. An array of type S can hold a T
		 */
		Number[] n = new Number[7];
		n[0] = (Integer) 5;
		n[1] = (Float) 5f;
		n[2] = (Long) 5l;
		
		for(Number num : n) {
			if(num != null) {
				System.out.println(num.getClass());
			}
		}
		
		// Generic types are removed at runtime so the JVM doesn't know what type anything is
		List<Integer> ints = new ArrayList<Integer>();
		// The compiler can't let us do this because the JVM would crash if we did the wrong thing.
		// We can't *widen* a generic type.
		//List<Number> nums = ints;
		
		// covariant generic
		List<?> myNumbers = new ArrayList<Integer>();
		myNumbers = new ArrayList<String>();
		
		/*
		 * In a covariant generic we cannot perform an insert. The compiler doesn't know what the actualy type will be
		 * and therefore cannot check that the type is correct.
		 */
		//myNumbers.add("hello");
		
		// contravariance: Going from super to subclass
		// With covariance you cannot write, but with contravariance you cannot read.
		
		// This arraylist can be any type so long as it extends Number
		List<? super Number> add = new ArrayList<Object>();
		add.add((Float) 3f);
		// We cannot know what the actual type is going to be at runtime, so we cannot implicitly cast.
		Float f = (Float) add.get(0);
		System.out.println(f);
		
		List<Integer> myInts = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
		List<Number> list = new ArrayList<Number>();
		copyNumberToList(myInts, list);
		System.out.println(list);
	}
	// ? extends Number indicates that the class indicated by ? can be any specific implementation of the super class
	// ? super Number indicates that the class indicated by ? can be ANY implementation of the super class
	public static void copyNumberToList(List<? extends Number> source, List<? super Number> destination) {
		for(Number n : source) {
			destination.add(n);
		}
	}

}
