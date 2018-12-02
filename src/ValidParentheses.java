/*
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.

Subscribe to see which companies asked this question
 */

import java.util.Stack;

public class ValidParentheses {
	public boolean isValid(String s) {
		Stack<Integer> stk = new Stack<Integer>();
		final String parentheses = "(){}[]";
		for (int i = 0; i < s.length(); ++i) {
            int pos = parentheses.indexOf(s.substring(i, i + 1));
            if (pos % 2 == 1) {
                if (stk.isEmpty() || stk.pop() != pos - 1)
                    return false;
            } else {
                stk.push(pos);
            }
        }
		return stk.isEmpty();
	}
}
