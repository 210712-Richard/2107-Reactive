package com.revature;

public class Bean {
	private String name;
	
	public Bean() {
		super();
	}
	
	public Bean(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return "Bean [name=" + name + "]";
	}
}
