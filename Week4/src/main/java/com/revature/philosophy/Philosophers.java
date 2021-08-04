package com.revature.philosophy;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 5 philosophers sit down at a table to eat dinner
// Between each philosopher there is a chopstick
// To eat their rice, each philospher needs two chopsticks, one from their left, and one from their right
// There are not enough chopsticks.
// How do the philosophers eat their food without starving any philosophers??
public class Philosophers {
	
	public static Map<Philosopher,Integer> philosophers= new HashMap<Philosopher, Integer>();
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
		
		philosophers.put(p1,1);
		philosophers.put(p2,2);
		philosophers.put(p3,3);
		philosophers.put(p4,4);
		philosophers.put(p5,5);

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
	private boolean even;

	public Philosopher(String position, Chopstick left, Chopstick right) {
		this.position = position;
		this.left = left;
		this.right = right;
		
		switch(position) {
		case "three":
		case "five":
		case "one": even = false;
		break;
		case "two":
		case "four": even = true;
		break;
		}
	}

	@Override
	public void run() {
		//deadlock();
		//livelock();
		//someKindOfSolution();
		//michael();
		hasEaten();
	}
	private void hasEaten() {
		while(true) {
			System.out.println("philosopher " + position + " is thinking.");
			try {
				if (left.getLock().tryLock(1+Philosophers.philosophers.get(this), TimeUnit.SECONDS)) {
					System.out.println("philosopher " + position + " has left chopstick.");
					Thread.sleep(1000);
					if (right.getLock().tryLock(1+Philosophers.philosophers.get(this), TimeUnit.SECONDS)) {
						System.out.println("philosopher " + position + " is EATING.");
						Thread.sleep(1000);

						Philosophers.philosophers.put(this, Philosophers.philosophers.get(this)-1);
						System.out.println("Philosopher " + position + " is done eating for now.");

						right.getLock().unlock();
					} else {
						System.out.println("philosopher " + position + " Couldn't Eat");
						Philosophers.philosophers.put(this, Philosophers.philosophers.get(this)+1);
					}

					left.getLock().unlock();
				} else {
					System.out.println("philosopher " + position + " Couldn't Eat");

					Philosophers.philosophers.put(this, Philosophers.philosophers.get(this)+1);
				}
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}

	private void michael() {
		if(even) {
			even();
		} else {
			deadlock();
		}
	}

	private void even() {
		while (true) {
			// think
			System.out.println("philosopher " + position + " is thinking.");
			synchronized (right) {
				System.out.println("philosopher " + position + " has left chopstick.");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (left) {
					System.out.println("philosopher " + position + " is EATING.");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			System.out.println("Philosopher " + position + " is done eating for now.");
		}
	}

	private void someKindOfSolution() {
		while(true) {
			System.out.println("philosopher " + position + " is thinking.");
			try {
				if (left.getLock().tryLock(1, TimeUnit.SECONDS)) {
					System.out.println("philosopher " + position + " has left chopstick.");
					Thread.sleep(1000);
					if (right.getLock().tryLock((new Random()).nextInt(10), TimeUnit.SECONDS)) {
						System.out.println("philosopher " + position + " is EATING.");
						Thread.sleep(1000);

						System.out.println("Philosopher " + position + " is done eating for now.");

						right.getLock().unlock();
					} else {
						System.out.println("philosopher " + position + " Couldn't Eat");
					}

					left.getLock().unlock();
				} else {
					System.out.println("philosopher " + position + " Couldn't Eat");
				}
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void livelock() {
		while (true) {
			// think
			System.out.println("philosopher " + position + " is thinking.");
			try {
				if (left.getLock().tryLock(1, TimeUnit.SECONDS)) {
					System.out.println("philosopher " + position + " has left chopstick.");
					Thread.sleep(1000);
					if (right.getLock().tryLock(1, TimeUnit.SECONDS)) {
						System.out.println("philosopher " + position + " is EATING.");
						Thread.sleep(1000);

						System.out.println("Philosopher " + position + " is done eating for now.");
						right.getLock().unlock();
					} else {
						System.out.println("philosopher " + position + " Couldn't Eat");
					}

					left.getLock().unlock();
				} else {
					System.out.println("philosopher " + position + " Couldn't Eat");
				}
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

		}
	}

	public void deadlock() {
		while (true) {
			// think
			System.out.println("philosopher " + position + " is thinking.");
			synchronized (left) {
				System.out.println("philosopher " + position + " has left chopstick.");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (right) {
					System.out.println("philosopher " + position + " is EATING.");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			System.out.println("Philosopher " + position + " is done eating for now.");
		}
	}

}

class Chopstick {
	private String position;
	private Lock lock;

	public Chopstick(String position) {
		this.position = position;
		this.lock = new ReentrantLock();
	}

	@Override
	public String toString() {
		return "Chopstick [position=" + position + "]";
	}

	public Lock getLock() {
		return lock;
	}
}