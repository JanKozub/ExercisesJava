package BinarySearch;

import org.junit.jupiter.api.Test;

import BinarySearch.BinarySearch;

import static org.assertj.core.api.Assertions.assertThat;


class BinarySearchTest {

    BinarySearch binarySearch = new BinarySearch();
    @Test
    void findValueInOneElementArray() {
        //GIVEN
        int[] array = {1};
        //WHEN
        int index = binarySearch.findValueArray(array,1);
        //THEN
        assertThat(index).isEqualTo(0);
    }
    @Test
    void findValueInSmallArray(){
        //GIVEN
        int[] array = {1,2,6,7,8,9,10};
        //WHEN
        int index = binarySearch.findValueArray(array,6);
        //THEN
        assertThat(index).isEqualTo(2);
    }
    @Test
    void findValueInLargeArray(){
        //GIVEN
        int[] array = new int[1000];
        for (int i = 0; i < 1000; i++){
            array[i] = i;
        }
        //WHEN
        int index = binarySearch.findValueArray(array,600);
        //THEN
        assertThat(index).isEqualTo(600);
    }

    @Test
    void findPlaceOfValueInArray() {
        //GIVEN
        int[] array = {1, 2, 6, 7, 9, 10};
        //WHEN
        int index = binarySearch.findValueArray(array, 8);
        //THEN
        assertThat(index).isEqualTo(-5);
    }

    @Test
    void findPlaceOfValueInArrayAtTheBeginningOfAnArray() {
        //GIVEN
        int[] array = {1, 2, 6, 7, 9, 10};
        //WHEN
        int index = binarySearch.findValueArray(array, 0);
        //THEN
        assertThat(index).isEqualTo(-1);
    }
}