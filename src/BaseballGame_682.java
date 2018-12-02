import java.util.Stack;

public class BaseballGame_682 {
    public int calPoints(String[] ops) {
        Stack<Integer> stack = new Stack();

        for (String op: ops) {
            if (op.equals("+")) {
                int top = stack.pop();
                int newTop = stack.peek() + top;
                stack.push(top);
                stack.push(newTop);
            }
            else if (op.equals("D")) {
                stack.push(stack.peek() * 2);
            }
            else if (op.equals("C")) {
                stack.pop();
            }
            else {
                stack.push(Integer.valueOf(op));
            }
        }

        int sum = 0;
        for(Integer value: stack) {
            sum += value;
        }
        return sum;
    }
}
