package com.revature.primitives;

public class Primitives {
	/*
	 * Primitive: a value that is not an object. Simple piece of data, no fields, no
	 * methods. Primitive reference: a variable that holds a piece of data instead
	 * of an object Object reference: a variable that holds a reference to an
	 * object.
	 */

	/*
	 * Primitives: boolean - ?????? - true / false - Boolean byte - 1 byte - a
	 * signed integer in 1 byte - Byte char - 2 bytes - a unicode character -
	 * Character short - 2 bytes - a signed integer in 2 bytes - Short int - 4 bytes
	 * - a signed integer in 4 bytes - Integer long - 8 bytes - a signed integer in
	 * 8 bytes - Long float - 4 bytes - a signed floating point number in 4 bytes -
	 * Float double - 8 bytes - a signed floating point number in 8 bytes - Double
	 * 
	 * The space that a boolean takes is JVM dependent. It can range from a single
	 * bit in bit addressable hardware to a full byte. Some JVMs will provision a
	 * byte for multiple boolean values.
	 */

	public static void main(String[] args) {
		//values();
		//convertibleIntTypes();
		//literalValues();
		//implicitCasting();
		//objectsVsPrimitives();
		//System.out.println(parseInt("-234"));
		
		
		// values();
		// convertibleIntTypes();
		// literalValues();
		// implicitCasting();
		// objectsVsPrimitives();
		//System.out.println(parseInt("-234"));
		//System.out.println(parseInt("-234-6"));
		//System.out.println(parseInt("-hello"));
		overloading();
	}

	/*
	 * Overloading: Using the same name for a method with different parameters. Overloaded methods may have different return types,
	 * 		and may throw different exceptions, but must have different parameters.
	 * 
	 * Overloading is a form of runtime polymorphism - overloading occurs at runtime, when the JVM determines the best match
	 * 	for a method call.
	 * 
	 * Overloading Precedence
	 * 1. Exact Match: Is there a match where I don't have to do any work?
	 * 2. Implicit Cast: Is there a match if I just cast something?
	 * 3. Autoboxing: Can I turn an object into a primitive or a primitive into an object?
	 * 4. VarArgs: 
	 */
	
		
	private static void overloading() {
		int intPrimitive = 5;
		Integer intObject = 5;
		short shortPrimitive = 5;
		Short shortObject = 5;
		long longPrimitive = 5;
		Long longObject = 5l;
		Byte byteObject = 3;

		method(intPrimitive); // Exact Match - 1
		method(shortPrimitive); // Implicit Cast - 1
		method(intObject); // Autoboxing - 1
		method(shortObject); // Exact Match - 2
		method(byteObject); // Unwrap and Implicit Cast - 1
		method(longPrimitive);
		method(longObject);
		
		method(intPrimitive, longPrimitive); // exact match - 5
		method(intPrimitive, intPrimitive); // implicit cast - 5
		//method(intPrimitive, longObject); // ambiguous. JVM has to do the same amount of work, for both options, and chooses neither.
		// If the JVM has multiple methods it could call with specific parameters it will refuse to choose.
		method(intPrimitive, longObject.longValue()); // exact match - 5
		method(intObject, shortPrimitive); //implicit - 4
		method(1);
		method(1, 1);
		method(1, 1, 1);
		method(1, 1, 1, 1);
		int[] arr = {1, 2, 3, 4, 5, 6, 7};
		method(1, arr);
		method(1, '2', '4');
		method(1, intObject, 6, 7, shortObject);
	}
	
	// Method 1
	public static void method(int i) {
		System.out.println("Method 1");
	}
	// Method 2
	public static void method(Short s) {
		System.out.println("Method 2");
	}
	// Method 3
	public static void method(Long l) {
		System.out.println("Method 3");
	}
	// Method 4
	public static void method(Integer x, long y) {
		System.out.println("Method 4");
	}
	// Method 5
	public static void method(int x, long y) {
		System.out.println("Method 5");
	}
	/*
	 * VarArgs: Must be the last parameter of a method.
	 * 			There can be only one vararg parameter in a method
	 * 			You can pass an array into varargs
	 * 			You can pass any number of nonarray arguments into varargs
	 */
	// Method 6
	public static void method(int i, int...x) {
		System.out.println("parameters: "+i+", "+java.util.Arrays.toString(x));
	}
	// Takes in a String representation of an integer value and returns an integer
	// value.
	public static int parseInt(String string) {
		// input validation skipped for time
		int result = 0;
		char c[] = string.toCharArray();
		boolean neg = false;
		for (int i = 0; i < c.length; i++) {
			if ('-' == c[i]) {
				neg = true;
			} else {
				result = result + (c[i] - 48) * (int) Math.pow(10, c.length - (i + 1));
			}
		}
//				if(neg) {
//					result = result * -1;
//				}
//				return result;
		return neg ? -1 * result : result;
	}

