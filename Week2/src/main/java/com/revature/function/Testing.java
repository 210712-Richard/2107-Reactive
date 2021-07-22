package com.revature.function;

import java.util.ArrayList;
import java.util.Collection;

public class Testing {

	public static void main(String[] args) {
		Collection<String> a = new ArrayList<>();
		a.add("Pretender");
		a.add("Fascimile");
		a.add("Joker");
		a.add("Imposter");
		a.forEach((str)->{
			System.out.println(str);
		});
	}

}
