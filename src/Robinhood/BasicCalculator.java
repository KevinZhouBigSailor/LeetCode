package Robinhood;

import java.util.Stack;

public class BasicCalculator {
    public int caculate(String s) {
        Stack<Integer> stack = new Stack();
        char[] chars = s.toCharArray();
        int sign = 1;
        int number = 0;
        int res = 0;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (Character.isDigit(c)) {
                number = number * 10 + (int) (c - '0');
            } else if (c == '+') {
                res += sign * number;
                number = 0;
                sign = 1;
            } else if (c == '-') {
                res += sign * number;
                number = 0;
                sign = -1;
            } else if (c == '(') {
                stack.push(res);
                stack.push(sign);
                sign = 1;
                res = 0;
            } else if (c == ')') {
                res += sign * number;
                number = 0;
                res *= stack.pop();
                res += stack.pop();
            }
        }

        if (number != 0) {
            res += sign * number;
        }

        return res;
    }
}
