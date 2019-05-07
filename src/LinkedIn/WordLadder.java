package LinkedIn;

import javafx.util.Pair;

import java.util.*;

public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.size() == 0) {
            return 0;
        }
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            return 0;
        }
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String s = queue.poll();
                if (s.equals(endWord)) return level;
                List<String> neis = getNeighbors(s, wordSet);
                for (String nei : neis) {
                    if (!visited.contains(nei)) {
                        visited.add(nei);
                        queue.offer(nei);
                    }
                }
            }
            level++;
        }
        return level;
    }

    private List<String> getNeighbors(String s, Set<String> wordSet) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char[] chars = s.toCharArray();
            for (char ch = 'a'; ch < 'z'; ch++) {
                chars[i] = ch;
                String word = new String(chars);
                if (wordSet.contains(word)) {
                    res.add(word);
                }

            }
        }
        return res;
    }

    class Solution {

        private int L;
        private HashMap<String, ArrayList<String>> allComboDict;

        Solution() {
            this.L = 0;

            // Dictionary to hold combination of words that can be formed,
            // from any given word. By changing one letter at a time.
            this.allComboDict = new HashMap<String, ArrayList<String>>();
        }

        private int visitWordNode(
                Queue<Pair<String, Integer>> Q,
                HashMap<String, Integer> visited,
                HashMap<String, Integer> othersVisited) {
            Pair<String, Integer> node = Q.remove();
            String word = node.getKey();
            int level = node.getValue();

            for (int i = 0; i < this.L; i++) {

                // Intermediate words for current word
                String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);

                // Next states are all the words which share the same intermediate state.
                for (String adjacentWord : this.allComboDict.getOrDefault(newWord, new ArrayList<String>())) {
                    // If at any point if we find what we are looking for
                    // i.e. the end word - we can return with the answer.
                    if (othersVisited.containsKey(adjacentWord)) {
                        return level + othersVisited.get(adjacentWord);
                    }

                    if (!visited.containsKey(adjacentWord)) {

                        // Save the level as the value of the dictionary, to save number of hops.
                        visited.put(adjacentWord, level + 1);
                        Q.add(new Pair(adjacentWord, level + 1));
                    }
                }
            }
            return -1;
        }

        public int ladderLength(String beginWord, String endWord, List<String> wordList) {

            if (!wordList.contains(endWord)) {
                return 0;
            }

            // Since all words are of same length.
            this.L = beginWord.length();

            wordList.forEach(
                    word -> {
                        for (int i = 0; i < L; i++) {
                            // Key is the generic word
                            // Value is a list of words which have the same intermediate generic word.
                            String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
                            ArrayList<String> transformations =
                                    this.allComboDict.getOrDefault(newWord, new ArrayList<String>());
                            transformations.add(word);
                            this.allComboDict.put(newWord, transformations);
                        }
                    });

            // Queues for birdirectional BFS
            // BFS starting from beginWord
            Queue<Pair<String, Integer>> Q_begin = new LinkedList<Pair<String, Integer>>();
            // BFS starting from endWord
            Queue<Pair<String, Integer>> Q_end = new LinkedList<Pair<String, Integer>>();
            Q_begin.add(new Pair(beginWord, 1));
            Q_end.add(new Pair(endWord, 1));

            // Visited to make sure we don't repeat processing same word.
            HashMap<String, Integer> visitedBegin = new HashMap<String, Integer>();
            HashMap<String, Integer> visitedEnd = new HashMap<String, Integer>();
            visitedBegin.put(beginWord, 1);
            visitedEnd.put(endWord, 1);

            while (!Q_begin.isEmpty() && !Q_end.isEmpty()) {

                // One hop from begin word
                int ans = visitWordNode(Q_begin, visitedBegin, visitedEnd);
                if (ans > -1) {
                    return ans;
                }

                // One hop from end word
                ans = visitWordNode(Q_end, visitedEnd, visitedBegin);
                if (ans > -1) {
                    return ans;
                }
            }

            return 0;
        }
    }
}
