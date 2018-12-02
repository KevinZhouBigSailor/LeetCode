package leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzhou on 1/3/2018.
 */
public class FactorCombinations_254 {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        helper(res, new ArrayList<>(), n, 2);
        return res;
    }

    public void helper(List<List<Integer>> result, List<Integer> item, int n, int start) {
        if (n <= 1) {
            if (item.size() > 1) {
                result.add(new ArrayList<Integer>(item));
            }
        }

        for (int i = start; i <= n; i++) {
            if (n % i == 0) {
                item.add(i);
                helper(result, item, n / i, i);
                item.remove(item.size() - 1);
            }
        }
    }
}
