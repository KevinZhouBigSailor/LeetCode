import java.util.HashSet;
import java.util.Set;

/**
 * Created by zzhou on 7/17/2017.
 */
public class ContainDuplicateII_219 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<Integer>();
        for(int i = 0; i < nums.length; i++){
            if(i > k) set.remove(nums[i-k-1]);
            if(!set.add(nums[i])) return true;
        }
        return false;
    }
}
