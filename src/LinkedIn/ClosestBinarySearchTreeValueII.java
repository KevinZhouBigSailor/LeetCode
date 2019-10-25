package LinkedIn;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ClosestBinarySearchTreeValueII {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        Stack<TreeNode> smaller = new Stack<>();
        Stack<TreeNode> larger = new Stack<>();
        pushSmaller(root, target, smaller);
        pushLarger(root, target, larger);

        List<Integer> res = new ArrayList<>();
        TreeNode cur = null;
        while (res.size() < k) {
            if (smaller.isEmpty() || (!larger.isEmpty() && larger.peek().val - target < target - smaller.peek().val)) {
                cur = larger.pop();
                res.add(cur.val);
                pushLarger(cur.right, target, larger);
            } else {
                cur = smaller.pop();
                res.add(cur.val);
                pushSmaller(cur.left, target, smaller);
            }
        }
        return res;
    }

    private void pushSmaller(TreeNode node, double target, Stack<TreeNode> stack) {
        while (node != null) {
            if (node.val < target) {
                stack.push(node);
                node = node.right;
            } else {
                node = node.left;
            }
        }
    }

    private void pushLarger(TreeNode node, double target, Stack<TreeNode> stack) {
        while (node != null) {
            if (node.val >= target) {
                stack.push(node);
                node = node.left;
            } else {
                node = node.right;
            }
        }
    }
}
