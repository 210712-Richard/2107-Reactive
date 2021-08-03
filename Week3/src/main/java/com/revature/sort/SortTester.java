package com.revature.sort;

import java.util.Arrays;
import java.util.Random;

public class SortTester {
	public static void main(String[] args) {
		
		//basicTest();
		timeTest();
	}
	
	private static void timeTest() {
		int[] arr = new int[100_000];
		Random r = new Random();
		for(int i = 0; i< arr.length; i++) {
			arr[i] = r.nextInt(1000);
		}
		
		Long current_time = 0l;
		Long finish_time = 0l;
		
		BubbleSort bSorter = new BubbleSort();
		MergeSort mSorter = new MergeSort();
		
		
		int[] sortedArray = null;
		
		
		current_time = System.currentTimeMillis();
		sortedArray = bSorter.sort(arr);
		finish_time = System.currentTimeMillis();
		System.out.println("Bubble Sort took "+(double)(finish_time-current_time)/1000+" seconds");
		
		current_time = System.currentTimeMillis();
		sortedArray = mSorter.sort(arr);
		finish_time = System.currentTimeMillis();
		System.out.println("Merge Sort took "+(double)(finish_time-current_time)/1000+" seconds");
		System.out.println("Sort was called: "+mSorter.getRecursion()+ " times.");

	}

	public static void basicTest() {
		int[] arr = new int[100];
		Random r = new Random();
		for(int i = 0; i< arr.length; i++) {
			arr[i] = r.nextInt(1000);
		}
		System.out.println(Arrays.toString(arr));
		BubbleSort bSorter = new BubbleSort();
		int[] sortedArray = bSorter.sort(arr);
		System.out.println(Arrays.toString(sortedArray));
		
		MergeSort mSorter = new MergeSort();
		sortedArray = mSorter.sort(arr);
		System.out.println(Arrays.toString(sortedArray));
	}
}
