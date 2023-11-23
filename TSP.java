import java.util.*;
class Node implements Comparable<Node> {
    int level, parent, cost;
    int[] path;
    public Node(int level, int parent, int cost, int[] path) {
        this.level = level;
        this.parent = parent;
        this.cost = cost;
        this.path = Arrays.copyOf(path, path.length);
    }
    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.cost, other.cost);
    }
}
public class TSP {
    static int[][] graph;
    static int numNodes;
    static void tspBranchAndBound() {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        int[] path = new int[numNodes + 1];
        Node root = new Node(0, -1, 0, path);
        root.path[0] = 0;
        priorityQueue.add(root);
        while (!priorityQueue.isEmpty()) {
            Node current = priorityQueue.poll();
            if (current.level == numNodes - 1) {
                current.path[numNodes] = current.path[0];
                printSolution(current);
            } else {
                for (int i = 0; i < numNodes; i++) {
                    if (isValid(current, i)) {
                        Node child = createNode(current, i);
                        priorityQueue.add(child);
                    }
                }
            }
        }
    }
    static boolean isValid(Node current, int i) {
        for (int j = 0; j <= current.level; j++) {
            if (current.path[j] == i)
                return false;
        }
        return true;
    }
    static Node createNode(Node parent, int i) {
        int level = parent.level + 1;
        int[] path = Arrays.copyOf(parent.path, parent.path.length);
        path[level] = i;
        return new Node(level, parent.level, parent.cost + graph[parent.path[parent.level]][i], path);
    }
    static void printSolution(Node node) {
        System.out.println("Optimal Path: " + Arrays.toString(node.path));
        System.out.println("Cost: " + node.cost);
    }
    public static void main(String[] args) {
        graph = new int[][]{{0, 10, 15, 20},{10, 0, 35, 25},{15, 35, 0, 30},{20, 25, 30, 0}};
        numNodes = graph.length;
        tspBranchAndBound();
    }
}