package lessons.graphs;

import java.util.*;
import java.util.stream.Collectors;

public class Graph1 {
    public interface Visitor {
        void visit(int from, int to, int weight);
    }

    private Map<Node, Collection<Edge>> nodes = new HashMap<>();
    private List<Edge> edges = new ArrayList<>();

    public Node addNode(int num) {
        Node node = new Node(num);
        if (!nodes.containsKey(node)) {
            nodes.put(node, new ArrayList<>());
        }
        return node;
    }

    public Edge addEdge(int from, int to, int weight) {
        Edge edge = new Edge(addNode(from), addNode(to), weight);
        int idx = edges.indexOf(edge);
        if (idx < 0) {
            edges.add(edge);
        } else
            edge = edges.get(idx);
        return edge;
    }

    public Node getNode(int num) {
        return nodes.keySet().stream()
                .filter(p -> p.number == num)
                .findAny()
                .orElseThrow(() -> new NoSuchElementException());
    }

    public Collection<Edge> getEdgesFrom(int start) {
        return Collections.unmodifiableCollection(nodes.get(new Node(start)));
    }

    public Collection<Edge> getEdgesTo(int end) {
        return edges.stream().filter(p -> p.endNode.equals(new Node(end))).collect(Collectors.toList());
    }

    public void visitDF(int start, Visitor visitor) {
        Set<Edge> edgesChecked = new HashSet<>();
        LinkedList<Node> currentPath = new LinkedList<>();

        currentPath.add(getNode(start));
        while(!currentPath.isEmpty()) {
            Node currentNode = currentPath.getLast();
            Collection<Edge> edges = getEdgesFrom(currentNode.number);
            Optional<Edge> nextEdge = edges.stream().filter(p -> !edgesChecked.contains(p)).findAny();
            if(nextEdge.isPresent()) {
                currentPath.add(nextEdge.get().endNode);
                visitor.visit(nextEdge.get().startNode.number, nextEdge.get().endNode.number, nextEdge.get().weight);
                edgesChecked.add(nextEdge.get());
            } else {
                currentPath.remove(nextEdge.get().startNode);
            }
        }

    }

    static class Edge {
        private final Node startNode;
        private final Node endNode;
        private final int weight;

        public Edge(Node startNode, Node endNode, int weight) {
            this.startNode = startNode;
            this.endNode = endNode;
            this.weight = weight;
        }

        public Node getStartNode() {
            return startNode;
        }

        public Node getEndNode() {
            return endNode;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return Objects.equals(startNode, edge.startNode) &&
                    Objects.equals(endNode, edge.endNode);
        }

        @Override
        public int hashCode() {
            return Objects.hash(startNode, endNode);
        }
    }

    static class Node {
        private final int number;

        public Node(int number) {
            this.number = number;
        }

        public int getNumber() {
            return number;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return number == node.number;
        }

        @Override
        public int hashCode() {
            return Objects.hash(number);
        }
    }
}
