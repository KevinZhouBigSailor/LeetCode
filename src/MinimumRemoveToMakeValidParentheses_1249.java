import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class MinimumRemoveToMakeValidParentheses_1249 {
    public String minRemoveToMakeValid(String s) {
        Set<Integer> indexesToRemove = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            }
            if (s.charAt(i) == ')') {
                if (stack.isEmpty()) {
                    indexesToRemove.add(i);
                } else {
                    stack.pop();
                }
            }
        }
        // Put any indexes remaining on stack into the set.
        while (!stack.isEmpty()) indexesToRemove.add(stack.pop());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!indexesToRemove.contains(i)) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    class Solution {
        private StringBuilder removeInvalidClosing(CharSequence string, char open, char close) {
            StringBuilder sb = new StringBuilder();
            int balance = 0;
            for (int i = 0; i < string.length(); i++) {
                char c = string.charAt(i);
                if (c == open) {
                    balance++;
                }
                if (c == close) {
                    if (balance == 0) continue;
                    balance--;
                }
                sb.append(c);
            }
            return sb;
        }

        public String minRemoveToMakeValid(String s) {
            StringBuilder result = removeInvalidClosing(s, '(', ')');
            result = removeInvalidClosing(result.reverse(), ')', '(');
            return result.reverse().toString();
        }
    }
}
