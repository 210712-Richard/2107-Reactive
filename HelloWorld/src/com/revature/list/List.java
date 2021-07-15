package com.revature.list;

public interface List<T> {
	T get(int index);
	void add(T item);
	void add(int index, T item);
	T remove(int index);
	T remove(T item);
}
