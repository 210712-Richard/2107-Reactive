package com.revature.list;

public class ArrayList2<T> implements List<T>{
	private T[] arr;
	private int size;
	private int maxSize;
	private static final int INITIAL_CAP = 1;
	
	public ArrayList2() {
		arr = (T[]) new Object[INITIAL_CAP];
		maxSize = INITIAL_CAP;
		size = 0;
	
	}
	@Override
	public T get(int index) {
	
		return arr[index];
	}

	@Override
	public void add(T item) {
		if(maxSize <= size) {
			T[] temp = (T[]) new Object[maxSize*2];
			for(int i = 0; i < size; i++) {
				temp[i] = arr[i];
				maxSize = maxSize * 2;
				}
			arr = temp;
		}
		for(int i = 0; i <= size; i++) {
			if(arr[i] == null) {
				arr[i] = item;
			}
		}
		size++;
		
	}
	@Override
	public void add(int index, T item) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public T remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public T remove(T item) {
		// TODO Auto-generated method stub
		return null;
	}
}