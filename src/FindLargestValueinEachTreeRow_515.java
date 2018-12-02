import java.util.ArrayList;
import java.util.List;

public class FindLargestValueinEachTreeRow_515 {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res, 0);
        return res;
    }

    private void dfs(TreeNode root, List<Integer> res, int degree) {
        if (root == null) return;

        if (degree == res.size()) {
            res.add(root.val);
        } else {
            res.set(degree, Math.max(root.val, res.get(degree)));
        }
        dfs(root.left, res, degree + 1);
        dfs(root.right, res, degree + 1);
    }
}
