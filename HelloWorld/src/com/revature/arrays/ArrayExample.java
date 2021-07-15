package com.revature.arrays;

import java.util.Arrays;

public class ArrayExample {
	public static void main(String[] args) {
		/*
		 * An array is a block of memory that has been allocated to hold 'n' pieces of
		 * data.
		 * 
		 * An array of size 6 that holds integers: 6 * 4 bytes = 24 bytes of memory.
		 * 0x00. is the first thing. To get the fifth item, we need 4*4+Ox00 = 0x10 (16)
		 * 
		 * We search arrays at the speed of math. It is just as fast to get the 2nd
		 * index of an array as it is to get the 99th.
		 * 
		 * The COST of this is that arrays have to be a solid block of memory and
		 * therefore have a fixed size. The size of the array must be declared when the
		 * array is initialized, and because the memory around that block might get
		 * allocated to something else, we cannot expand it. If we need a bigger array
		 * later, we'll have to declare a new one.
		 */
		int[] arr = new int[7];
		Integer[] integerArray = new Integer[7];

		int[] arr1 = { 1, 3, 5, 6, 8 };
		int[] arr2 = new int[] { 1, 3, 5 };
		int arr3[] = new int[8];
		int arr4[][] = { { 1, 2, 3 }, { 4, 5 }, { 6, 7, 8, 9 } };
		System.out.println(arr4[1][0]);
		System.out.println(arr1[0]);
		// Arrays are objects.

		// problem: In order to have super fast retrieval and write, we have fixed size
		// This means that if we have an array of size 105, and want to add a 106th
		// item, we have
		// to create a whole new array for that. and copy all the old values over.
		// This is actually what ArrayList does.
		
		System.out.println(Arrays.toString(arr1));
		System.out.println(arrayToString(arr1));
		int[] arr5 = {5, 4, 2, 4, 32432, 232, 2, 3, 45};
		System.out.println(Arrays.toString(arr5));
		Arrays.sort(arr5);
		System.out.println(Arrays.toString(arr5));

	}
	public static String arrayToString(int[] arr) {
		String s = "[";
		for(int i = 0; i< arr.length; i++) {
			s += arr[i];
			if( (i+1) != arr.length) {
				s += ", ";
			}
		}
		s = s + "]";
		return s;
	}
}
