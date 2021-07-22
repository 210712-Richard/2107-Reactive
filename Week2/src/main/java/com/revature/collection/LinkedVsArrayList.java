package com.revature.collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class LinkedVsArrayList {

	private static LinkedList<Integer> ll = new LinkedList<>();
	private static ArrayList<Integer> al = new ArrayList<>();
	public static void main(String[] args) {
		
		testInsertion();
		
		testRetrieval();
		
		testRandomRemoval();
		testRemovalFirstElement();
		testRemovalLastElement();
	};
	
	private static void testRemovalLastElement() {
		int length = 10_000;
		long start = System.currentTimeMillis();
		removeLast(length, ll);
		long finish = System.currentTimeMillis();
		System.out.println("Removing the last "+length+" items from LinkedList took "+(finish-start)+" ms");
		
		start = System.currentTimeMillis();
		removeLast(length, al);
		finish = System.currentTimeMillis();
		System.out.println("Removing the last "+length+" items from ArrayList took "+(finish-start)+" ms");
	}

	private static void removeLast(int length, List<Integer> list) {
		for(int i = 0; i< length; i++) {
			Integer test = list.remove(list.size()-1);
			test++;
		}
	}
	
	private static void testRemovalFirstElement() {
		int length = 10_000;
		long start = System.currentTimeMillis();
		removeFirst(length, ll);
		long finish = System.currentTimeMillis();
		System.out.println("Removing the first "+length+" items from LinkedList took "+(finish-start)+" ms");
		
		start = System.currentTimeMillis();
		removeFirst(length, al);
		finish = System.currentTimeMillis();
		System.out.println("Removing the first "+length+" items from ArrayList took "+(finish-start)+" ms");
	}

	private static void removeFirst(int length, List<Integer> list) {
		for(int i = 0; i< length; i++) {
			Integer test = list.remove(0);
			test++;
		}
	}

	private static void testInsertion() {
		int length = 1_000_000;
		long start = System.currentTimeMillis();
		insertMany(length, ll);
		long finish = System.currentTimeMillis();
		System.out.println("Inserting "+length+" items into LinkedList took "+(finish-start)+" ms");
		
		start = System.currentTimeMillis();
		insertMany(length, al);
		finish = System.currentTimeMillis();
		System.out.println("Inserting "+length+" items into ArrayList took "+(finish-start)+" ms");
		
	}

	private static void insertMany(int length, List<Integer> list) {
		for(int i = 0; i < length; i++) {
			list.add(i);
		}
	}

	private static void testRandomRemoval() {
		int length = 1_000;
		long start = System.currentTimeMillis();
		removeMany(length, ll);
		long finish = System.currentTimeMillis();
		System.out.println("Removing "+length+" items from LinkedList took "+(finish-start)+" ms");
		
		start = System.currentTimeMillis();
		removeMany(length, al);
		finish = System.currentTimeMillis();
		System.out.println("Removing "+length+" items from ArrayList took "+(finish-start)+" ms");
	}
	
	private static void removeMany(int length, List<Integer> list) {
		Random rand = new Random();
		for(int i = 0; i< length; i++) {
			Integer test = list.remove(rand.nextInt(list.size()));
			test++;
		}
	}
	

	private static void testRetrieval() {
		int length = 1_000;
		long start = System.currentTimeMillis();
		retrieveMany(length, ll);
		long finish = System.currentTimeMillis();
		System.out.println("Retrieving "+length+" items from LinkedList took "+(finish-start)+" ms");
		
		start = System.currentTimeMillis();
		retrieveMany(length, al);
		finish = System.currentTimeMillis();
		System.out.println("Retrieving "+length+" items from ArrayList took "+(finish-start)+" ms");
		
	}

	private static void retrieveMany(int length, List<Integer> list) {
		Random rand = new Random();
		for(int i = 0; i< length; i++) {
			Integer test = list.get(rand.nextInt(list.size()));
			test++;
		}
	}
}
