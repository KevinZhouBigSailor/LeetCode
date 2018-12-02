import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class ConstructStringFromBinaryTree_606 {
    public String tree2str(TreeNode t) {
        if (t == null)
            return "";
        Stack<TreeNode> stack = new Stack<>();
        stack.push(t);
        Set<TreeNode> visited = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            t = stack.peek();
            if (visited.contains(t)) {
                stack.pop();
                sb.append(")");
            } else {
                visited.add(t);
                sb.append("(" + t.val);
                if (t.left == null && t.right != null) {
                    sb.append("()");
                }
                if (t.right != null)
                    stack.push(t.right);
                if (t.left != null)
                    stack.push(t.left);
            }
        }
        return sb.substring(1, sb.length() - 1);
    }
}
