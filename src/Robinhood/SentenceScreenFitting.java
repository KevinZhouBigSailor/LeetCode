package Robinhood;

import java.util.ArrayList;
import java.util.List;

public class SentenceScreenFitting {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        String s = String.join(" ", sentence) + " ";
        int start = 0, l = s.length();
        for (int i = 0; i < rows; i++) {
            start += cols;
            if (s.charAt(start % l) == ' ') {
                start++;
            } else {
                while (start > 0 && s.charAt((start - 1) % l) != ' ') {
                    start--;
                }
            }
        }
        return start / s.length();
    }

    public List<String> wordWrap(String input, Integer k) {
        List<String> res = new ArrayList<>();
        String[] words = input.split("\\s+");
        int curK = k;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (word.length() > k) return null;
            if (curK < word.length()) {
                res.add(sb.toString());
                curK = k;
                sb = new StringBuilder();
            }
            curK -= (word.length() + 1);
            sb.append(word);
            if (curK > 0 && i != words.length - 1) {
                sb.append(" ");
            }

        }
        res.add(sb.toString());

        return res;
    }


    public static void main(String[] args) {
        String input = "his dog chases her cat";
        SentenceScreenFitting sentenceScreenFitting = new SentenceScreenFitting();
        System.out.println(sentenceScreenFitting.wordWrap(input, 15));
    }
}
