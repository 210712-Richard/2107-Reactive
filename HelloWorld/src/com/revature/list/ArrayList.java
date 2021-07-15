package com.revature.list;

import java.util.Arrays;

public class ArrayList<T> implements List<T>{
	private Object[] array;
	public static final Integer INITIAL_CAPACITY = 5;
	private Integer capacity;
	private Integer size;
	
	public ArrayList() {
		this(ArrayList.INITIAL_CAPACITY);
	}
	
	public ArrayList(int capacity) {
		array = new Object[capacity];
		this.capacity = capacity;
		this.size = 0;
	}
	
	@Override
	public T get(int index) {
		if(index > size || index < 0) {
			return null;
		}
		return (T) this.array[index];
	}

	@Override
	public void add(T item) {
		resizeArray();
		array[size] = item;
		size++;
	}

	@Override
	public void add(int index, T item) {
		resizeArray();
		T temp = item;
		Integer currentIndex = index;
		while(temp != null) {
			T other = (T) this.array[currentIndex];
			this.array[currentIndex] = temp;
			currentIndex++;
			temp = other;
		}
		size++;
	}
	
	private void resizeArray() {
		if(size+1 >= capacity) {
			this.capacity = this.capacity * 2;
			this.array = Arrays.copyOf(this.array, this.capacity);
		}
	}

	@Override
	public T remove(int index) {
		if(index >= this.size || index < 0) {
			return null;
		}
		T data = (T) this.array[index];
		
		this.array[index] = null;
		
		for(int i = index; i < this.size; i++) {
			//System.out.println(this.size + " "+ i);
			this.array[i] = (i+1 >= size) ? null : this.array[i+1];
		}
		
		this.size--;
		return data;
	}

	@Override
	public T remove(T item) {
		for(int i = 0; i< this.size; i++) {
			if(this.array[i].equals(item)) {
				return this.remove(i);
			}
		}
		
		return null;
	}
	@Override
	public String toString() {
		return Arrays.toString(this.array);
	}
}
