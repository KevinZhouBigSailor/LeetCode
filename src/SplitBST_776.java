/**
 * Created by zzhou on 3/30/2018.
 */
public class SplitBST_776 {
    public TreeNode[] splitBST(TreeNode root, int V) {
        if(root == null)
            return new TreeNode[] {null, null};
        if(root.val <= V) {
            TreeNode[] bns = splitBST(root.right, V);
            root.right = bns[0];
            bns[0] = root;
            return bns;
        } else {
            TreeNode[] bns = splitBST(root.left, V);
            root.left = bns[1];
            bns[1] = root;
            return bns;
        }
    }
}
