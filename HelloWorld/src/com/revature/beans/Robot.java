package com.revature.beans;

public interface Robot {
	default void hold() {
		System.out.println("Awaiting orders.");
	}
}
