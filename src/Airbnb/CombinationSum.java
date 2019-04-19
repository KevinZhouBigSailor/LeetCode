package Airbnb;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(target, 0, candidates, res, new ArrayList<>());
        return res;
    }

    private void dfs(int remain, int start, int[] candidates, List<List<Integer>> res, List<Integer> tempList) {
        if (remain < 0) return;
        if (remain == 0) res.add(new ArrayList<>(tempList));
        else {
            for (int i = start; i < candidates.length; i++) {
                tempList.add(candidates[i]);
                dfs(remain - candidates[i], i, candidates, res, tempList);
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}
