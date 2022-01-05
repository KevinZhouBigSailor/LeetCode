package Google;

import java.util.*;

public class LongestStringChain_1084 {
    public int longestStrChain(String[] words) {
        Map<String, Integer> memo = new HashMap<>();
        Set<String> wordsPresent = new HashSet<>();
        wordsPresent.addAll(List.of(words));
        int ans = 0;
        for (String word : words) {
            ans = Math.max(ans, dfs(wordsPresent, memo, word));
        }
        return ans;
    }

    private int dfs(Set<String> words, Map<String, Integer> memo, String currentWord) {
        if (memo.containsKey(currentWord)) {
            return memo.get(currentWord);
        }

        int maxLength = 1;
        StringBuilder sb = new StringBuilder(currentWord);

        for (int i = 0; i < currentWord.length(); i++) {
            sb.deleteCharAt(i);
            String newWord = sb.toString();

            if (words.contains(newWord)) {
                int currentLength = 1 + dfs(words, memo, newWord);
                maxLength = Math.max(maxLength, currentLength);
            }
            sb.insert(i, currentWord.charAt(i));
        }
        memo.put(currentWord, maxLength);

        return maxLength;
    }

    /*------------------------------------------------------------*/

    public int longestStrChain2(String[] words) {
        Map<String, Integer> dp = new HashMap<>();

        Arrays.sort(words, (a, b) -> a.length() - b.length());
        int result = 1;

        for (String word : words) {
            int presentLength = 1;

            for (int i = 0; i < word.length(); i++) {
                StringBuilder tmp = new StringBuilder(word);
                tmp.deleteCharAt(i);
                String predecessor = tmp.toString();
                int prevLength = dp.getOrDefault(predecessor, 0);
                presentLength = Math.max(presentLength, prevLength + 1);
            }
            dp.put(word, presentLength);
            result = Math.max(result, presentLength);
        }
        return result;
    }
}
