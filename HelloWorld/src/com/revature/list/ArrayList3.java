package com.revature.list;

import java.lang.reflect.Array;

public class ArrayList3<T> implements List<T> {
	T[] arr;
	int size = 10;
	int numOfItems = 0;
	Class<T> clazz;
	
	public ArrayList3(Class<T> clazz) {
		super();
		this.clazz = clazz;
		arr = (T[])Array.newInstance(clazz, size);
	}
	public ArrayList3(int size, Class<T> clazz) {
		this(clazz);
		this.size = size;
	}
	
	public void add(T obj, int i) {
		if(numOfItems < size-1) {
			arr[numOfItems++] = obj;
		}else{
			size += size;
			T[] nArr = (T[])Array.newInstance(clazz, size);
			
			
		}
		
	}
	@Override
	public T get(int index) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void add(T item) {
		// TODO Auto-generated method stub
		
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
