package LinkedIn;

import java.util.LinkedList;
import java.util.Queue;

public class SerializeandDeserializeBST {
    private static final String spliter = ",";
    private static final String NN = "X";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if (root == null) return null;
        buildString(root, sb);
        return sb.toString();
    }

    private void buildString(TreeNode node, StringBuilder sb) {
        sb.append(node.val).append(spliter);
        if (node.left != null) buildString(node.left, sb);
        if (node.right != null) buildString(node.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null) return null;
        Queue<Integer> q = new LinkedList<>();
        String[] nodes = data.split(spliter);
        for (String node : nodes) {
            q.offer(Integer.parseInt(node));
        }
        return buildTree(q);
    }

    private TreeNode buildTree(Queue<Integer> nodes) {
        if (nodes.isEmpty()) return null;
        TreeNode root = new TreeNode(nodes.poll());
        Queue<Integer> samllerQueue = new LinkedList<>();
        while (!nodes.isEmpty() && nodes.peek() < root.val) {
            samllerQueue.offer(nodes.poll());
        }
        //smallerQueue : 3,2   storing elements smaller than 5 (root)
        root.left = buildTree(samllerQueue);
        //q: 6,7   storing elements bigger than 5 (root)
        root.right = buildTree(nodes);
        return root;
    }
}
