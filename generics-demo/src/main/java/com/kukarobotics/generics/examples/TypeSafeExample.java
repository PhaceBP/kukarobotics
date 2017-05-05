package com.kukarobotics.generics.examples;

import java.util.ArrayList;
import java.util.List;

public class TypeSafeExample {

	/*
	public static long sum(Number[] numbers) {

		long sum = 0;

		for (Number n : numbers) {
			sum += n.longValue();
		}

		return sum;
	}
	*/
	
	public static long sum(List<? extends Number> numbers) {

		long sum = 0;

		for (Number n : numbers) {
			sum += n.longValue();
		}

		return sum;
	}

	public static void main(String[] args) {

		
		Integer[] n1 = {1,3,4,5,10};
		Long[] n2 = {12L,123L, 2345L};
		
		//System.out.println(sum(n1));
		//System.out.println(sum(n2));
		
		
		Number[] n3 = n1;
		
		System.out.println(n3);
		
		
		//n3[0] = Math.PI; ArrayStoreException not integer array!
		
		
		List<Integer> n4 = new ArrayList<Integer>();
		
		System.out.println(sum(n4));
		
		List<? extends Number> numbers = new ArrayList<Number>();
		List<? super Number> numbers2 = new ArrayList<Number>();
		
		//numbers.add(1);
		
		Object n = numbers2.get(0);
 	}
}
