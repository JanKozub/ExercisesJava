package DataStructures.Tree;

import java.io.PrintStream;

public class SimpleTree<T extends Comparable<T>> {

    private Node root;

    public SimpleTree() {
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void add(T value) {
        if (value == null) {
            throw new NullPointerException();
        }

        if (isEmpty()) {
            root = new Node(value);
        } else {
            add(root, value);
        }
    }

    public int depth() {
        return depth(root);
    }

    public boolean contains(T value) {
        return contains(root, value) != null;
    }

	public void print(PrintStream out) {
	}


    private void add(Node root, T value) {
        int order = value.compareTo(root.value); // <0, ==0, >0
        if (order < 0) {
            if (root.left == null) {
                root.left = new Node(value);
            } else {
                add(root.left, value);
            }
        } else if (order > 0) {
            if (root.right == null) {
                root.right = new Node(value);
            } else {
                add(root.right, value);
            }
        }
    }

    private int depth(Node root) {
        if (root == null) {
            return 0;
        }

        int lDepth = depth(root.left);
        int rDepth = depth(root.right);
        int maxDepth = Math.max(lDepth, rDepth);

        return 1 + maxDepth;
    }

    private Node contains(Node root, T value) {
        if (root == null)
            return root;

        int order = value.compareTo(root.value);
        if (order < 0) {
            return contains(root.left, value);
        } else if (order > 0) {
            return contains(root.right, value);
        } else {
            return root;
        }
    }

    private class Node {
        final T value;
        Node left;
        Node right;

        public Node(T value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }

    }



    public static void main(String[] args) {
		SimpleTree<Integer> tree = new SimpleTree<>();

		tree.add(5);
		tree.add(3);
		tree.add(2);
		tree.add(1);
		tree.add(4);

		tree.add(7);
		tree.add(8);
		tree.add(9);

		tree.print(System.out);
	}
}