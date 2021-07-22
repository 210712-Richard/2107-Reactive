package com.revature.collection;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import com.revature.beans.Associate;

public class SetExample {
	public static void main(String[] args) {
		//setExample();
		associates();
	}

	private static void associates() {
		Set<Associate> associates = new TreeSet<Associate>();
		associates.add(new Associate("Fara", 89, LocalDate.of(2020, 3, 2)));
		associates.add(new Associate("Fara", 89, LocalDate.of(2020, 3, 2)));
		associates.add(new Associate("Danny", 94, LocalDate.of(2021, 5, 4)));
		associates.add(new Associate("Quentin", 38, LocalDate.of(2010, 6, 2)));
		associates.add(new Associate("Jaclyn", 70, LocalDate.of(2015, 8, 8)));
		System.out.println(associates);
		
		Set<Associate> associates2 = new TreeSet<Associate>(new AssociateScoreComparator());
		associates2.add(new Associate("Fara", 89, LocalDate.of(2020, 3, 2)));
		associates2.add(new Associate("Fara", 89, LocalDate.of(2020, 3, 2)));
		associates2.add(new Associate("Danny", 94, LocalDate.of(2021, 5, 4)));
		associates2.add(new Associate("Quentin", 70, LocalDate.of(2010, 6, 2)));
		associates2.add(new Associate("Jaclyn", 70, LocalDate.of(2015, 8, 8)));
		System.out.println(associates2);
	}

	private static void setExample() {
		/*
		 * Set: A collection with no duplicates
		 * 		A Set does not keep order of insertion necessarily.
		 */
		Set<Integer> hashSet = new HashSet<Integer>();
		System.out.println(hashSet);
		hashSet.add(5);
		hashSet.add(6);
		System.out.println(hashSet);
		System.out.println(hashSet.add(4));
		System.out.println(hashSet);

		System.out.println(hashSet.add(4));
		System.out.println(hashSet);
		hashSet.add(11);
		hashSet.add(-11);
		System.out.println(hashSet);
		hashSet.add(999);
		System.out.println(hashSet);
		hashSet.add(10);
		hashSet.add(8);
		System.out.println(hashSet);
		
		Set<Integer> treeSet = new TreeSet<Integer>(hashSet);
		System.out.println(treeSet);
	}
}

class AssociateScoreComparator implements Comparator<Associate> {

	@Override
	public int compare(Associate o1, Associate o2) {
		// defines an alternative ordering to the "natural" order
		if(o1.getScore().equals(o2.getScore())) {
			return o1.compareTo(o2);
		}
		return o1.getScore().compareTo(o2.getScore());
	}
	
}
