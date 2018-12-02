/*
 * Given a string, find the length of the longest substring without repeating characters. 
 * For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3. For "bbbbb" the longest substring is "b", with the length of 1.
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class LongestSubstringWithoutRepeatingCharacters_3 {
	public int lengthOfLongestSubstring(String s) {
//		HashSet<Character> hash = new HashSet<Character>();
//	    int n = s.length();
//	    int max = 0;
//	    for(int i = 0; i < n; i++){
//	        int j = i;
//	        int an = 0;
//	        while(j < n&&hash.add(s.charAt(j))){
//	            j++;
//	            an++;
//	        }
//	        max = Math.max(max, an);
//	        hash.clear();
//	    }
//	    return max;
		
		/*int max = 0, left = 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();

        //init map
        for (int i = 0; i < s.length(); ++i) {
        	if (!map.containsKey(s.charAt(i))) {
        		map.put(s.charAt(i), -1);
        	}
        }            

        for (int i = 0; i < s.length(); ++i) {
            if (map.get(s.charAt(i)) >= left)
                left = map.get(s.charAt(i)) + 1;
            map.put(s.charAt(i), i) ;
            max = Math.max(max, i - left + 1);
        }
        return max;*/
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }
	
	 public static void main(String[] args) {
	     Scanner cin = new Scanner(System.in);
	     LongestSubstringWithoutRepeatingCharacters_3 s = new LongestSubstringWithoutRepeatingCharacters_3();
	     System.out.println(s.lengthOfLongestSubstring("abcabcbb"));
	 }
}
