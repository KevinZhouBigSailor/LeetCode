package Google.QuestionBank;

import java.util.*;

public class ZipHuffmanTrees_2962 {
    class Node {
        Node parent;
        int value = 0;
        Node left;
        Node right;
        boolean full = isLeaf();

        public Node(Node parent, int value) {
            this.parent = parent;
            this.value = value;
        }

        private boolean isLeaf() {
            return this.value != 0;
        }
    }

    public Node buildTree(Map<Integer, Integer> mappings) throws Exception {
        Node root = new Node(null, 0);
        for (Map.Entry<Integer, Integer> entry : sortByValue(mappings).entrySet()) {
            Node node = root;
            int depth = entry.getValue();
            for (int curr_depth = 0; curr_depth < depth - 1; curr_depth++) {
                if (node.left == null) {
                    node.left = new Node(node, 0);
                    node = node.left;
                } else if (!node.left.full) {
                    node = node.left;
                } else if (node.right == null) {
                    node.right = new Node(node, 0);
                    node = node.right;
                } else {
                    if (node.right.full) throw new Exception("Infeasible input");
                    node = node.right;
                }
            }
            if (node.left == null) {
                node.left = new Node(node, 0);
            } else {
                assert node.right == null;
                node.right = new Node(node, 0);
            }
            refresh_null(node);
        }

        return root;
    }

    // this works due to our construction order (where we only construct the right child once the left is full,
    // and only call this for a parent once left/right child is full).
    public void refresh_null(Node node) {
        node.full = node.isLeaf() || (node.left != null && node.right != null);
        if (node.full && node.parent != null) {
            refresh_null(node.parent);
        }
    }

    public Map<Integer, Integer> sortByValue(Map<Integer, Integer> mappings) {
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>();
        list.sort(Map.Entry.comparingByValue());
        Map<Integer, Integer> result = new LinkedHashMap<>();
        for (Map.Entry<Integer, Integer> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }

    class Solution2 {
        // When adding a new path, pad with 0's as the least significant bits to the desired length
        // Increment by 1 (at the current length) before moving to the next insertion
    }
}
