package com.revature.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;

public class ListExample {
	public static void main(String[] args) {

		// arrayListExample();
		// stephen();
		polymorphism();
	}

	private static void polymorphism() {
		LinkedList<Integer> link = new LinkedList<Integer>(); // Many things.
		List<Integer> list = link;
		Deque<Integer> deque = link; // Deque - Double Ended Queue.
		Queue<Integer> queue = link; // Queue - First in first out implementation. You put objects at the back, take
										// them from the front
		Collection<Integer> coll = link;
		Iterable<Integer> iter = link;

		// QUEUE
		System.out.println("QUEUE example");
		// Queues are First in, first out (FIFO)
		System.out.println("Queue: " + queue);

		// add vs offer
		queue.add(7);
		System.out.println("Queue: " + queue);
		queue.add(8);
		System.out.println("Queue: " + queue);
		queue.offer(5);
		System.out.println("Queue: " + queue);
		// difference: If it fails, add will throw an exception, offer will return false

		// element vs peek : Look at the first element in the queue
		System.out.println("Element: " + queue.element());
		System.out.println("Peek: " + queue.peek());
		// difference: If the queue is empty, element() throws an
		// exception, peek() returns null

		// remove vs poll
		System.out.println("Queue: " + queue);
		System.out.println("Remove: " + queue.remove());
		System.out.println("Queue: " + queue);

		System.out.println("Element: " + queue.element());
		System.out.println("Peek: " + queue.peek());
		System.out.println("Queue: " + queue);
		System.out.println("Remove: " + queue.poll());
		System.out.println("Queue: " + queue);

		System.out.println("Element: " + queue.element());
		System.out.println("Peek: " + queue.peek());
		System.out.println("Queue: " + queue);
		System.out.println("Remove: " + queue.poll());
		System.out.println("Queue: " + queue);
		
		//System.out.println("Element: "+queue.element()); // no such element exception
		System.out.println("Peek: "+queue.peek()); //null
		
		//System.out.println("Remove: "+queue.remove()); // no such element exception
		System.out.println("Poll: "+queue.poll()); //null
		
		deque.addFirst(1); // add
		deque.add(1);
		deque.addLast(1); // add to the end
		deque.offerFirst(1); // offer
		deque.offer(1);
		deque.offerLast(1); // offer to the end
		
		deque.pollFirst();
		deque.pollLast();
		deque.removeFirst();
		deque.removeLast();
		deque.peekFirst();
		deque.peekLast();
		deque.getFirst(); // element
		deque.getLast();
	}

	private static void stephen() {
		List<String> alphaList = new ArrayList<>(26);
		for (int i = 0; i < 26; i++) {
			char c = 'a';
			String s = "" + (char) (c + i);
			alphaList.add(s);
		}
		System.out.println(alphaList);
	}

	public static void arrayListExample() {
		List<Integer> list = new ArrayList<>(5);
		list.add(3);
		list.add(5);
		list.add(1);
		list.add(11);
		list.add(6);
		list.add(7);
		System.out.println(list);
		list.add(3, 5);
		System.out.println(list);

		Collection<Integer> coll = list;
		System.out.println(list);
		System.out.println(list.get(4));
		System.out.println(list.get((Integer) 3));
		System.out.println(list.get(list.get(3)));

		System.out.println(list.remove(3));
		System.out.println(list);
		// list.remove(Object) is overloaded
		System.out.println(list.remove((Integer) 3));
		System.out.println(list);

		Collections.sort(list);
		System.out.println(list);

		// because Collection is Iterable, we can use enhanced for loops.
		for (Integer i : list) {
			System.out.println(i);
		}

		// you cannot modify a collection that you are iterating over.
		for (Integer i : list) {
			// list.remove(i);
		}
		// If I remove something from the list, the indices of the remaining objects
		// might change

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
			if (list.get(i) == 5) {
				list.remove(i);
				i--; // we need to go back a space because we just shifted the indices.
			}
		}
		System.out.println(list);

		// Use the iterator directly
		/*
		 * In implementing the Iterable interface, an object has an Iterator. An
		 * Iterator is an object we can use to move through an Iterable.
		 * 
		 * If we're using the iterator directly, we can modify the collection
		 */
		Iterator<Integer> iterator = list.iterator();
		while (iterator.hasNext()) {
			Integer i = iterator.next();
			System.out.println(i);
			if (i == 7) {
				iterator.remove();
			}
		}
		System.out.println(list);

		// Lists actually have special ListIterator that other collections don't have
		// that can go backwards or forwards.
		for (ListIterator<Integer> listIterator = list.listIterator(list.size()); listIterator.hasPrevious();) {
			System.out.print(listIterator.previous() + " ");
		}
	}
}
