package lessons.LambdaExpresion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Predicate;

public class Lambda2 {
    public static interface MyRunnable {
        void runme();
    }

    public static void run10times(Runnable r) {
        for (int i = 0; i < 10; i++) {
            System.out.println("Running");
            r.run();
        }
    }

    public static void run10times(MyRunnable r) {
        for (int i = 0; i < 10; i++) {
            System.out.println("RunningMe");
            r.runme();
        }
    }

    public static void run10times(Callable<?> r) {
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println("Calling");
                r.call();
            } catch (Exception e) {
            }
        }
    }

    public static void main(String[] args) {
        //MyRunnable r =  () -> printMe();
        //run10times(r);

        List<String> l = new ArrayList<>();
        l.add("value1");
        l.add("value2");
        l.add("value12");
        l.add("value3");
        l.add("value11");
        l.add("value12");
        l.add("value13");
        System.out.println(l);
        l.removeIf(value -> value.endsWith("2"));
        System.out.println(l);

    }


    public static void printMe() {
        System.out.println("Hello world!");
    }
}
