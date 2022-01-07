package Google;

import java.util.*;

/**
 * https://leetcode.com/problems/guess-the-word/discuss/133862/Random-Guess-and-Minimax-Guess-with-Comparison
 */

public class GuessTheWord_843 {
    public void findSecretWord(String[] wordlist, Master master) {
        for (int i = 0, x = 0; i < 10 && x < 6; ++i) {
            HashMap<String, Integer> count = new HashMap<>();
            for (String w1 : wordlist)
                for (String w2 : wordlist)
                    if (match(w1, w2) == 0)
                        count.put(w1, count.getOrDefault(w1 , 0) + 1);
            String guess = "";
            int min0 = 100;
            for (String w : wordlist)
                if (count.getOrDefault(w, 0) < min0) {
                    guess = w;
                    min0 = count.getOrDefault(w, 0);
                }
            x = master.guess(guess);
            List<String> wordlist2 = new ArrayList<String>();
            for (String w : wordlist)
                if (match(guess, w) == x)
                    wordlist2.add(w);
            wordlist = wordlist2.toArray(new String[0]);
        }
    }

    public void findSecretWord2(String[] wordlist, Master master) {
        for (int t = 0, x = 0; t < 10 && x < 6; ++t) {
            int count[][] = new int[6][26], best = 0;
            for (String w : wordlist)
                for (int i = 0; i < 6; ++i)
                    count[i][w.charAt(i) - 'a']++;
            String guess = wordlist[0];
            for (String w: wordlist) {
                int score = 0;
                for (int i = 0; i < 6; ++i)
                    score += count[i][w.charAt(i) - 'a'];
                if (score > best) {
                    guess = w;
                    best = score;
                }
            }
            x = master.guess(guess);
            List<String> wordlist2 = new ArrayList<String>();
            for (String w : wordlist)
                if (match(guess, w) == x)
                    wordlist2.add(w);
            wordlist = wordlist2.toArray(new String[0]);
        }
    }

    public int match(String a, String b) {
        int matches = 0;
        for (int i = 0; i < a.length(); ++i)
            if (a.charAt(i) == b.charAt(i))
                matches ++;
        return matches;
    }
}

interface Master {
    int guess(String word);
}
