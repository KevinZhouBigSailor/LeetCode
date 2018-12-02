/*
 * Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? 
 * Find all unique quadruplets in the array which gives the sum of target.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class FourSum_18 {
	public List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> ret = new ArrayList<List<Integer>>();
        HashMap<Integer, List<Integer[]>> hm = new HashMap<Integer, List<Integer[]>>();
        int len = nums.length;

        Arrays.sort(nums);
        // store pair
        for (int i = 0; i < len - 1; ++i) {
            for (int j = i + 1; j < len; ++j) {
                int sum = nums[i] + nums[j];
                Integer[] tuple = {nums[i], i, nums[j], j};
                if (!hm.containsKey(sum)) {
                    hm.put(sum, new ArrayList<Integer[]>());
                }
                hm.get(sum).add(tuple);
            }
        }

        Integer[] keys = hm.keySet().toArray(new Integer[hm.size()]);
        System.out.println(Arrays.toString(keys));
        for (int key : keys) {
            //if (hm.containsKey(key)) {
                if (hm.containsKey(target - key)) {
                    List<Integer[]> first_pairs = hm.get(key);
                    List<Integer[]> second_pairs = hm.get(target - key);

                    for (int i = 0; i < first_pairs.size(); ++i) {
                        Integer[] first = first_pairs.get(i);
                        for (int j = 0; j < second_pairs.size(); ++j) {
                            Integer[] second = second_pairs.get(j);
                            // check
                            if (first[1] != second[1] && first[1] != second[3] &&
                                    first[3] != second[1] && first[3] != second[3]) {
                                List<Integer> ans = Arrays.asList(first[0], first[2], second[0], second[2]);
                                Collections.sort(ans);
                                if (!ret.contains(ans)) {
                                    ret.add(ans);
                                }
                            }
                        }
                    }

                    hm.remove(key);
                    hm.remove(target - key);
                }
            //}
        }

        return ret;
    }
	
	public static void main(String[] args) {
		FourSum_18 s = new FourSum_18();
        int[] input = {2,1,0,-1};
        System.out.println(s.fourSum(input, 2));
    }
}
