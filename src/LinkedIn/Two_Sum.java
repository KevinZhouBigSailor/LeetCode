package LinkedIn;/*
 	Given an array of integers, return indices of the two numbers such that they add up to a specific target.

	You may assume that each input would have exactly one solution.
	
	Example:
	Given nums = [2, 7, 11, 15], target = 9,
	
	Because nums[0] + nums[1] = 2 + 7 = 9,
	return [0, 1].
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Two_Sum {
	public int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<>();
	    int[] ans = new int[2];
	    for (int i = 0; i < nums.length; i++) {
	        if (map.get(target-nums[i]) != null) {
	        	ans[0] = map.get(target-nums[i]);
	            ans[1] = i;	            
	            return ans;
	        }
	        map.put(nums[i],i);
	    }
	    return ans;
    }
	
	public static void main(String[] args) {
		int[] nums = {2, 7, 11, 15};
		int target = 13;
		Two_Sum instance = new Two_Sum();
		System.out.println(Arrays.toString(instance.twoSum(nums, target)));
	}
}
