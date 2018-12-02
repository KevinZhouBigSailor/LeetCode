/**
 * Created by zzhou on 2/27/2018.
 */
public class PathSumIV_666 {
    int ans = 0;

    public int pathSum(int[] nums) {
        TreeNode root = new TreeNode(nums[0] % 10);
        for (int num : nums) {
            if (num == nums[0]) continue;
            int depth = num / 100, pos = num / 10 % 10, val = num % 10;
            pos--;
            TreeNode cur = root;
            for (int d = depth - 2; d >= 0; d--) {
                if (pos < 1 << d) {
                    if (cur.left == null) cur.left = new TreeNode(val);
                    cur = cur.left;
                } else {
                    if (cur.right == null) cur.right = new TreeNode(val);
                    cur = cur.right;
                }
                pos %= 1 << d;
            }
        }

        dfs(root, ans);
        return ans;
    }

    private void dfs(TreeNode root, int sum) {
        if (root == null) return;
        sum += root.val;
        if (root.left == null && root.right == null) {
            ans += sum;
        } else {
            dfs(root.left, sum);
            dfs(root.right, sum);
        }
    }
}
