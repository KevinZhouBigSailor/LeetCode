package Facebook;

import java.util.*;

public class WordBreakII_140 {
    public List<String> wordBreak(String s, List<String> wordDict) {
        return DFS(s, wordDict, new HashMap<String, LinkedList<String>>());
    }

    // DFS function returns an array including all substrings derived from s.
    private List<String> DFS(String s, List<String> wordDict, HashMap<String, LinkedList<String>>map) {
        if (map.containsKey(s))
            return map.get(s);

        LinkedList<String> res = new LinkedList<String>();
        if (s.length() == 0) {
            res.add("");
            return res;
        }
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                List<String> sublist = DFS(s.substring(word.length()), wordDict, map);
                for (String sub : sublist)
                    res.add(word + (sub.isEmpty() ? "" : " ") + sub);
            }
        }
        map.put(s, res);
        return res;
    }

    public static void main(String[] args) {
        WordBreakII_140 instance = new WordBreakII_140();
        String s = "catsanddog";
        List<String> dict = new ArrayList<>();
        String[] dicts = {"cat", "cats", "and", "sand", "dog"};
        dict.addAll(Arrays.asList(dicts));
        System.out.println(instance.wordBreak(s, dict));
    }
}
