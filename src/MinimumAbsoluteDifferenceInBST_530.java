/**
 * Created by zzhou on 4/19/2017.
 * Input:

 1
 \
 3
 /
 2

 Output:
 1

 Explanation:
 The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).
 Note: There are at least two nodes in this BST.
 */
public class MinimumAbsoluteDifferenceInBST_530 {
    int min = Integer.MAX_VALUE;
    Integer prev = null;

    public int getMinimumDifference(TreeNode root) {
        if (root == null) return min;

        getMinimumDifference(root.left);

        if(prev != null) min = Math.min(min, Math.abs(root.val - prev));
        prev = root.val;

        getMinimumDifference(root.right);
        return min;
    }
}
