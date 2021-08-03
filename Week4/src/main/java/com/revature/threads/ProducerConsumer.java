package com.revature.threads;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class ProducerConsumer {
	//public static Queue<Object> data = new LinkedList<Object>();
	public static Queue<Object> data = new ConcurrentLinkedDeque<Object>();
	public static void main(String[] args) {
		new Thread(new Producer()).start();
		new Thread(new Consumer()).start();
	}
}


class Producer implements Runnable {
	public void run() {
		while(true) {
			try {
				Thread.sleep(4);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			ProducerConsumer.data.add(new Object());
			System.out.println("PRODUCER: Queue Size: "+ProducerConsumer.data.size());
		}
	}
}

class Consumer implements Runnable {
	public void run() {
		while(true) {
			try {
				Thread.sleep(4);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			while(ProducerConsumer.data.size() > 0) {
				System.out.println(ProducerConsumer.data.remove());
			}
		}
	}
}