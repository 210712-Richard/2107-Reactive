package com.revature.sort;

import java.util.Arrays;

public class BubbleSort {
	
	// How do you sort something?
	/*
	 * 1. Bring in the list/array/whatever
	 * 2. Evaluate the first element compared to the second element?
	 * 		If the first element should go after the second element, we should swap them
	 * 3. Go to the next element and evaluate it with the subsequent element, swap if necessary
	 * 4. Continue until you reach the end of the list.
	 * 5. Return to Step 2 if any swaps were made.
	 * 6. It's sorted.
	 */
	public int[] sort(int[] arr) {
		boolean swapped = false;
		int counter = 0;
		arr = Arrays.copyOf(arr, arr.length);
		do {
			swapped = false;
			counter++;
			for(int i = 0; i < arr.length-1; i++) {
				if(arr[i] > arr[i+1]) {
					int temp = arr[i];
					arr[i] = arr[i+1];
					arr[i+1] = temp;
					swapped = true;
				}
			}
			
		} while(swapped);
		System.out.println("Sort performed "+counter+" sweeps of the array.");
		return arr;
	}
}
