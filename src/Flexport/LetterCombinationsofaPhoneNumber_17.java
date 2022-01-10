package Flexport;

import java.util.*;

public class LetterCombinationsofaPhoneNumber_17 {
    public List<String> letterCombinations(String digits) {
        LinkedList<String> ans = new LinkedList<String>();
        if (digits.length() == 0) return ans;
        String[] mapping = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        for (int i = 0; i < digits.length(); i++) {
            int x = Character.getNumericValue(digits.charAt(i));
            while (ans.peek().length() == i) {
                String t = ans.remove();
                for (char s : mapping[x].toCharArray()) {
                    ans.add(t + s);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
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
        List<String> result = new ArrayList<>();
        dfs(result, map, "234", 0, new StringBuilder());
        System.out.println(Arrays.toString(result.toArray()));
    }

    public static void dfs(List<String> result, Map<String, String> map, String digits, int index, StringBuilder
            current) {
        if (index == digits.length()) {
            result.add(current.toString());
        } else {
            for (int j = index + 1; j <= digits.length(); j++) {
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
