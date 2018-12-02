import java.util.ArrayList;
import java.util.List;

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
}
