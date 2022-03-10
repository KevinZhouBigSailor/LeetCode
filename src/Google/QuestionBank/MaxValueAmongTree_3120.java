package Google.QuestionBank;

import java.util.*;

public class MaxValueAmongTree_3120 {
    class Edge {
        public final int start;
        public final int end;

        public Edge(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    class Solver {
        private final Edge[] edges;

        public Solver(Edge[] edges) {
            this.edges = edges;
        }

        public void findMaxValues(Map<Integer, Set<Integer>> tree,
                                  int currentNode,
                                  int currentMax,
                                  Map<Integer, Integer> values) {
            Set<Integer> children = tree.get(currentNode);
            currentMax = Math.max(currentMax, currentNode);

            if (children == null) {
                values.put(currentNode, currentMax);
                return;
            }
            for (Integer child : children) {
                findMaxValues(tree, child, currentMax, values);
            }
        }

        public Map<Integer, Integer> getMaxValues() {
            // The first edge has the root
            int root = edges[0].start;

            // Create a tree for the traversal
            Map<Integer, Set<Integer>> tree = new HashMap<>();
            for (Edge edge : edges) {
                Set<Integer> children = tree.getOrDefault(edge.start, new HashSet<>());
                children.add(edge.end);
                tree.put(edge.start, children);
            }
            // Note: the leaf nodes will never start an edge, so they will not have an entry in the tree
            Map<Integer, Integer> maxValues = new HashMap<>();
            findMaxValues(tree, root, root, maxValues);
            return maxValues;
        }
    }

    public void main(String[] args) {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(4, 5));
        edges.add(new Edge(4, 3));
        edges.add(new Edge(5, 1));
        edges.add(new Edge(3, 2));
        edges.add(new Edge(3, 6));

        Solver solver = new Solver(edges.toArray(new Edge[edges.size()]));
        Map<Integer, Integer> maxValues = solver.getMaxValues();
        for (Map.Entry<Integer, Integer> entry : maxValues.entrySet()) {
            System.out.println(entry.getKey() + entry.getValue());
        }
    }
}
