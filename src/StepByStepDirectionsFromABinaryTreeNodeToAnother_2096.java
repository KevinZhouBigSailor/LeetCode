/**
 * https://leetcode.com/problems/step-by-step-directions-from-a-binary-tree-node-to-another/discuss/1612105/3-Steps
 */
public class StepByStepDirectionsFromABinaryTreeNodeToAnother_2096 {
    public String getDirections(TreeNode root, int startValue, int destValue) {
        StringBuilder left = new StringBuilder(), right = new StringBuilder();
        find(root, startValue, left);
        find(root, destValue, right);
        int i = 0, max_i = Math.min(left.length(), right.length());
        while (i < max_i && left.charAt(left.length() - i - 1) == right.charAt(right.length() - i - 1)) {
            i++;
        }
        return "U".repeat(left.length() - i) + right.reverse().substring(i);
    }

    private boolean find(TreeNode n, int val, StringBuilder sb) {
        if (n.val == val) {
            return true;
        }
        if (n.left != null && find(n.left, val, sb)) {
            sb.append("L");
        } else if (n.right != null && find(n.right, val, sb)) {
            sb.append("R");
        }
        return sb.length() > 0;
    }
}
