

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class GuessTheWord_843 {
    //int[][] H;

    public void findSecretWord(String[] wordlist, Master master) {
        for (int i = 0, x = 0; i < 10 && x < 6; ++i) {
            HashMap<String, Integer> count = new HashMap<>();
            for (String w1 : wordlist)
                for (String w2 : wordlist)
                    if (match(w1, w2) == 0)
                        count.put(w1, count.getOrDefault(w1, 0) + 1);
            Pair<String, Integer> minimax = new Pair<>("", 1000);
            for (String w : wordlist)
                if (count.getOrDefault(w, 0) < minimax.getValue())
                    minimax = new Pair<>(w, count.getOrDefault(w, 0));
            x = master.guess(minimax.getKey());
            List<String> wordlistTmp = new ArrayList<String>();
            for (String w : wordlist)
                if (match(minimax.getKey(), w) == x)
                    wordlistTmp.add(w);
            wordlist = wordlistTmp.toArray(new String[0]);
        }
    }

    public int match(String a, String b) {
        int matches = 0;
        for (int i = 0; i < a.length(); ++i) if (a.charAt(i) == b.charAt(i)) matches++;
        return matches;
    }

    /*int N = wordlist.length;
        H = new int[N][N];
        for (int i = 0; i < N; ++i)
            for (int j = i; j < N; ++j) {
                int match = 0;
                for (int k = 0; k < 6; ++k)
                    if (wordlist[i].charAt(k) == wordlist[j].charAt(k))
                        match++;
                H[i][j] = H[j][i] = match;
            }

        List<Integer> possible = new ArrayList();
        HashSet<Integer> path = new HashSet();
        for (int i = 0; i < N; ++i) possible.add(i);

        while (!possible.isEmpty()) {
            int guess = solve(possible, path);
            int matches = master.guess(wordlist[guess]);
            if (matches == wordlist[0].length()) return;
            List<Integer> possible2 = new ArrayList();
            for (Integer j : possible) if (H[guess][j] == matches) possible2.add(j);
            possible = possible2;
            path.add(guess);
        }*/

    /**
     * Get most common word index from the remaining indexes
     *
     * @param possible
     * @param path
     * @return
     */
    /*public int solve(List<Integer> possible, HashSet<Integer> path) {
        if (possible.size() <= 2) return possible.get(0);
        List<Integer> ansgrp = possible;
        int ansguess = -1;

        for (int guess = 0; guess < H.length; ++guess) {
            if (!path.contains(guess)) {
                ArrayList<Integer>[] groups = new ArrayList[7];
                for (int i = 0; i < 7; ++i) groups[i] = new ArrayList<Integer>();
                for (Integer j : possible)
                    if (j != guess) {
                        groups[H[guess][j]].add(j);
                    }

                ArrayList<Integer> maxgroup = groups[0];
                for (int i = 0; i < 7; ++i)
                    if (groups[i].size() > maxgroup.size())
                        maxgroup = groups[i];

                if (maxgroup.size() < ansgrp.size()) {
                    ansgrp = maxgroup;
                    ansguess = guess;
                }
            }
        }

        return ansguess;
    }*/
}

interface Master {
    int guess(String word);
}