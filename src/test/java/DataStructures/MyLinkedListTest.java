package DataStructures;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MyLinkedListTest {
    @Test
    public void testEmptyListShouldHaveZeroSize() {
        // given
        MyLinkedList<Integer> list = new MyLinkedList<>();

        // when
        int size = list.size();

        // then
        assertThat(size).isEqualTo(0);
    }

    @Test
    public void testAddToEmptyList() {
        // given
        MyLinkedList<Integer> list = new MyLinkedList<>();

        // when
        list.add(5);

        // then
        assertThat(list.size()).isEqualTo(1);
    }

    @Test
    public void testAddFirst() {
        // given
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(3);

        // when
        list.add(0, 5);

        // then
        assertThat(list.get(0)).isEqualTo(5);
        assertThat(list.get(1)).isEqualTo(3);
        assertThat(list.size()).isEqualTo(2);
    }


    @Test
    public void testAddLast() {
        // given
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(3);

        // when
        list.add(list.size(), 5);

        // then
        assertThat(list.get(0)).isEqualTo(3);
        assertThat(list.get(1)).isEqualTo(5);
        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    public void testAddMid() {
        // given
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(3);
        list.add(6);

        // when
        list.add(1,5);

        // then
        assertThat(list.get(0)).isEqualTo(3);
        assertThat(list.get(1)).isEqualTo(5);
        assertThat(list.get(2)).isEqualTo(6);
        assertThat(list.size()).isEqualTo(3);
    }


    @Test
    public void testDeleteFirst() {
        // given
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(3);
        list.add(6);
        list.add(5);
        // when
        list.delete(0);

        // then
        assertThat(list.get(0)).isEqualTo(6);
        assertThat(list.get(1)).isEqualTo(5);
        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    public void testDeleteLast() {
        // given
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(3);
        list.add(6);
        list.add(5);
        // when
        list.delete(list.size()-1);

        // then
        assertThat(list.get(0)).isEqualTo(3);
        assertThat(list.get(1)).isEqualTo(6);
        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    public void testDeleteMid() {
        // given
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(3);
        list.add(6);
        list.add(5);
        list.add(9);
        // when
        list.delete(2);

        // then
        assertThat(list.get(0)).isEqualTo(3);
        assertThat(list.get(1)).isEqualTo(6);
        assertThat(list.get(2)).isEqualTo(9);
        assertThat(list.size()).isEqualTo(3);
    }

    @Test
    public void testToString() {
        // given
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(6);
        list.add(9);
        list.add(3);
        list.add(7);
        // when

        // then
        assertThat(list.toString()).isEqualTo("6, 9, 3, 7");
    }

    @Test
    public void testToStringOnEmptyList() {
        // given
        MyLinkedList<Integer> list = new MyLinkedList<>();
        // when

        // then
        assertThat(list.toString()).isEqualTo("");
    }

}