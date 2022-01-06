package Google;

import java.util.ArrayList;
import java.util.List;

public class FindLeavesOfBinaryTree_366 {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        height(root, res);
        return res;
    }

    private int height(TreeNode node, List<List<Integer>> res) {
        if (node == null) return -1;
        int height = 1 + Math.max(height(node.left, res), height(node.right, res));
        if (height + 1 > res.size()) res.add(new ArrayList<>());
        res.get(height).add(node.val);
        return height;
    }
}
