import java.util.HashMap;

/**
 * Created by zzhou on 4/11/2018.
 */
public class PalindromePermutation_266 {
    public boolean canPermutePalindrome(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        int count = 0;
        for (char c : map.keySet()) {
            count += map.get(c) % 2;
        }
        return count <= 1;
    }
}
