package Homework;

import org.junit.jupiter.api.Test;
import java.util.Random;

import static org.assertj.core.api.Assertions.*;


class ArraySorterTest {

    @Test
    void testSortSortsAlreadySortedArray() {
        // given
        int[] array = new int[]{1, 2, 3, 4, 5};

        // when
        ArraySorter.sort(array);

        // then
        for (int i = 0; i < array.length - 1; i++) {
            assertThat(array[i + 1]).isGreaterThanOrEqualTo(array[i]);
        }
    }

    @Test
    void testSortSortsInvertedArray() {
        // given
        int[] array = new int[]{5, 4, 3, 2, 1};

        // when
        ArraySorter.sort(array);

        // then
        for (int i = 0; i < array.length - 1; i++) {
            assertThat(array[i + 1]).isGreaterThanOrEqualTo(array[i]);
        }
    }

    @Test
    void testSortSortsEmptyArray() {
        // given
        int[] array = new int[]{};

        // when
        ArraySorter.sort(array);

        // then
    }

    @Test
    void testSortSortsRandomArray() {
        int[] array = {3, 4, 63, 4, 96, 34, 75, 6, 2, 434, 3, 56, 2, 3};

        // when
        ArraySorter.sort(array);

        // then
        for (int i = 0; i < array.length - 1; i++) {
            assertThat(array[i + 1]).isGreaterThanOrEqualTo(array[i]);
        }
    }

    @Test
    void testSortSortsRandomArray2() {
        Random r = new Random();
        int[] array = new int[r.nextInt(100) + 50];
        for (int i = 0; i > array.length; i++)
            array[i] = r.nextInt(1000) - 500;

        // when
        ArraySorter.sort(array);

        // then
        for (int i = 0; i < array.length - 1; i++) {
            assertThat(array[i + 1]).isGreaterThanOrEqualTo(array[i]);
        }
    }

    @Test
    void testSortFailsOnNullArray() {
        // given
        int[] array = null;

        // when
        ArraySorter.sort(array);

        // then
    }

    @Test
    void testSort2RandomArrayWithKolejnoscSetToFalse() {
        // given
        int[] array = {0, 1, 347, 6, 93, 17, 46, 0, 83, 27, 69, 4, 0, 6};

        // when
        ArraySorter.sort2(array,false);

        // then
        for (int i = 0; i < array.length - 1; i++) {
            assertThat(array[i + 1]).isGreaterThanOrEqualTo(array[i]);
        }
    }
    @Test
    void testSort2RandomArrayWithKolejnoscSetToTrue() {
        // given
        int[] array = {0, 1, 347, 6, 93, 17, 46, 0, 83, 27, 69, 4, 0, 6};

        // when
        ArraySorter.sort2(array,true);

        // then
        for (int i = 0; i < array.length - 1; i++) {
            assertThat(array[i + 1]).isLessThanOrEqualTo(array[i]);
        }
    }
    @Test
    void testSort2NullArrayWithKolejnoscSetToTrue() {
        // given
        int[] array = null;

        // when
        ArraySorter.sort2(array,true);

        // then
    }
    @Test
    void testSort2SortsAlreadySortedArray() {
        // given
        int[] array = new int[]{1, 2, 3, 4, 5};

        // when
        ArraySorter.sort2(array,true);

        // then
        for (int i = 0; i < array.length - 1; i++) {
            assertThat(array[i + 1]).isGreaterThanOrEqualTo(array[i]);
        }
    }

    @Test
    void testSort2SortsInvertedArray() {
        // given
        int[] array = new int[]{5, 4, 3, 2, 1};

        // when
        ArraySorter.sort2(array,true);

        // then
        for (int i = 0; i < array.length - 1; i++) {
            assertThat(array[i + 1]).isGreaterThanOrEqualTo(array[i]);
        }
    }
    @Test
    void testSort2For10MlnElements(){
        // given
        int[] array = new int[1000];
        for (int i = 0; i <1000; i++){
            array[i] = i;
        }
        // when
        ArraySorter.sort2(array,true);
        // then
        for (int i = 0; i < array.length - 1; i++) {
            assertThat(array[i + 1]).isGreaterThanOrEqualTo(array[i]);
        }
    }
    @Test
    void testSort3SortsAlreadySortedArray(){
        // given
        int[] array = {1,2,3,4,5,6,7,8,9};
        // when
        ArraySorter.sort3(array);
        // then
        for (int i = 0; i < array.length - 1; i++) {
            assertThat(array[i]).isLessThan(array[i+1]);
        }
    }
    @Test
    void testSort3SortsRandomArray(){
        // given
        int[] array = {75,67,27,465,32,6746,74,26,45,7,3};
        // when
        ArraySorter.sort3(array);
        // then
        for (int i = 0; i < array.length - 1; i++) {
            assertThat(array[i]).isLessThan(array[i+1]);
        }
    }
    @Test
    void testSort3SortsNullArray(){
        // given
        int[] array = null;
        // when
        ArraySorter.sort3(array);
        // then
    }
}