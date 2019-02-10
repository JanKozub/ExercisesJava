package Homework;

public class ArraySorter {

    public static void sort(int[] array) {
        int length = array.length;
        for (int g = 0; g < length; g++) {
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] < array[i + 1]) {

                } else {
                    int val = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = val;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] data = {-5,87,2,7,8,23,56,345,64,414,32,57,5};

        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i]);
            System.out.print(", ");
        }
        System.out.println("");
        System.out.println("Sorted: ");

        sort(data);

        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i]);
            System.out.print(", ");
        }
    }
}
