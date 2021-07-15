package com.revature.shadowing;

public class Shadowing {

	public static void main(String[] args) {
		A a = new A();
		A b = new B();
		
		System.out.println(b.x+" "+b.method() + " "+ a.x + " " +a.method());
		
		// 42 42 42 42
		// 42 24 42 42 <-
		// 24 42 42 42
	}
	/*
	 * Member variables are not overridden. If A defines an x and B defines an x, then both of those x's exist
	 * If a child class defines a member that is also defined by the parent class then it will "shadow" that variable.
	 */

}

class A {
	public int x = 42;
	public int method() {
		return x;
	}
}

class B extends A{
	public int x = 24;
	@Override
	public int method() {
		return x;
	}
}