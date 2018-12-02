/**
 * Created by zzhou on 7/7/2017.
 */
public class KthSmallestElementinaBST_230 {
    private static int number = 0;
    private static int count = 0;

    public int kthSmallest(TreeNode root, int k) {
        count = k;
        helper(root);
        return number;
    }

    public void helper(TreeNode n) {
        if (n.left != null) helper(n.left);

        if (--count == 0) {
            number = n.val;
            return;
        }

        if (n.right != null) helper(n.right);
    }
}