	private static void objectsVsPrimitives() {
		// Object: State and behavior. Fields and methods.

		// == operator: An operator that compares two values.

		int intPrimitive = 5;
		short shortPrimitive = 5;
		long longPrimitive = 5l;
		float floatPrimitive = 5f;
		double doublePrimitive = 5.0;
		System.out.println("int==short: " + (intPrimitive == shortPrimitive));
		System.out.println("int==long: " + (intPrimitive == longPrimitive));
		System.out.println("int==float: " + (intPrimitive == floatPrimitive));
		System.out.println("int==double: " + (intPrimitive == doublePrimitive));

		Integer intObject = 5;
		// can I wrap the primitive into an object of the same type?
		// can I unwrap the object into a primitive of the same type?
		System.out.println("int==Integer: " + (intPrimitive == intObject));
		System.out.println("short==Integer: " + (shortPrimitive == intObject));
		Integer intObject2 = new Integer(5);
		System.out.println("Integer == Integer: " + (intObject == intObject2));
		// Object references hold memory locations, not values
		// == compares the value of the reference, and for object references that is a
		// memory address.
		// object == object: only true if both objects are literally the same object in
		// memory.
		System.out.println("Integer == Integer: " + (intObject == intObject));
		// To check the equality of the value of two objects we use the equals() method.

		System.out.println("Integer.equals(Integer): " + (intObject.equals(intObject2)));

		// Strings are objects
		String s = "fine";
		String s2 = "fine";
		String s3 = new String("fine");

		System.out.println("s == s2 " + (s == s2));
		System.out.println("s == s3 " + (s == s3));
		System.out.println("When comparing objects, use .equals():" + s.equals(s3));

		Short shortObject = 5;
		// System.out.println("Short == Integer: "+(shortObject == intObject));
		System.out.println("Short.equals(Integer): " + shortObject.equals(intObject)); // equals() checks types
		System.out.println(shortObject.intValue() == intObject.intValue());
	}

	private static void implicitCasting() {
		char c = 8;
		short s = 8;
		byte b = 8;
		int i;

		i = c;
		i = b;
		i = s;

		s = b;
		s = (short) c;
		s = (short) i;

		// byte - 8 bits. -2^7 - 2^7 -1 = -128 - 128
		// short - 16 bits. -2^15 - 2^15 -1 = -32768 - 32767
		// char - 16 bits. 0 - 2^16 -1 = 0 - 65535

		c = (char) b;
		c = (char) s;
		c = (char) i;
	}

	private static void literalValues() {
		int i = 3; // 3 is a literal int value
		i = 763;
		float f = 98; // assigning an int literal to a float reference.
		double d = 8.0; // 8.0 is a double literal, 8d is also a double literal
		f = 8.0f; // 8.0f and 8f are float literals
		long l = 2999222120l; // l makes an int literal into a long literal
		char c = 'c'; // 'c' is a char literal

		System.out.println((double) 8);
	}

	// Convertible Int Type: Any type that can be implicitly cast into an int.
	// The JVM will implicitly convert one type to another if that conversion
	// can not result in loss of data.
	private static void convertibleIntTypes() {
		long l = ((long) Integer.MAX_VALUE) + 2;
		System.out.println(l);
		int i = (int) l;
		System.out.println(i);

		i = 0;

		boolean bool = true;
		// i = bool; // cannot implicitly or explicitly cast from boolean to any number
		// type, because boolean isn't a number
		byte b = 8;
		i = b; // every valid byte value is a valid int value.
		char c = 'c';
		char c2 = 68;
		System.out.println(c2);
		i = c; // every valid character is a valid int value.
		short s = 8;
		i = s; // every valid short is a valid int

		float f = 8; // floating point number.
		i = (int) f; // not a convertible int type. Can't hold decimal values
		f = 3.9f;
		System.out.println((int) f);
		double d = 8;
		i = (int) d; // not a convertible int type. Can't hold decimal values

		// float is a convertible double type
		d = f;
		f = (float) d;

		// s = i; // short can't hold int
		// c = i;
		// b = i;
		f = i; // floats can hold ints
		d = i; // doubles can hold ints
		l = i; // longs can hold ints
	}

	private static void values() {
		System.out.println(Double.BYTES);
		System.out.println(Integer.BYTES);
		System.out.println(Byte.MIN_VALUE);
		System.out.println(Byte.MAX_VALUE);
		// A byte takes up 8 bits. One of those bits is the sign bit.
		// that means we have 7 bits of data, with one bit controlling + or -
		// 2^7 = 128
		// 0-127 = 128 pieces of data
		// -1 - -128 = 128 pieces of data.
		// 2^8 = 256

		System.out.println(Long.MAX_VALUE);
		System.out.println(Long.MIN_VALUE);

		System.out.println(Double.MAX_VALUE);
		System.out.println(Double.MIN_VALUE);
		System.out.println(Double.MAX_EXPONENT);
		System.out.println(Double.MIN_EXPONENT);
		System.out.println(Double.NEGATIVE_INFINITY);
		System.out.println(Double.POSITIVE_INFINITY);
		double d = 5.0 / 0.0;
		System.out.println(d);
	}
}
