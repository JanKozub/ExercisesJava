package Homework;

public class ArraySorter {

    public static void sort(int[] array) {
        if (array == null)
            return;

        int length = array.length;
        for (int g = 0; g < length; g++) {
            for (int i = 0; i < array.length - 1 - g; i++) {
                if (array[i] >= array[i + 1]) {
                    int val = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = val;
                }
            }
        }
    }

    public static int[] sort2(int[] array, boolean kolejnosc) {
        if (array == null) return array;
        int[] newArray = new int[array.length];
        int smallestValue = array[0];
        int smallestIndex = 0;

        for (int i = 0; i < array.length; i++) {
            for (int g = 0; g < array.length; g++) {
                if (smallestValue < array[g]) {
                    smallestValue = array[g];
                    smallestIndex = g;
                }
            }

            array[smallestIndex] = 0;
            newArray[i] = smallestValue;
            smallestValue = 0;
        }

        if (kolejnosc) {
            int size = newArray.length;
            int[] array2 = new int[size];
            for (int o = 0; o < newArray.length; o++) {
                array2[o] = newArray[size - 1];
                size--;
            }
            return array2;
        }
        return newArray;
    }

    public static int[] sort3(int[] array) {
        if (array == null) return array;
        int num = 0;
        int val = array[0];
        int lastVal;
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j < array.length; j++) {
                if (val > array[j]) {
                    val = array[j];
                    num = j;
                }
            }
            lastVal = array[i];
            array[i] = val;
            array[num] = lastVal;
            val = array[array.length - 1];
            num = array.length - 1;
        }
        return array;
    }
}
