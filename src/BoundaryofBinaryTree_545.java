import java.util.LinkedList;
import java.util.List;

/**
 * Created by zzhou on 2/7/2018.
 */
public class BoundaryofBinaryTree_545 {
    /*
    Flag=0: Root Node.

    Flag=1: Left Boundary Node.

    Flag=2: Right Boundary Node.

    Flag=3: Others(Middle Node).
     */
    enum Flag {
        ROOT, LEFT, RIGHT, MIDDLE
    }

    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> left_boundary = new LinkedList<>();
        List<Integer> right_boundary = new LinkedList<>();
        List<Integer> leaves = new LinkedList<>();
        preorder(root, left_boundary, right_boundary, leaves, Flag.ROOT);

        left_boundary.addAll(leaves);
        left_boundary.addAll(right_boundary);
        return left_boundary;
    }

    public void preorder(TreeNode root, List<Integer> left_boundary, List<Integer> right_boundary, List<Integer> leaves, Flag flag) {
        if (root == null) return;

        //Root
        if (flag == Flag.ROOT || flag == Flag.LEFT) {
            left_boundary.add(root.val);
        } else if (flag == Flag.RIGHT) {
            right_boundary.add(0, root.val);
        } else if (root.left == null && root.right == null) {
            leaves.add(root.val);
        }

        //Left
        if (root.left != null) {
            preorder(root.left, left_boundary, right_boundary, leaves, childFlag(root, flag, true));
        }

        //Right
        if (root.right != null) {
            preorder(root.right, left_boundary, right_boundary, leaves, childFlag(root, flag, false));
        }
    }

    private Flag childFlag(TreeNode parent, Flag flag, boolean isLeftChild) {
        if (flag == Flag.ROOT) {
            return isLeftChild ? Flag.LEFT : Flag.RIGHT;
        }

        //Parent on left boundary, if exist left child, the right child should be a middle node
        if (flag == Flag.LEFT && parent.left != null && !isLeftChild) {
            return Flag.MIDDLE;
        }

        //Parent on right boundary, if exist right node, the left child should be a middle node.
        if (flag == Flag.RIGHT && isLeftChild && parent.right != null) {
            return Flag.MIDDLE;
        }

        return flag;
    }
}
