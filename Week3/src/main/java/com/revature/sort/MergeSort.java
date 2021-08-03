package com.revature.sort;

import java.util.Arrays;

public class MergeSort {
	
	public int counter = 0;
	
	/*
	 * 1. If the list is of size one, we return.
	 * 2. Split the list in half.
	 * 3. For each list, we call merge sort.
	 * 4. We combine the two sorted lists as a sorted list.
	 */
	public int[] sort(int[] arr) {
		if(arr==null || arr.length < 2) {
			return arr;
		}
		int newSize = arr.length / 2;
		int[] a1 = Arrays.copyOfRange(arr, 0, newSize);
		int[] a2 = Arrays.copyOfRange(arr, newSize, arr.length);
		
		int[] sorted1 = sort(a1);
		int[] sorted2 = sort(a2);
		
		int[] sortedArray = new int[arr.length];
		
		for(int i= 0, j=0, k=0; i<sorted1.length || j<sorted2.length; k++) {
			if(i==sorted1.length) {
				// we are at the end of sorted1 and must pull from sorted2
				sortedArray[k] = sorted2[j];
				j++;
			} else {
				if(j==sorted2.length) {
					// we are at the end of sorted2 and must pull from sorted 1
					sortedArray[k] = sorted1[i];
					i++;
				} else {
					if(sorted1[i] < sorted2[j]) {
						// we need to pull from sorted 1
						sortedArray[k] = sorted1[i];
						i++;
					} else {
						// we need to pull from sorted 2
						sortedArray[k] = sorted2[j];
						j++;
					}
				}
			}
		}
		counter++;
		//System.out.println("Merge Sort has recursed "+ counter+++" times");
		return sortedArray;
	}
	
	public int getRecursion() {
		return counter;
	}
}
