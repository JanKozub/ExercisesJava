package katas.BinarySearch;

import java.util.Comparator;

public class BinarySearchComparator implements Comparator<Number> {

    public int findValueArrayComparator(int[] array, int value) {
        int index = -1;
        int left = 0;
        int right = array.length;
        int mid = 0;

        while (compare(right, left) >= 1) {
            mid = (left + right) / 2;
            int halfValue = array[mid];
            if (value == halfValue) {
                index = mid;
                break;
            } else if (value > halfValue) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (index == -1) index = 0 - mid;
        return index;
    }

    @Override
    public int compare(Number right, Number left) {
        return right.intValue() - left.intValue();
    }
}
