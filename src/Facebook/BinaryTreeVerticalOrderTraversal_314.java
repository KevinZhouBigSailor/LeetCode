package Facebook;

import java.util.*;

/**
 * Created by zzhou on 1/22/2018.
 */

// TODO: Check different solutions
public class BinaryTreeVerticalOrderTraversal_314 {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<>();
        Queue<TreeNodeCol> q = new LinkedList<>();

        q.offer(new TreeNodeCol(root, 0));

        while (!q.isEmpty()) {
            TreeNodeCol nodeCol = q.poll();
            if (!map.containsKey(nodeCol.col)) {
                map.put(nodeCol.col, new ArrayList<Integer>());
            }
            map.get(nodeCol.col).add(nodeCol.node.val);

            if (nodeCol.node.left != null) {
                q.add(new TreeNodeCol(nodeCol.node.left, nodeCol.col - 1));
            }

            if (nodeCol.node.right != null) {
                q.add(new TreeNodeCol(nodeCol.node.right, nodeCol.col + 1));
            }
        }

        for (Integer key : map.keySet()) {
            res.add(map.get(key));
        }
        return res;
    }

    class TreeNodeCol {
        TreeNode node;
        Integer col;

        TreeNodeCol(TreeNode node, Integer col) {
            this.node = node;
            this.col = col;
        }
    }
}
