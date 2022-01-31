package Facebook;

import java.util.*;

/**
 * Created by zzhou on 2/7/2018.
 */
public class BinaryTreeRightSideView_199 {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        rightView(root, result, 0);
        return result;
    }

    public void rightView(TreeNode curr, List<Integer> result, int curDepth) {
        if (curr == null) return;
        if (curDepth == result.size()) {
            result.add(curr.val);
        }

        rightView(curr.right, result, curDepth + 1);
        rightView(curr.left, result, curDepth + 1);
    }

    class Solution {
        public List<Integer> rightSideView(TreeNode root) {
            if (root == null) return new ArrayList<Integer>();

            ArrayDeque<TreeNode> queue = new ArrayDeque() {{
                offer(root);
            }};
            List<Integer> rightside = new ArrayList();

            while (!queue.isEmpty()) {
                int levelLength = queue.size();

                for (int i = 0; i < levelLength; ++i) {
                    TreeNode node = queue.poll();
                    // if it's the rightmost element
                    if (i == levelLength - 1) {
                        rightside.add(node.val);
                    }

                    // add child nodes in the queue
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }
            }
            return rightside;
        }
    }
}
