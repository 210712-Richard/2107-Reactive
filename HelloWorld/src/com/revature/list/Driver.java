package com.revature.list;

public class Driver {

	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		System.out.println(list);
		list.add(5);
		System.out.println(list);
		list.add(6);
		list.add(7);
		list.add(3);
		System.out.println(list);
		
		System.out.println(list.get(4));
		System.out.println(list.get(3));
		System.out.println(list.get(2));
		list.add(4);
		list.add(4);
		list.add(57);
		System.out.println(list.get(6));
		System.out.println(list.get(0));
		
		list.add(0, 66);
		System.out.println(list);
		list.add(7, 33);
		list.add(3, 22);
		System.out.println(list);
		System.out.println(list.remove(4));
		System.out.println(list);
		System.out.println(list.remove(0));
		System.out.println(list.remove(8));
		System.out.println(list);
		System.out.println(list.remove(7));
		System.out.println(list);
		Integer myItem = list.get(2);
		System.out.println(myItem);
		System.out.println(list.remove(myItem));
		System.out.println(list);
	}

}
