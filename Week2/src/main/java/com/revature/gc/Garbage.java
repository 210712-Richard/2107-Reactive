package com.revature.gc;

public class Garbage {
	private String name;
	public Garbage(String name) {
		this.name = name;
	}
	
	public static void main(String[] args) {
		// The GC will collect orphaned objects (objects with no reference to them)
		// it will do this when it thinks that it needs to, ie when memory is starting to run out.
		
		for(int i = 0; i< 10; i++) {
			Garbage g = new Garbage("" + i);
			// if we're especially concerned with the memory the JVM is taking up, we can
			// pretty please with sugar on top ask the GC to run.
			System.gc(); // there is no guarantee that the GC will do anything when you call this method.
		}
		System.out.println("All of the garbage objects are orphans.");
		System.gc();
	}
	
	@Override
	public String toString() {
		return "Garbage [name=" + name + "]";
	}
	
	@Override
	public void finalize() {
		/* finalize is a method that the GC will call on every object before it frees the associated object's memory.
		 * finalize is not a method on the garbage collector
		 * finalize does not call the garbage collector
		 * the garbage collector calls finalize as a courtesy just before it frees the memory.
		 * 
		 * You should never override finalize as leaving resources open in an object that might be freed is now considered bad practice.
		 * However, it exists so that you can close resourses held by objects that are being garbage collected.
		 * In fact, it is considered enough of a bad practice that Java 11 has deprecated the method entirely.
		 * 
		 * 
		 * CALLING FINALIZE DOES NOT DO ANYTHING.
		 * It does NOT mark the object for garbage collection.
		 * It does NOT call the garbage collector in any way.
		 * CALLING FINALIZE DOES NOT DO ANYTHING.
		 * 
		 * The finalize method does not do anything.
		 */
		System.out.println(this);
	}
}
