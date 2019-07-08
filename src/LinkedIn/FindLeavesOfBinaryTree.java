package LinkedIn;

import java.util.ArrayList;
import java.util.List;

public class FindLeavesOfBinaryTree {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        height(root, res);
        return res;
    }

    private int height(TreeNode root, List<List<Integer>> res) {
        if (root == null) return -1;
        int height = 1 + Math.max(height(root.left, res), height(root.right, res));
        if (height + 1 > res.size()) res.add(new ArrayList<>());
        res.get(height).add(root.val);
        return height;
    }
}
