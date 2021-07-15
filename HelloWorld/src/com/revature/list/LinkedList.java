package com.revature.list;

import com.revature.auto.Automobile;

public class LinkedList<T> implements List<T>{
	private Node<T> head;
	private Node<T> tail;
	private Integer size;
	
	public LinkedList() {
		super();
		this.size = 0;
	}
	// @Override is an annotation.
	// Annotation: A method you can use to modify your classes, methods, and fields in some manner, usually through Reflection
	// @Override lets the compile know that what follows is meant to be an overridden method.
	@Override
	public T get(int index) {
		if(index >= this.size || index < 0) {
			// if there are only 3 things in the list, and we ask for index 5, that doesn't exist.
			return null;
		}
		if(index < this.size/2) {
			Node<T> currentNode = this.head;
			for(int i= 0; i< index; i++) {
				currentNode = currentNode.getNext();
			}
			return currentNode.getData();
		}
		Node<T> currentNode = this.tail;
		for(int i = this.size-1; i > index; i--) {
			currentNode = currentNode.getPrevious();
		}
		return currentNode.getData();
	}

	/*
	 * Add the item to the end of the list
	 */
	@Override
	public void add(T item) {
		Node<T> newNode = new Node<T>(item);
		if(this.size == 0) {
			this.head = newNode;
			this.tail = newNode;
			this.size++;
			return;
		}
		newNode.setPrevious(this.tail); // the old tail becomes our previous
		this.tail.setNext(newNode); // the new node becomes the next for the old tail
		this.tail = newNode; // The new node becomes the new tail.
		this.size++;
	}

	@Override
	public void add(int index, T item) {
		if(index == this.size-1) {
			this.add(item);
			return;
		}
		Node<T> previous = getNodeAtIndex(index-1);
		Node<T> next = getNodeAtIndex(index);
		Node<T> newNode = new Node<T>(item, next, previous);
		if(next != null) {
			next.setPrevious(newNode);
		}
		if(previous != null) {
			previous.setNext(newNode);
		}
		this.size++;
		if(index == 0) {
			this.head = newNode;
		}
	}
	
	private Node<T> getNodeAtIndex(int index) {
		if(index >= this.size || index < 0) {
			// if there are only 3 things in the list, and we ask for index 5, that doesn't exist.
			return null;
		}
		if(index < this.size/2) {
			Node<T> currentNode = this.head;
			for(int i= 0; i< index; i++) {
				currentNode = currentNode.getNext();
			}
			return currentNode;
		}
		Node<T> currentNode = this.tail;
		for(int i = this.size-1; i > index; i--) {
			currentNode = currentNode.getPrevious();
		}
		return currentNode;
	}

	@Override
	public T remove(int index) {
		if(index < 0 || index >= this.size) {
			return null;
		}
		T data = this.get(index);
		
		
		if(index == 0) {
			this.head = getNodeAtIndex(1);
			this.head.setPrevious(null);
			this.size--;
			return data;
		}
		
		if(index == this.size-1) {
			this.tail = getNodeAtIndex(index-1);
			this.tail.setNext(null);
			this.size--;
			return data;
		}
		
		Node<T> next = getNodeAtIndex(index+1);
		Node<T> previous = getNodeAtIndex(index-1);
		
		next.setPrevious(previous);
		previous.setNext(next);
		this.size--;
		
		return data;
	}

	@Override
	public T remove(T item) {
		Node<T> currentNode = this.head;
		for(int i = 0; i < this.size; i++) {
			if(currentNode.getData().equals(item)) {
				return this.remove(i);
			}
			currentNode = currentNode.getNext();
		}
		return null;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((head == null) ? 0 : head.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		result = prime * result + ((tail == null) ? 0 : tail.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LinkedList other = (LinkedList) obj;
		if (head == null) {
			if (other.head != null)
				return false;
		} else if (!head.equals(other.head))
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		if (tail == null) {
			if (other.tail != null)
				return false;
		} else if (!tail.equals(other.tail))
			return false;
		return true;
	}
	@Override
	public String toString() {
		String list = "[";
		Node<T> currentNode = this.head;
		for(int i = 0; i < this.size; i++) {
			list += currentNode.getData();
			if(i != this.size-1)
				list+=", ";
			currentNode = currentNode.getNext();
		}
		list += "]";
		return list;
	}
	
	
}

class Node<T> {
	private T data;
	private Node<T> next;
	private Node<T> previous;
	
	public Node() {
		super();
	}
	public Node(T data) {
		super();
		this.data = data;
	}
	public Node(T data, Node next, Node previous) {
		super();
		this.data = data;
		this.next = next;
		this.previous = previous;
	}
	
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public Node getNext() {
		return next;
	}
	public void setNext(Node next) {
		this.next = next;
	}
	public Node getPrevious() {
		return previous;
	}
	public void setPrevious(Node previous) {
		this.previous = previous;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((next == null) ? 0 : next.hashCode());
		result = prime * result + ((previous == null) ? 0 : previous.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (next == null) {
			if (other.next != null)
				return false;
		} else if (!next.equals(other.next))
			return false;
		if (previous == null) {
			if (other.previous != null)
				return false;
		} else if (!previous.equals(other.previous))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Node [data=" + data + ", next=" + next + ", previous=" + previous + "]";
	}
}