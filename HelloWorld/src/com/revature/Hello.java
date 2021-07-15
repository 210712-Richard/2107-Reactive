/*
 * Naming convention for packages: reverse.domain.name
 * 
 * Package declaration is the very first part of a Java file.
 * If you do not declare a package, your class belongs to the default package.
 */
package com.revature;

// Import statements should come after the package declaration and allow us
// to reference classes without having to use the fully qualified class name.
import java.util.Arrays;

/*
 * What is the name of this class? Hello?
 * Fully Qualified Class Name: The package and class name. com.revature.Hello
 * 
 * Classes by convention are named in PascalCase - EveryWordIsCapitalized
 */
public class Hello /*extends Object */{
	
	/*
	 * Methods by convention are named in camelCase - everyWordExceptTheFirstIsCapitalized
	 * 
	 * Main method: The entry point into your application. Without a main method,
	 * 		Java can't run your app.
	 * 
	 * 		main - the name of the main method is "main"
	 * 		void - the main method cannot return anything.
	 * 		static - the main method is a member of a class not an object. No
	 * 				object needs to be instantiated to run main.
	 * 		public - the main method must be a public member of a class
	 * 				or Java can't find it.
	 * 		args - the main method must take an array of Strings as arguments
	 * 
	 * 	main may be final or synchronized but doesn't need to be either.
	 *  main can throw any kind of exception or no exceptions.
	 */
	public static void main(String[] args) {
		System.out.println("Hello World");
		// variable declarations are also camelCase by convention
		int[] arr = {5, 5, 5, 32, 2};
		System.out.println(arr);
		System.out.println(Arrays.toString(arr));
		
		System.out.println(args);
		System.out.println(Arrays.toString(args));
		
		Hello h = new Hello();
		// In Java, an object is an instance of a class. A class is like a blueprint that
		// dictates the state and behavior of any objects instantiated with it.
		// All classes implicitly extends Object unless they explicitly extends something else.
		h = new Hello("hi");
		h = new Hello(1, 3);
	}
	public static final int NUMBER_WHEELS = 4;
	/* 
	 * Constructor: A special method used to instantiate objects. All classes have an implicit no-arguments constructor
	 * 		unless they define a constructor.
	 */
	// This constructor is basically the same as the implicit constructor we are provided with.
	/*
	 * public Hello() { super(); }
	 */
	public Hello() {
		super();
		System.out.println("Constructor called.");
	}
	
	// overloading the constructor: Declaring multiple versions of it with differnet parameters
	public Hello(String h) {
		//super(); // A call to the constructor of the parent class. An implicit first line.
		System.out.println("Constructor called but with String: "+h);
	}
	
	public Hello(int i, int j) {
		// constructor chain calls must be the first line of a constructor
		this("args"); // Call another constructor in the same class
		System.out.println("constructor called with two numbers");
	}
	
	{
		// Instance block. This will run anytime an object is instantiated
		System.out.println("Before the constructor runs, even.");
	}
	
	static {
		// Static block. This will run when the class is loaded.
		System.out.println("Before the main method, usually.");
	}
}
