package com.revature.threads;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ThreadPlayground {
	private static long wordCount = 0;

	public static void main(String[] args) {
		// threadStates();
		// threadException();
		//oneThread();
		//wordCount = 0;
		//twoThreads();
		//wordCount = 0;
		//fourThreads();
		deadlock();
	}

	private static void deadlock() {
		// Deadlock
		Resource a = new Resource();
		Resource b = new Resource();
		
		Thread t1 = new Thread(()-> {
			System.out.println("Hello from r1");
			// synchronized block - Tell the thread it needs exclusive access to a resource
			synchronized(a) {
				System.out.println("I have claimed A");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized(b) {
					System.out.println("I have claimed B");
				}
			}
		});
		Thread t2 = new Thread(()-> {
			System.out.println("Hello from r2");
			// synchronized block - Tell the thread it needs exclusive access to a resource
			synchronized(b) {
				System.out.println("I have claimed B");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized(a) {
					System.out.println("I have claimed A");
				}
			}
		});
		t1.start();
		t2.start();
		while(true) {
			System.out.println("T1: "+t1.getState());
			System.out.println("T2: "+t2.getState());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void fourThreads() {
		double start = System.currentTimeMillis();

		Thread t1 = new Thread(() -> {
			updateWordCount("t8.3.1.shakespeare.txt");
		});
		Thread t2 = new Thread(() -> {
			updateWordCount("t8.3.2.shakespeare.txt");
		});
		Thread t3 = new Thread(() -> {
			updateWordCount("t8.3.3.shakespeare.txt");
		});
		Thread t4 = new Thread(() -> {
			updateWordCount("t8.3.4.shakespeare.txt");
		});
		t1.start();
		t2.start();
		t3.start();
		t4.start();

		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		double end = System.currentTimeMillis();
		System.out.println("There are " + wordCount + " words in Shakespeare "
				+ "(counting the copyright stuff) and it took " + (long) ((end - start)) + " ms");
	}

	private static void twoThreads() {
		double start = System.currentTimeMillis();

		Thread t1 = new Thread(() -> {
			updateWordCount("t8.1.shakespeare.txt");
		});
		Thread t2 = new Thread(() -> {
			updateWordCount("t8.2.shakespeare.txt");
		});
		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		double end = System.currentTimeMillis();
		System.out.println("There are " + wordCount + " words in Shakespeare "
				+ "(counting the copyright stuff) and it took " + (long) ((end - start)) + " ms");
	}

	private static void oneThread() {
		double start = System.currentTimeMillis();

		Thread t1 = new Thread(() -> {
			updateWordCount("t8.shakespeare.txt");
		});
		t1.start();

		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		double end = System.currentTimeMillis();
		System.out.println("There are " + wordCount + " words in Shakespeare "
				+ "(counting the copyright stuff) and it took " + (long) ((end - start)) + " ms");
	}

	private static void updateWordCount(String filename) {
		long words = 0;
		try (BufferedReader reader = new BufferedReader(
				new FileReader(ThreadPlayground.class.getClassLoader().getResource(filename).getFile()))) {
			// Above: We retrieved the file from the classloader instead of the filesystem.
			// This makes it easier to port the implementation to a web server.
			String line = "";
			while (null != (line = reader.readLine())) {
				String[] wordArray = line.split(" ");
				words = words + wordArray.length;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		setWordCount(wordCount + words);
	}
	
	private static synchronized void setWordCount(long words) {
		wordCount = words;
	}

	private static void threadException() {
		Thread t1 = new Thread(() -> {
			System.out.println("T1: Exception thread");
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			throw new RuntimeException("T1: I'm not ok");
		});
		Thread t2 = new Thread(() -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("T2: Am I ok?");
		});

		t2.start();
		t1.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("MT: Am I ok?");
	}

	public static void threadStates() {
		Runnable r1 = () -> {
			System.out.println("R1 is running.");
			try {
				Thread.sleep(50);
			} catch (Exception e) {
				e.printStackTrace();
			}
			method();
		};
		Thread t1 = new Thread(r1);
		Runnable r2 = () -> {
			System.out.println("R2 is running.");
			method();
			try {
				t1.join(); // join will wait until the thread is finished.
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("Haha, I've outlived T1!");
			try {
				Thread.sleep(50);
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
		Thread t2 = new Thread(r2);

		System.out.println("Thread 1: " + t1.getState());
		System.out.println("Thread 2: " + t2.getState());
		t1.start();
		t2.start();
		while (t1.isAlive() || t2.isAlive()) { // isAlive returns true if the thread is not TERMINATED or NEW
			System.out.println("Thread 1: " + t1.getState());
			System.out.println("Thread 2: " + t2.getState());
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("Thread 1: " + t1.getState());
		System.out.println("Thread 2: " + t2.getState());

	}

	private static synchronized void method() {
		System.out.println("SYNCHRONIZED BLOCK/METHOD: " + Thread.currentThread());
		// If a thread calls this method, no other thread is allowed to call this method
		// until this method returns. It has to WAIT
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Synchronized method ends: " + Thread.currentThread());
	}
}

class Resource {
	private int i = 10;
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
}

class MockThread {
	Runnable r;
	MockThread(){
		
	}
	MockThread(Runnable r) {
		this.r = r;
	}
	
	void start() {
		// vague thread stuff to make a thread
		
		// if the runnable is there, call that, otherwise call the run method
		if(r==null) {
			this.run();
		} else {
			r.run();
		}
	}
	void run() {
		
	}
}