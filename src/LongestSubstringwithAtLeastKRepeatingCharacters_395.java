/**
 * Created by zzhou on 5/8/2017.
 * Find the length of the longest substring T of a given string (consists of lowercase letters only) such that every character in T appears no less than k times.

 Example 1:

 Input:
 s = "aaabb", k = 3

 Output:
 3

 The longest substring is "aaa", as 'a' is repeated 3 times.
 Example 2:

 Input:
 s = "ababbc", k = 2

 Output:
 5

 The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
 */
public class LongestSubstringwithAtLeastKRepeatingCharacters_395 {
    public int longestSubstring(String s, int k) {
        char[] str = s.toCharArray();
        return helper(str,0,s.length(),k);
    }

    private int helper(char[] str, int start, int end, int k) {
        if (end - start < k) return 0;//substring length shorter than k.
        int[] count = new int[26];
        for (int i = start; i < end; i++) {
            int idx = str[i] - 'a';
            count[idx]++;
        }
        for (int i = 0; i < 26; i++) {
            if (count[i] < k && count[i] > 0) { //count[i]=0 => i+'a' does not exist in the string, skip it.
                for (int j = start; j < end; j++) {
                    if (str[j] == i + 'a') {
                        int left = helper(str, start, j, k);
                        int right = helper(str, j + 1, end, k);
                        return Math.max(left, right);
                    }
                }
            }
        }
        return end-start;
    }

    public static void main(String[] args) {
        LongestSubstringwithAtLeastKRepeatingCharacters_395 instance = new LongestSubstringwithAtLeastKRepeatingCharacters_395();
        System.out.println(instance.longestSubstring("ababbc", 2));
    }
}
