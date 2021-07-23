package com.revature.reflection;

@MyAnnotation
public class Annotations {
	public static void main(String[] args) {
		System.out.println(Annotations.class.isAnnotationPresent(MyAnnotation.class));
		/*
		 * Most Java Frameworks will mark a class with an annotation and then discover those classes
		 * and create objects of them for you.
		 */
	}
}
