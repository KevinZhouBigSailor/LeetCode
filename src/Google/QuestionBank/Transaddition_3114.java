package Google.QuestionBank;

import java.util.*;

// NOTE: Up to L4
public class Transaddition_3114 {
    private Character[] LETTERS = new Character[26];

    public Transaddition_3114() {
        for (int i = 0; i < 26; i++) {
            LETTERS[i] = (char) (i + 65);
        }
    }

    public boolean ReachableInOne(String start, String end) {
        int[] startLetterCounts = new int[26];
        int[] endLetterCounts = new int[26];
        for (char c : start.toCharArray()) {
            startLetterCounts[c - 'a']++;
        }
        for (char c : end.toCharArray()) {
            endLetterCounts[c - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            endLetterCounts[i] -= startLetterCounts[i];
        }
        int oneCount = 0;
        for (int i = 0; i < 26; i++) {
            if (endLetterCounts[i] != 0) {
                if (endLetterCounts[i] == 1) {
                    oneCount++;
                } else {
                    return false;
                }
            }
        }
        return oneCount == 1;
    }

    public List<String> allowOneStep(List<String> starts, List<String> ends) {
        HashMap<String, List<String>> endMap = new HashMap<>();
        for (String end : ends) {
            String key = orderIndependentKey(end);
            endMap.putIfAbsent(key, new ArrayList<>());
            endMap.get(key).add(end);
        }

        List<String> result = new ArrayList<>();
        for (String start : starts) {
            for (Character letter : LETTERS) {
                String key = orderIndependentKey(letter + start);
                if (endMap.containsKey(key)) {
                    result.addAll(endMap.get(key));
                }
            }
        }

        return result;
    }

    public List<String> findLongestChain(List<String> words) {
        HashMap<String, List<String>> wordMap = new HashMap<>();
        for (String word : words) {
            String key = orderIndependentKey(word);
            wordMap.putIfAbsent(key, new ArrayList<>());
            wordMap.get(key).add(word);
        }

        List<String> result = new ArrayList<>();
        List<String> newResult = new ArrayList<>();
        result.add(" ");
        while (true) {
            newResult.clear();
            for (String word : result) {
                for (Character letter : LETTERS) {
                    String key = orderIndependentKey(letter + word);
                    if (wordMap.containsKey(key)) {
                        newResult.addAll(wordMap.get(key));
                    }
                }
            }
            if (newResult.size() > 0) {
                result.clear();
                result.addAll(newResult);
                continue;
            } else {
                break;
            }
        }
        if (result.size() == 1 && result.get(0).equals(" ")) {
            return new ArrayList<String>();
        }
        return result;
    }

    // Get sorted characters hashcode
    private String orderIndependentKey(String word) {
        int[] letterCounts = new int[26];
        for (char c : word.toCharArray()) {
            letterCounts[c - 'a']++;
        }
        return String.join("", letterCounts.toString());
    }
}
