package DataStructures;

public class MyLinkedList<T> {
    private Node<T> tail;
    private int size;

    public MyLinkedList() {
        tail = null;
        size = 0;
    }

    public void add(T value) {
        add(size(), value);
    }

    public void add(int index, T value) {
        Node<T> newNode = new Node<>();
        newNode.value = value;

        if (tail == null) {
            if (index != 0) throw new IndexOutOfBoundsException();
            tail = newNode;
            size++;
            return;
        }

        if(index == size()) {
            Node<T> tmp = getNodeAt(index - 1);
            newNode.prev = tmp;
            tmp.next = newNode;
            size++;
            return;
        }

        Node<T> tmp = getNodeAt(index);
        newNode.prev = tmp.prev;
        newNode.next = tmp;
        tmp.prev = newNode;
        if (newNode.prev != null) {
            newNode.prev.next = newNode;
        }

        if(index == 0)
            tail = newNode;
        size++;
    }

    public T delete(int index) {
        if (tail == null) {
            return null;
        }

        Node<T> tmp = getNodeAt(index);

        Node<T> next = tmp.next;
        if (next != null) {
            next.prev = tmp.prev;
        }

        Node<T> prev = tmp.prev;
        if (prev != null) {
            prev.next = tmp.next;
        } else {
            tail = next;
        }
        size--;
        return tmp.value;
    }

    public T get(int index) {
        if (tail == null) {
            return null;
        }

        Node<T> tmp = getNodeAt(index);
        return tmp.value;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            if (i != 0) {
                sb.append(", ");
            }

            sb.append(get(i));
        }
        return sb.toString();
    }


    private Node<T> getNodeAt(int index) {
        Node<T> tmp = tail;

        int counter = 0;
        while(tmp != null) {
            if(counter == index)
                return tmp;

            tmp = tmp.next;
            counter++;
        }

        throw  new IndexOutOfBoundsException();
    }


    private class Node<T> {
        private Node<T> prev;
        private T value;
        private Node<T> next;
    }
}