import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetsII_90 {
    /*public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> each = new ArrayList<>();
        bfs(res, each, 0, nums);
        return res;
    }

    public void bfs(List<List<Integer>> res, List<Integer> each, int pos, int[] nums) {
        if (pos <= nums.length) res.add(each);
        int i = pos;
        while (i < nums.length) {
            each.add(nums[i]);
            bfs(res, new ArrayList<>(each), i + 1, nums);
            each.remove(each.size() - 1);
            i++;
            while (i < nums.length && nums[i] == nums[i - 1]) i++;
        }
    }*/

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, int start){
        list.add(new ArrayList<>(tempList));
        for(int i = start; i < nums.length; i++){
            if(i > start && nums[i] == nums[i-1]) continue; // skip duplicates
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }
}
