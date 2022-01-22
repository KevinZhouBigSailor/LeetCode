package Flexport.practice;

import java.util.*;

public class LetterCombinationsofaPhoneNumber {
    Map<String, String> map = Map.of(
            "2", "abc",
            "3", "def",
            "4", "ghi",
            "5", "jkl",
            "6", "mno",
            "7", "pqrs",
            "8", "tuv",
            "9", "wxyz",
            "23", "qcd"
    );

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        dfs(result, map, digits, 0, new StringBuilder());
        return result;
    }

    private void dfs(List<String> result, Map<String, String> map,
                     String digits, int index, StringBuilder current) {
        if (index == digits.length()) {
            result.add(current.toString());
        } else {
            for (int j = index + 1; j < digits.length(); j++) {
                String key = digits.substring(index, j);
                String value = map.get(key);
                if (value == null) {
                    return;
                }
                for (int i = 0; i < value.length(); i++) {
                    current.append(value.charAt(i));
                    dfs(result, map, digits, j, current);
                    current.deleteCharAt(current.length() - 1);
                }
            }
        }
    }
}
