package com.revature.scopes;

public class ScopeExample {
	/*
	 * Scope
	 * 	The lifetime of a reference.
	 * 
	 * Java has 4 scopes:
	 * 1. class/static scope - Variables that belong to the class itself.
	 * 		Variables inside of the class scope exist starting when the class
	 * 		is loaded into the JVM and are available until the app terminates or
	 * 		the class somehow becomes unloaded.
	 * 2. instance/object scope - Variables that belong to individual objects.
	 * 		Variables inside of the object scope exist starting when the object is instantiated
	 * 		and are available within that object only and until the object is garbage collected.
	 * 3. method scope - variables that are declared within a method.
	 * 		Variables inside this scope exist only while the method is on the stack, and are only availble
	 * 		within that method.
	 * 4. block scope - variables declared within curly braces.
	 */
	public static int i; // class scoped.
	public int j;
	
	public static void main(String[] args) {
		staticMethod();
		ScopeExample s = new ScopeExample();
		s.j = 3; // j exists on the object we created.
		s.method();
	}
	
	static {
		// static block - inside the class
		i = 3;
		// j = 3; // j doesn't exist because this isn't an object. This is a class
	}
	
	private static void staticMethod() {
		// static method - inside the class
		i = 3;
		//j = 3;
		//k = 3;
	}
	
	private void method() {
		i = 3; // the method is inside the object inside the class so it can see class-scoped things
		j = 3; // the method is inside the object so it can see the object-scoped things.
		int k = 3;
		
		k = 2;
		for(int l = 3; l<4; l++)  {
			l = 5;
		}
		//l = 3;
	}
}
