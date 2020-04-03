package katas.findOdd;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/*
Given an array, find the integer that appears an odd number of times.

There will always be only one integer that appears an odd number of times.
 */
public class FindOdd {

    public static void main(String[] args) {
        System.out.println(FindOdd.findIt(new int[]{1, 1, 1, 1, 1, 1, 10, 1, 1, 1, 1}));
    }

    public static int findIt(int[] a) {
        Map<Integer, Integer> numbers = new HashMap<>();

        for (int num : a) {
            if (!numbers.containsKey(num)) {
                numbers.put(num, 1);

            } else {
                int val = numbers.get(num) + 1;
                numbers.remove(num);
                numbers.put(num, val);
            }
        }
        System.out.println(numbers);

        AtomicInteger output = new AtomicInteger(a[0]);
        numbers.forEach((key, value) -> {
            if (value % 2 == 1 || value % 2 == -1)
                output.set(key);
        });
        return output.intValue();
    }
}
