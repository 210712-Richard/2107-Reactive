package com.revature.threads;

public class ThreadExample {
	public static void main(String[] args) {
		System.out.println("M1: This is the main thread: " + Thread.currentThread());
		System.out.println("Count of all active threads: " + Thread.activeCount());
		Thread currentThread = Thread.currentThread();
		System.out.println("State of main thread: " + currentThread.getState());

		// thread();
		// runnable();
		lambda();

		System.out.println("M1: This is the end of execution for the main thread.");
	}

	private static void lambda() {
		// What thread is this one?
		System.out.println(Thread.currentThread());
		System.out.println("Method call in Main thread.");

		System.out.println("Count of all active threads: " + Thread.activeCount());
		Runnable r = () -> {
			System.out.println("My thread: I'm a thread: " + Thread.currentThread());
			System.out.println("Count of all active threads: " + Thread.activeCount());
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("My thread: I'm a thread, but I'm dying now");
		};
		Thread t = new Thread(r);
		t.start();
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Method call in Main thread returning.");
	}

	private static void runnable() {
		// What thread is this one?
		System.out.println(Thread.currentThread());
		System.out.println("Method call in Main thread.");

		System.out.println("Count of all active threads: " + Thread.activeCount());
		Thread t = new Thread(new MyRunnable());
		t.start();
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Method call in Main thread returning.");
	}

	public static void thread() {
		// What thread is this one?
		System.out.println(Thread.currentThread());
		System.out.println("Method call in Main thread.");

		System.out.println("Count of all active threads: " + Thread.activeCount());
		Thread t = new MyThread();
		t.start();
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Method call in Main thread returning.");
	}
}

class MyRunnable implements Runnable {
	public void run() {
		System.out.println("My thread: I'm a thread: " + Thread.currentThread());
		System.out.println("Count of all active threads: " + Thread.activeCount());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("My thread: I'm a thread, but I'm dying now");
	}
}

class MyThread extends Thread {
	@Override
	public void run() {
		System.out.println("My thread: I'm a thread: " + Thread.currentThread());
		System.out.println("Count of all active threads: " + Thread.activeCount());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("My thread: I'm a thread, but I'm dying now");
	}

}