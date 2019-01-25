package org.jk.plan.tests;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class GenericTypes {

	public static void main(String[] args) {
		List<Integer> intItems = new LinkedList<>();
		intItems.add(1);
		intItems.add(2);
		intItems.add(3);

		List<Long> longItems = new LinkedList<>();
		longItems.add(1L);
		longItems.add(2L);
		longItems.add(3L);

		List<Double> doubleItems = new LinkedList<>();
		doubleItems.add(1.0);
		doubleItems.add(2.3);
		doubleItems.add(3.4);

		System.out.println("done " + sum(intItems));
		System.out.println("done " + sum(longItems));
		System.out.println("done " + sum(doubleItems));
	}

	static <T extends Number> long sum(Collection<T> items) {
		Iterator<T> it = items.iterator();

		long sum = 0;
		while (it.hasNext()) {
			sum += it.next().longValue();
		}

		return sum;
	}
}
