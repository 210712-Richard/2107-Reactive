package com.revature.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Streams {

	public static void main(String[] args) {
		/*
		 * The Stream API allows us to treat a group of items as an
		 * "infinite flow of objects" A Stream is just a sequence of elements that we
		 * can apply functions to.
		 * 
		 * There are two types of operations you can apply to a stream:
		 * 		Intermediate operation: modifies the elements in a stream but doesn't automatically get applied.
		 * 		Terminal operation: applies all intermediate operations before applying itself and ends the stream.
		 */
		//foreach();
		//intermediate();
		//reduce();
		//terminal();
		//filter();
		mapAndFlatMap();
	}
	private static void mapAndFlatMap() {
		String[] hamlet = {"To be, or not to be, that is the question:", 
				"Whether 'tis nobler in the mind to suffer",
				"The slings and arrows of outrageous fortune",
				"Or to take Arms against a Sea of troubles,",
				"And by opposing end them: to die, to sleep;"};
		
		Function<String, Stream<String>> f = (s) -> Stream.of(s.split(" "));
		// Map applies it's transform to the elements and returns whatever comes out.
		System.out.println("Map");
		System.out.println(Stream.of(hamlet).map(f).collect(Collectors.toList()));
		
		// Flat map tries to flatten the elements into a single stream.
		// If the transform would result in stream of streams, it creates a single stream out of it.
		System.out.println("Flat Map");
		System.out.println(Stream.of(hamlet).flatMap(f).collect(Collectors.toList()));
		
		
		System.out.println("Map into flatmap");
		System.out.println(Stream.of(hamlet)
				.map(f)
				.flatMap(s -> s)
				.collect(Collectors.toList()));
	}
	

	private static void filter() {
		Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		Predicate<Integer> evenChecker = (num) -> (0 == (num % 2));
		System.out.println(Arrays.toString(Stream.of(arr).filter(evenChecker).toArray()));
	}

	private static void terminal() {
		Integer[] arr = {1, 2, 3, 4, 5};
		System.out.println(Stream.of(arr).count());
		System.out.println(Stream.of(arr).max((a, b)-> a-b));
		System.out.println(Stream.of(arr).min((a, b)-> a-b));
		System.out.println(Stream.of(arr).findAny());
		System.out.println(Stream.of(arr).findFirst());
		System.out.println(Stream.of(arr).anyMatch((element)->element==3));
		System.out.println(Stream.of(arr).allMatch((element)->element==3));
		System.out.println(Stream.of(arr).noneMatch((element)->element==7));
		System.out.println("collecting as a list");
		System.out.println(Stream.of(arr).collect(Collectors.toList()));
		System.out.println(Stream.of(arr).collect(Collectors.toList()).getClass());
		
		System.out.println(fakeMax((a, b)-> a-b));
	}

	private static Optional<Integer> fakeMax(Comparator<Integer> comp) {
		Integer[] givenStream = {1, 2, 3, 4, 5};
		Integer max = givenStream[0];
		for(int i = 1; i < givenStream.length; i++) {
			if(comp.compare(max, givenStream[i]) < 0) {
				max = givenStream[i];
			}
		}
		
		return Optional.of(max);
	}

	private static void reduce() {
		Integer[] arr = { 1, 2, 3, 4, 5 };
		BinaryOperator<Integer> b = (Integer result, Integer element) -> {
			return result == null ? element : result + element;
		};
		System.out.println(Stream.of(arr).reduce(b).orElse(0));
	}
	private static void intermediate() {
		// The simplest intermediate function is peek();
		// peek() applies a Consumer to each element of the Stream.
		Integer[] arr = { 1, 2, 3, 4, 5 };

		Stream<Integer> stream = Stream.of(arr);
		stream = stream.peek((element)->{System.out.println(element);});
		
		stream.forEach((element)->{System.out.println(element);});
		
		// above, we operated on the stream individually, however, it is best practice to chain your operations onto the initial stream.
		
		Stream.of(arr)
				.peek((element) -> {System.out.println(element);})
				.forEach((element) -> {System.out.println(element);});
	}

	private static void foreach() {
		Integer[] arr = { 1, 2, 3, 4, 5 };
		
		// This stream represents a sequence of the values 1, 2, 3, 4, 5
		Stream<Integer> stream = Stream.of(arr);
		
		// We can apply functions to the stream by chaining operations to it.
		// The simplest operation we can perform is forEach();
		// forEach applies a Consumer to each element of the stream and closes the stream.
		stream.forEach((element)->{System.out.println(element);});
		
	}

}
