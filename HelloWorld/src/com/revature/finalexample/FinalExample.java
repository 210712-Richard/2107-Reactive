package com.revature.finalexample;
/*
 * final
 * 	class - can not be extended
 *  method- can not be overridden
 *  field - can not be reassigned
 */
public class FinalExample {
	public static void main(String[] args) {
		A a = new A();
		System.out.println(a.b.name);
		a.b.name = "hello";
		System.out.println(a.b.name);
		//a.b = new Bean(); // final prevents reassignment
		// primitive values are assigned directly
		//a.x = 2;
		a.method();
		B b = new B();
		b.method();
		
		C c = new C();
		c.method();
	}
}


class Bean {
	public String name = "Bean";
}

class A {
	final int x = 5;
	final Bean b = new Bean();
	void method() {
		System.out.println("This is a method. A");
	}
}

class B extends A {
	@Override
	final void method() {
		System.out.println("This is a method. B");
	}
}

final class C extends B {
//	@Override
//	void method() {
//		System.out.println("This is a method. C");
//	}
}

class D /*extends C*/ {
	
}