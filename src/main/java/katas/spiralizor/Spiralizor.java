package katas.spiralizor;

import java.util.Arrays;

public class Spiralizor {
    public static void main(String[] args) {
        for (int i = 1; i < 20; i++) {
            System.out.println(i);
            printArray(spiralize(i));
        }
    }

    public static int[][] spiralize(int size) {

        int[][] array = new int[size][size];

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                array[y][x] = 1;
            }
        }

        for (int start = 1; start < (size / 2); start = start + 2) {
            for (int y = start; y < size - start; y++) {
                for (int x = start; x < size - start; x++) {
                    if (y == start || y == size - 1 - start)
                        array[y][x] = 0;
                    if (x == start || x == size - 1 - start)
                        array[y][x] = 0;
                }
            }
            array[start][start - 1] = 0;
            array[start + 1][start] = 1;
        }

        if (size % 2 == 0 && size > 2)
            array[size / 2][size / 2 - 1] = 0;

        if (size % 2 == 1 && size > 5 && array[size / 2][(size / 2) - 2] == 0) {
            array[size / 2][size / 2] = 0;
            array[size / 2][(size / 2) - 1] = 0;
        }

        return array;
    }

    public static int[][] spiralize2(int size) {

        int[][] array = new int[size][size];

        for (int y = 0; y < size; y++) {
            Arrays.fill(array[y], 1);
        }

        int s2 = size / 2;

        for (int start = 1; start < s2; start = start + 2) {
            makeCircle(array, start);

            array[start][start - 1] = 0;
            array[start + 1][start] = 1;
        }

        boolean isEvenSize = size % 2 == 0;

        if (isEvenSize && size > 2)
            array[s2][s2 - 1] = 0;

        if (!isEvenSize && size > 5 && array[s2][s2 - 2] == 0) {
            array[s2][s2] = 0;
            array[s2][s2 - 1] = 0;
        }

        return array;
    }

    private static void makeCircle(int[][] array, int startOffset) {
        int size = array.length;
        for (int y = startOffset; y < size - startOffset; y++) {
            for (int x = startOffset; x < size - startOffset; x++) {
                if (y == startOffset || y == size - 1 - startOffset)
                    array[y][x] = 0;
                if (x == startOffset || x == size - 1 - startOffset)
                    array[y][x] = 0;
            }
        }
    }

    private static void printArray(int[][] array) {
        for (int[] ints : array) {
            System.out.println(Arrays.toString(ints));
        }
    }

}

