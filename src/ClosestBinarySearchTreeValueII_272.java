import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ClosestBinarySearchTreeValueII_272 {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> res = new ArrayList<>();

        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();

        inorder(root, s1, target, false);
        inorder(root, s2, target, true);

        while (k-- > 0) {
            if (s1.isEmpty()) {
                res.add(s2.pop());
            } else if (s2.isEmpty()) {
                res.add(s1.pop());
            } else if (Math.abs(s1.peek() - target) > Math.abs(s2.peek() - target)) {
                res.add(s2.pop());
            } else {
                res.add(s1.pop());
            }
        }
        return res;
    }

    public void inorder(TreeNode root, Stack<Integer> stack, double target, boolean reverse) {
        if (root == null) return;
        inorder(reverse ? root.right : root.left, stack, target, reverse);
        if ((reverse && root.val <= target) || (!reverse && root.val > target)) return;
        stack.push(root.val);
        inorder(reverse ? root.left : root.right, stack, target, reverse);
    }
}
