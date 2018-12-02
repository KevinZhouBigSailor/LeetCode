import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by zzhou on 7/24/2017.
 */
public class InvertTree_226 {
    /*public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        final TreeNode left = root.left,
                right = root.right;

        root.left = invertTree(right);
        root.right = invertTree(left);
        return root;
    }*/

    public TreeNode invertTree(TreeNode root) {

        if (root == null) {
            return null;
        }

        final Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            final TreeNode node = queue.poll();
            final TreeNode left = node.left;
            node.left = node.right;
            node.right = left;

            if(node.left != null) {
                queue.offer(node.left);
            }
            if(node.right != null) {
                queue.offer(node.right);
            }
        }
        return root;
    }
}
