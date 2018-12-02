import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by zzhou on 2/15/2018.
 */
public class LongestSubstringwithAtMostKDistinctCharacters_340 {
    public int lengthOfLongestSubstringKDistinct(String str, int k) {
        if (str == null || str.length() == 0 || k == 0) {
            return 0;
        }
        TreeMap<Integer, Character> lastOccurrence = new TreeMap<>();
        Map<Character, Integer> inWindow = new HashMap<>();
        int left = 0;
        int max = 1;
        for (int i = 0; i < str.length(); i++) {
            char in = str.charAt(i);
            while(inWindow.size() == k && !inWindow.containsKey(in)) {
                int first = lastOccurrence.firstKey();
                char out = lastOccurrence.get(first);
                inWindow.remove(out);
                lastOccurrence.remove(first);
                left = first + 1;
            }

            if (inWindow.containsKey(in)) {
                lastOccurrence.remove(inWindow.get(in));
            }
            inWindow.put(in, i);
            lastOccurrence.put(i, in);
            max = Math.max(max, i - left + 1);
        }
        return max;
    }
}
