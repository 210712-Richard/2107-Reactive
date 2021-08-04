package com.revature.threads;

public class MonitorExample {
	// Monitor Design Pattern: A design pattern that allows us to create a thread-safe object
	// where only one thread can access the object at a single point in time.
	
	
	// Monitor design pattern is implemented by both Vector and HashTable in Java as well as a few other things
	
	// We don't want to access the get method of our name or id field if another thread is accessing the set method.
	
	private String name;
	private Integer id;
	private final Object lockName = new Object();
	private final Object lockId = new Object();
	public String getName() {
		synchronized(lockName) {
			return name;
		}
	}
	public void setName(String name) {
		synchronized(lockName) {
			this.name = name;
		}
	}
	public Integer getId() {
		synchronized(lockId) {
			return id;
		}
	}
	public void setId(Integer id) {
		synchronized(lockId) {
			this.id = id;
		}
	}
	
	
}
