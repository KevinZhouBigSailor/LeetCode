package Google.QuestionBank;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IsThereAtLeastOnePairHammingDistance_2802 {
    /*
    private class TrieNode {
        public boolean isWord;
        public TrieNode[] children;
        public List<String> startwith;
        public String word;
        int weight;

        public TrieNode() {
            children = new TrieNode[26];
            startwith = new ArrayList<>();
        }
    }
     */

    // Time complexity O(nm)

    private int xorSum(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            result ^= s.charAt(i);
        }
        return result;
    }

    public boolean hasDistanceExactlyOnce(List<String> strings) {
        int n = strings.size();
        int m = strings.get(0).length();
        List<String> uniqueStrings = new ArrayList<>(new HashSet<String>(strings));
        List<Integer> string_hashs = uniqueStrings.stream().map(s -> xorSum(s)).collect(Collectors.toList());
        for (int i = 0; i < m; i++) {
            Map<Integer, List<String>> seen = new HashMap<>();
            List<Map.Entry<String, Integer>> zippedList = IntStream.range(0, uniqueStrings.size())
                    .mapToObj(j -> Map.entry(uniqueStrings.get(j), string_hashs.get(j)))
                    .collect(Collectors.toList());
            for (Map.Entry entry : zippedList) {
                String string = entry.getKey().toString();
                int hashCode = Integer.valueOf(entry.getValue().toString()); // To add error handling
                int hashCodeMinus = hashCode ^ (string.charAt(i) & 1);
                for (String other : seen.get(hashCodeMinus)) {
                    if (hammingDistance(string, other) == 1) {
                        return true;
                    }
                }
                seen.putIfAbsent(hashCodeMinus, new ArrayList<>());
                seen.get(hashCodeMinus).add(string);
            }
        }
        return false;
    }

    private int hammingDistance(String s, String b) {
        return 1;
    }

    private int hammingDistance(int x, int y) {
        int xor = x ^ y, count = 0;
        for (int i = 0; i < 32; i++) count += (xor >> i) & 1;
        return count;
    }
}
