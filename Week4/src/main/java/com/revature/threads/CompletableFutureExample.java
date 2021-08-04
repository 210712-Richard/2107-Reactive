package com.revature.threads;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompletableFutureExample {
	public static Integer id = null;
	public static void main(String[] args) {
		noCompletableFuture();
		completableFuture();
		System.out.println("I'm doing other things now while I wait, knowing the id will eventually be updated.");
	}
	private static void completableFuture() {
		System.out.println("Main Thread executuing CompletableFuture");
		// CompletableFuture is an object that can be used to trigger another task to run once the value exists
		CompletableFuture<Integer> idFuture = new CompletableFuture<Integer>();
		CompletableFuture<Integer> nextFuture = idFuture.thenApply(result -> {
			id = result;
			return id;
		});
		
		nextFuture.thenRun(()-> {
			System.out.println("Runnable: I will only run once the other thread has finished making that id.");
		});
		ExecutorService es = Executors.newSingleThreadExecutor();
		es.submit(()->{
			System.out.println("Callable: trying to obtain an id.");
			Thread.sleep(3000);

			idFuture.complete(new Random().nextInt(100));
			return null;
		});
		es.shutdown();
		System.out.println("Main thread done executing CompletableFuture");
	}
	private static void noCompletableFuture() {
		System.out.println("Main Thread executing Future");
		ExecutorService es = Executors.newSingleThreadExecutor();
		Future<Integer> f = es.submit(()->{
			System.out.println("Callable: trying to obtain an id.");
			Thread.sleep(3000);
			return new Random().nextInt(100);
		});
		
		try {
			id = f.get();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		es.execute(()-> {
			System.out.println("Runnable: I will only run once the other thread has finished making that id.");
		});
		es.shutdown();
		System.out.println("Main thread done executing with futures");
	}
}
