import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermutationsII_47 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        boolean[] visited = new boolean[nums.length];
        List<Integer> current = new ArrayList<>();

        return result;
    }

    public void backtracking(int[] nums, List<List<Integer>> result, boolean[] visited, List<Integer> current) {
        if (result.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                if (i > 0 && nums[i] == nums[i - 1] && visited[i - 1]) return;
                visited[i] = true;
                current.add(nums[i]);
                backtracking(nums, result, visited, current);
                current.remove(current.size() - 1);
                visited[i] = false;
            }
        }
    }
}
