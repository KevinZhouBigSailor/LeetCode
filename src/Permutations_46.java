import java.util.ArrayList;
import java.util.List;

public class Permutations_46 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        backtracking(lists, new ArrayList<>(), nums);
        return lists;
    }

    public void backtracking(List<List<Integer>> lists, List<Integer> tempList, int[] nums) {
        if (tempList.size() == nums.length) {
            lists.add(new ArrayList<>(tempList));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if(tempList.contains(nums[i])) continue; // element already exists, skip
                tempList.add(nums[i]);
                backtracking(lists, tempList, nums);
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}
