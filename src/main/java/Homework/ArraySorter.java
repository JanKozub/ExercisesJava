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

    public static void sort2(int[] array, boolean kolejnosc) {
        if (array == null)return;
        int[] newArray = new int[array.length];
        int val = array[0];
        int num = 0;
        for (int i = 0; i < array.length; i++) {
            for (int g = 0; g < array.length; g++) {
                if (val < array[g]) {
                    val = array[g];
                    num = g;
                }
            }
            array[num] = 0;
            newArray[i] = val;
            val = 0;
        }
        if (kolejnosc) {
            int size = newArray.length;
            int[] array2 = new int[size];
            for (int o = 0; o < newArray.length; o++) {
                array2[o] = newArray[size - 1];
                size--;
            }
        }
    }
}
