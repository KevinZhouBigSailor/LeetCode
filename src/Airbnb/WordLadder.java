package Airbnb;

import java.util.*;

public class  WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.size() == 0)
            return 0;
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord))
            return 0;
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        //wordList.remove(endWord);
        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String str = queue.poll();
                if (str.equals(endWord)) return level;
                List<String> neis = neighbors(str, wordSet);
                for (String nei : neis) {
                    if(!visited.contains(nei)) {
                        visited.add(nei);
                        queue.offer(nei);
                    }
                }
            }
            level++;
        }

        return 0;
    }

    private List<String> neighbors(String s, Set<String> wordList) {
        List<String> res = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char[] chars = s.toCharArray();
            for (char ch = 'a'; ch <= 'z'; ch++) {
                chars[i] = ch;
                String word = new String(chars);
                if (wordList.remove(word)) {
                    res.add(word);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        String[] wordArr = {"hot", "dot", "dog", "lot", "log", "cog"};
        List<String> wordList = new ArrayList<>();
        Collections.addAll(wordList, wordArr);

        WordLadder wordLadder = new WordLadder();
        System.out.println(wordLadder.ladderLength(beginWord, endWord, wordList));
    }
}
