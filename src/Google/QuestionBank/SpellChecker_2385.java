package Google.QuestionBank;

import java.util.*;

public class SpellChecker_2385 {
    /**
     * Ask questions:
     * e.x: how do we handle numbers, spaces, and punctuation?
     * would it have empty string?
     * how do we handle multiple matching corrections?
     */

    // Alternative solution: Convert map and dictionary to trie O(ML + DL),
    // L is max length of the input string in the map

    // Normal solution: We assume that all corrections are "complete words"
    public String translate(String inputString, Map<String, String> corrections) {
        StringBuilder sb = new StringBuilder();
        List<String> inputs = split(inputString);
        for (String input : inputs) {
            if (corrections.containsKey(input)) {
                sb.append(corrections.get(input));
            } else {
                sb.append(input);
            }
        }
        return sb.toString();
    }

    private List<String> split(String input) {
        List<String> result = new ArrayList<>();
        result.addAll(Arrays.asList(input.split(" ")));
        return result;
    }
}
