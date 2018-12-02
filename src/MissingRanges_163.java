import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzhou on 2/20/2018.
 */
public class MissingRanges_163 {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        for (int n : nums) {
            if (n == Integer.MIN_VALUE) {
                lower = n + 1;
                continue;
            }
            if (lower == n - 1) res.add("" + lower);
            else if (lower < n - 1) res.add(lower + "->" + (n - 1));
            if (n == Integer.MAX_VALUE) return res; // added
            lower = n + 1;
        }
        // if (lower == Integer.MIN_VALUE) return res;
        if (lower == upper) res.add("" + lower);
        else if (lower < upper) res.add(lower + "->" + upper);
        return res;
    }
}
