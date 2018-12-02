import java.util.*;

public class WordBreak_139 {
    public boolean wordBreak(String s, List<String> wordDict) {
        return wordbreakhelper(s, new HashSet<>(wordDict), 0, new Boolean[s.length()]);
    }

    public boolean wordbreakhelper(String s, Set<String> wordDict, int start, Boolean[] memo) {
        if (start == s.length()) return true;

        if (memo[start] != null) return memo[start];

        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end)) && wordbreakhelper(s, wordDict, end, memo))
                return memo[start] = true;
        }
        return memo[start] = false;
    }
}
