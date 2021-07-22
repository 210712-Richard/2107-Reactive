package com.revature.collection;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

public class MapExample {
	/*
	 * Map: Is a group of key-value pairs every value is identified by a key using
	 * that key results in retrieving that value
	 * 
	 * collections have add and remove maps have put and get.
	 * 
	 * Maps are part of the Collection API, but MAPS ARE NOT COLLECTIONS and they
	 * are not Iterable either
	 */
	public static void main(String[] args) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "Danny");
		map.put(2, "Quentin");
		map.put(3, "Fara");
		System.out.println(map);
		map.put(3, "Michael");
		map.put(-11, "Jasmine");
		System.out.println(map);
		map = new TreeMap<Integer, String>(map);
		System.out.println(map);

		// maps are not iterable, but we can get iterable things from a map
		for (Entry<Integer, String> e : map.entrySet()) {
			System.out.println(e);
		}
		for (Integer key : map.keySet()) {
			System.out.println(key);
			System.out.println(map.get(key));
		}
		for (String value : map.values()) {
			System.out.println(value);
		}

		// instanceof operator will tell us what kind of thing it is
		System.out.println("Is values a collection? " + (map.values() instanceof Collection));
		System.out.println("Is values a set? " + (map.values() instanceof Set));
		System.out.println("Is values a list? " + (map.values() instanceof List));
		System.out.println("Is values a queue? " + (map.values() instanceof Queue));
		System.out.println(map.getClass().getName());
		System.out.println(map.values().getClass().getName());
	}
}
