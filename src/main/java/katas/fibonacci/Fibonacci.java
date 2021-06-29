package katas.fibonacci;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {
    public static long fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fib(n - 1) + fib(n - 2);
    }

    static Map<Integer, Long> valueMap = new HashMap<>();
    static {
        valueMap.put(0, 0L);
        valueMap.put(1, 1L);
    }

    public static long fib2(int n) {
        if (valueMap.containsKey(n)) {
            return valueMap.get(n);
        } else {
            long val = fib2(n - 1) + fib2(n - 2);
            valueMap.put(n, val);
            return val;
        }
    }


    public static long fib2b(int n) {
        Map<Integer, Long> valueMap = new HashMap<>();
        {
            valueMap.put(0, 0L);
            valueMap.put(1, 1L);
        }

        return fib2binternalCall(n, valueMap);
    }

    private static long fib2binternalCall(int n, Map<Integer, Long> valueMap) {
        if (valueMap.containsKey(n)) {
            return valueMap.get(n);
        } else {
            long val = fib2binternalCall(n - 1, valueMap) + fib2binternalCall(n - 2, valueMap);
            valueMap.put(n, val);
            return val;
        }
    }
    

    public static long fib3(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        long num1 = 0;
        long num2 = 1;
        for (int i = 2; i <= n;i++){
            long result = num2 + num1;
            num1 = num2;
            num2 = result;
        }
        return num2;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(fib3(100));
        long end = System.currentTimeMillis();
        System.out.println("Took: " + (end - start) + "ms");
    }
}