package org.jk.plan.tests;

import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class LambdaExpressions {

	public static void main(String[] args) throws Exception {
		Runnable r = () -> System.out.println("I am running");
		Callable<String> c = () -> {
			return "I am running";
		};

		Function<Integer, String> f = v -> "[" + v + "]";
		BiFunction<Integer, Double, String> bf = (v1, v2) -> "[" + v1 + ";" + v2 + "]";
		BinaryOperator<Integer> sum = (v1, v2) -> v1 + v2;
		UnaryOperator<String> reverse = LambdaExpressions::myReverse;

		r.run();
		System.out.println(c.call());
		System.out.println(f.apply(10));
		System.out.println(bf.apply(10, 20.0));
		System.out.println(sum.apply(10, 20));
		System.out.println(reverse.apply("alamakota"));
	}


	public static String myReverse(String s) {
		StringBuilder sb = new StringBuilder();
		for(int i = s.length() - 1; i >= 0; i--) {
			sb.append(s.charAt(i));
		}
		return sb.toString();
	}

}
