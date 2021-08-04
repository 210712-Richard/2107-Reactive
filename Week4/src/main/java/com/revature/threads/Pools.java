package com.revature.threads;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Pools {
	public static Long wordCount = 0l;
	public static void main(String[] args) {
		threadPool();
		//ThreadPlayground.fourThreads();
		whateverIWantHasNoEffectOnTheFunctionWeMakeInsideOfHere();
		invokeAll();
	}

	private static void invokeAll() {
		ExecutorService pool = Executors.newFixedThreadPool(4);
		Long myWords = 0l;
		long start = System.currentTimeMillis();
		List<Callable<Long>> callables = new ArrayList<Callable<Long>>();
		callables.add(()-> {
			return getWordCount("t8.3.1.shakespeare.txt");
		});
		callables.add(()-> {
			return getWordCount("t8.3.2.shakespeare.txt");
		});
		callables.add(()-> {
			return getWordCount("t8.3.3.shakespeare.txt");
		});
		callables.add(()-> {
			return getWordCount("t8.3.4.shakespeare.txt");
		});
		List<Future<Long>> results = null;
		try {
			results = pool.invokeAll(callables);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		try {
			for(Future<Long> result : results) {
				myWords += result.get();
			}
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		
		System.out.println("Executor: " + myWords);
		System.out.println("Executor: " + (end - start)+ " ms");
		pool.shutdown();
	}

	private static void whateverIWantHasNoEffectOnTheFunctionWeMakeInsideOfHere() {
		// Callable is like Runnable except that a Callable has a return type where Runnable does not.
		Callable<Long> cImplicitReturn = () -> getWordCount("");
		Callable<Long> cExplicitReturn = () -> {
			return getWordCount("");
		};
		//cImplicitReturn.call();
		ExecutorService pool = Executors.newFixedThreadPool(4);
		Long myWords = 0l;
		long start = System.currentTimeMillis();
		// pass Callables to the pool by using the submit method.
		// submit returns an object called a Future.
		Future<Long> f1 = pool.submit(()-> {
			return getWordCount("t8.3.1.shakespeare.txt");
		});
		Future<Long> f2 = pool.submit(()-> {
			return getWordCount("t8.3.2.shakespeare.txt");
		});
		Future<Long> f3 = pool.submit(()-> {
			return getWordCount("t8.3.3.shakespeare.txt");
		});
		Future<Long> f4 = pool.submit(()-> {
			return getWordCount("t8.3.4.shakespeare.txt");
		});
		// A Future is an object that will eventually hold a value.
		// Futures have get(), get(timeout), isDone
		// get() will block until the value exists.
		// get(timeout) will block until the value exists of the timeout is over whichever comes first
		// isDone() will return true if the value exists.
		
		try {
			myWords = f1.get().longValue() + f2.get().longValue() + f3.get().longValue() +f4.get().longValue();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		
		System.out.println("Executor: " + myWords);
		System.out.println("Executor: " + (end - start)+ " ms");
		pool.shutdown();
	}

	private static void threadPool() {
		wordCount = 0l;
		// ExecutorService represents a thread pool, a group of threads we can use to accomplish a task.
		// It is an object that manages threads for you.
		ExecutorService pool = Executors.newFixedThreadPool(4);

		long start = System.currentTimeMillis();
		// pass tasks to our pool
		pool.execute(()-> {
			updateWordCount("t8.3.1.shakespeare.txt");
		});
		pool.execute(()-> {
			updateWordCount("t8.3.2.shakespeare.txt");
		});
		pool.execute(()-> {
			updateWordCount("t8.3.3.shakespeare.txt");
		});
		pool.execute(()-> {
			updateWordCount("t8.3.4.shakespeare.txt");
		});
		
		pool.shutdown(); // prevent the pool from accepting new tasks.
		// closes the pool when the tasks are complete.
		// pool.shutdownNow() // kills current tasks.
		
		try {
			while(!pool.awaitTermination(5, TimeUnit.SECONDS)) {
				System.out.println("Still waiting");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		
		System.out.println("Executor: " + wordCount);
		System.out.println("Executor: " + (end - start)+ " ms");
	}
	
	private static long getWordCount(String filename) {
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
		return words;
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
}
