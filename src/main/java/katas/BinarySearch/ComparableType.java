package katas.BinarySearch;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComparableType implements Comparable<ComparableType> {
    private int timestamp;
    private String name;

    public ComparableType(int timestamp, String name) {
        this.timestamp = timestamp;
        this.name = name;
    }

    @Override
    public int compareTo(@NotNull ComparableType o) {
        int order = Integer.compare(timestamp, o.timestamp);

        if(order == 0) {
            order = name.compareTo(o.name);
        }

        return order;
    }

    @Override
    public String toString() {
        return timestamp + ": " + name;
    }

    public static void main(String[] args) {
        ComparableType object1 = new ComparableType(1, "mno");
        ComparableType object2 = new ComparableType(1, "def");
        ComparableType object3 = new ComparableType(3, "jkl");
        ComparableType object4 = new ComparableType(3, "ghi");
        ComparableType object5 = new ComparableType(5, "abc");

        List<ComparableType> data = new ArrayList<>(List.of(
                object1,
                object2,
                object3,
                object4,
                object5));

        Collections.shuffle(data);
        Collections.sort(data);
        data.stream().forEach(System.out::println);

        //int order = object1.compareTo(object2);
    }

}
