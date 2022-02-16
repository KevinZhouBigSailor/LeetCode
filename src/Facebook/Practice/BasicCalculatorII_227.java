package Facebook.Practice;

import java.util.*;

public class BasicCalculatorII_227 {
    public int calculate(String s) {
        int len = s.length();
        if (s == null || (len == 0)) return 0;
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        char sign = '+';
        for (int i = 0; i < len; i++) {
            if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';
            }
            if ((!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ') || i == len - 1) {
                switch (sign) {
                    case '-':
                        stack.push(-num);
                        break;
                    case '+':
                        stack.push(num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    case '/':
                        stack.push(stack.pop() / num);
                }
                sign = s.charAt(i);
                num = 0;
            }
        }
        int res = 0;
        for (int i : stack) {
            res += i;
        }
        return res;
    }

    public static void main(String[] args) {
        BasicCalculatorII_227 instance = new BasicCalculatorII_227();
        instance.calculate("3+2*2");
    }
}
