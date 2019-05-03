package LinkedIn;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrintBinaryTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<List<String>> printTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        int height = getHeight(root);
        String[][] res = new String[height][(1 << height) - 1];
        for (String[] arr : res) {
            Arrays.fill(arr, "");
        }
        fill(res, root, 0, 0, res[0].length);
        List<List<String>> ans = new ArrayList<>();
        for (String[] arr : res) {
            ans.add(Arrays.asList(arr));
        }
        return ans;
    }

    public void fill(String[][] res, TreeNode root, int i, int l, int r) {
        if (root == null)
            return;
        res[i][(l + r) >> 1] = "" + root.val;
        fill(res, root.left, i + 1, l, (l + r) >> 1);
        fill(res, root.right, i + 1, (l + r + 1) >> 1, r);
    }

    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }
}
