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
public class Hello {
	
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
		int[] arr = {5, 5, 5, 32, 2};
		System.out.println(arr);
		System.out.println(Arrays.toString(arr));
		
		System.out.println(args);
		System.out.println(Arrays.toString(args));
		
		
	}
}
