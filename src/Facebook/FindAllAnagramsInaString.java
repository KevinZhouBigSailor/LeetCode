package Facebook;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by zzhou on 12/13/2017.
 * 1. Use two pointers: start and end to represent a window.
 * 2. Move end to find a valid window.
 * 3. When a valid window is found, move start to find a smaller window.
 */
public class FindAllAnagramsInaString {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new LinkedList<>();
        if (p.length() > s.length()) return result;
        Map<Character, Integer> map = new HashMap<>();
        for (char c : p.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int counter = map.size();

        int begin = 0, end = 0;
        int head = 0;
        int len = Integer.MAX_VALUE;


        while (end < s.length()) {
            char c = s.charAt(end);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) - 1);
                if (map.get(c) == 0) counter--;
            }
            end++;

            while (counter == 0) {
                char tempc = s.charAt(begin);
                if (map.containsKey(tempc)) {
                    map.put(tempc, map.get(tempc) + 1);
                    if (map.get(tempc) > 0) {
                        counter++;
                    }
                }
                if (end - begin == p.length()) {
                    result.add(begin);
                }
                begin++;
            }

        }
        return result;
    }

    public static void main(String[] args) {
        FindAllAnagramsInaString instance = new FindAllAnagramsInaString();
        String s = "abab";
        String p = "ab";
        instance.findAnagrams(s, p);
    }
}
