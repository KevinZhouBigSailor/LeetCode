/**
 * Created by zzhou on 12/11/2017.
 */
public class BinaryTreeLongestConsecutiveSequenceII_549 {
    int maxval = 0;

    public int longestConsecutive(TreeNode root) {
        longestPath(root);
        return maxval;
    }

    public int[] longestPath(TreeNode root) {
        if (root == null) return new int[]{0, 0};
        int inr = 1, drc = 1;
        if (root.left != null) {
            int[] l = longestPath(root.left);
            if (root.val == root.left.val - 1) inr = l[0] + 1;
            else if (root.val == root.left.val + 1) drc = l[1] + 1;
        }

        if (root.right != null) {
            int[] r = longestPath(root.right);
            if (root.val == root.right.val - 1) inr = Math.max(inr, r[0] + 1);
            else if (root.val == root.right.val + 1) drc = Math.max(drc, r[1] + 1);
        }

        maxval = Math.max(maxval, inr + drc - 1);
        return new int[]{inr, drc};
    }
}
