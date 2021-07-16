package com.revature.list;

import java.util.Arrays;

public class ArrayList1<T> implements List<T> {
	private Integer size;
	private Integer place;
	private Object[] array;
	
	public ArrayList1() {
		this.size = 5;
		this.place= 0;
		this.array = new Object[this.size];
	}
	public ArrayList1(Integer preferredSize) {
		this.size = preferredSize;
		this.array = new Object[this.size];
	}
	public ArrayList1(T...item) {
		this.size = item.length * 2;
		this.array = Arrays.copyOf(item, this.size); 
		this.place = item.length;
	}

	@Override
	public T get(int index) {
		if(this.size==0 || this.size<0 || this.size<index){
			return null;
		}
		return (T) array[index];
	}
	

	@Override
	public void add(T item) {
//		if(this.place == 0) {
//			array[this.place] = item;
//			this.place++;
//		}
		if(this.place < this.size-1) {
			array[this.place] = item;
			this.place++;
			return;
		}
		this.size = this.size *2;
		this.array = Arrays.copyOf(array, this.size);
		array[this.place] = item;
		this.place++;
		
	}
	public void add(int index, T item) {
		if(this.place==index) {
			add(item);
			return;
		}
		for(int i=this.place; i>index; i--) {
			array[i] = array[i-1];
		}
		array[index] = item;
		this.place++;
		
	}

	@Override
	public T remove(int index) {
		T item = get(index);
		System.out.println(item);
		if((this.place-1) == index) {
			array[index] = null;
			this.place--;
			return item;
		}
		for(int i= index; i <this.place; i++) {
			array[i] = array[i+1];
		}
		array[this.place-1] = null;
		this.place --;
		return item;
	}

	@Override
	public T remove(T item) {
		//traverse the list looking for item
		for(int i =0; i<this.place; i++) {
			if(array[i]==item) {
				remove(i);
				return item;
			}
			return null;
		}
		return null;
	}
	@Override
	public String toString() {
		String print = "[ ";
		for(int i=0; i<this.place; i++) {
			print += array[i];
			if(i<this.place-1) {
				print += ", ";
			}
		}
		print += " ]";
		return print;
	}

}
