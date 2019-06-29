package BinarySearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NonComparableType  {
    private int timestamp;
    private String name;

    public NonComparableType(int timestamp, String name) {
        this.timestamp = timestamp;
        this.name = name;
    }

    @Override
    public String toString() {
        return timestamp + ": " + name;
    }


    public static void main(String[] args) {
        Comparator<NonComparableType> myOrder = new Comparator<NonComparableType>() {
            @Override
            public int compare(NonComparableType o1, NonComparableType o2) {
                int order = Integer.compare(o1.timestamp, o2.timestamp);
                if (order == 0){
                    order = o1.name.compareTo(o2.name);
                }
                return order;
            }
        };

        NonComparableType object1 = new NonComparableType(1, "mno");
        NonComparableType object2 = new NonComparableType(1, "def");
        NonComparableType object3 = new NonComparableType(3, "jkl");
        NonComparableType object4 = new NonComparableType(3, "ghi");
        NonComparableType object5 = new NonComparableType(5, "abc");

        List<NonComparableType> data = new ArrayList<>(List.of(
                object1,
                object2,
                object3,
                object4,
                object5));

        Collections.shuffle(data);
        Collections.sort(data, myOrder);
        data.stream().forEach(System.out::println);

        //int order = object1.compareTo(object2);
    }

}
