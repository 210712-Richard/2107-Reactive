package com.revature.function;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.TreeSet;

import com.revature.beans.Associate;

public class ComparatorExample {
	public static void main(String[] args) {
		Comparator<Associate> comparator = (person1, person2) -> {
			if(person1.getBirthday().equals(person2.getBirthday())) {
				return person1.compareTo(person2);
			}
			return person1.getBirthday().compareTo(person2.getBirthday());
		};
		TreeSet<Associate> tree = new TreeSet<Associate>(comparator);
		tree.add(new Associate("Fara", 89, LocalDate.of(2020, 3, 2)));
		tree.add(new Associate("Danny", 94, LocalDate.of(2021, 5, 4)));
		tree.add(new Associate("Quentin", 70, LocalDate.of(2010, 6, 2)));
		tree.add(new Associate("Jaclyn", 70, LocalDate.of(2015, 8, 8)));
		System.out.println(tree);
	}
}
