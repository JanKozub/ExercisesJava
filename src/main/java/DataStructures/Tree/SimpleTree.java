package DataStructures.Tree;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class SimpleTree<T extends Comparable<T>> {
    public interface Visitor<T> {
        void visit(T value);
    }


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


    private void print(PrintStream out) {
        print(out, root, 0, "-");
    }


    private void print(PrintStream out, Node root, int depth, String prefix) {
        out.println(prefix + root);

        prefix += "-";
        depth += 1;
        if (root.left != null) {
            print(out, root.left, depth, prefix);
        }
        if (root.right != null) {
            print(out, root.right, depth, prefix);
        }
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

    public boolean contains2(T value){
        LinkedList<Node> list = new LinkedList<>();
        list.add(root);

        while (!list.isEmpty()){
            Node pos = list.removeFirst();
            if (value.compareTo(pos.value) == 0) {
                return true;
            }
            if (pos.left != null){
                list.add(pos.left);
            }
            if (pos.right != null){
                list.add(pos.right);
            }
        }
        return false;
    }

    public void visitBF(Visitor<T> visitor){
        LinkedList<Node> list = new LinkedList<>();
        list.add(root);

        while (!list.isEmpty()){
            Node pos = list.removeFirst();
            visitor.visit(pos.value);
            if (pos.left != null){
                list.add(pos.left);
            }
            if (pos.right != null){
                list.add(pos.right);
            }
        }
    }
    public void visitDF(Visitor<T> visitor){

        visitDF(root, visitor);
    }

    private void visitDF(Node root, Visitor<T> visitor){
        if (root == null) return;

        visitor.visit(root.value);
        visitDF(root.left, visitor);
        visitDF(root.right, visitor);
    }

    private void remove(){

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

        @Override
        public String toString(){
            return Objects.toString(value);
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

		//tree.print(System.out);
        tree.visitDF(v -> System.out.println(v));
	}
}