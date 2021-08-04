package com.revature.philosophy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// 5 philosophers sit down at a table to eat dinner
// Between each philosopher there is a chopstick
// To eat their rice, each philospher needs two chopsticks, one from their left, and one from their right
// There are not enough chopsticks.
// How do the philosophers eat their food without starving any philosophers??
public class Philosphers {
	public static void main(String[] args) {
		Chopstick one = new Chopstick("one");
		Chopstick two = new Chopstick("two");
		Chopstick three = new Chopstick("three");
		Chopstick four = new Chopstick("four");
		Chopstick five = new Chopstick("five");
		
		Philosopher p1 = new Philosopher("one", one, five);
		Philosopher p2 = new Philosopher("two", two, one);
		Philosopher p3 = new Philosopher("three", three, two);
		Philosopher p4 = new Philosopher("four", four, three);
		Philosopher p5 = new Philosopher("five", five, four);
		
		ExecutorService pool = Executors.newFixedThreadPool(5);
		pool.execute(p1);
		pool.execute(p2);
		pool.execute(p3);
		pool.execute(p4);
		pool.execute(p5);
	}
}

class Philosopher implements Runnable {
	
	private String position;
	private Chopstick left;
	private Chopstick right;
	
	public Philosopher(String position, Chopstick left, Chopstick right) {
		this.position = position;
		this.left = left;
		this.right = right;
	}

	@Override
	public void run() {
		while(true) {
			// think
			System.out.println("philosopher "+position+" is thinking.");
			synchronized(left) {
				System.out.println("philosopher "+position+" has left chopstick.");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized(right) {
					System.out.println("philosopher "+position+" is eating.");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			System.out.println("Philosopher "+position+" is done eating for now.");
		}
	}
	
}

class Chopstick {
	private String position;
	
	public Chopstick(String position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "Chopstick [position=" + position + "]";
	}
}