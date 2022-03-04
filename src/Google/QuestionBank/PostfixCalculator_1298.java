package Google.QuestionBank;

import java.util.*;

public class PostfixCalculator_1298 {
    private Set<Character> variableSet = new HashSet<>();
    Stack<Character> operands = new Stack<>();
    Map<Character, Character> variables = new HashMap<>();

    public PostfixCalculator_1298() {
        variableSet.addAll(List.of('a', 'b', 'c'));
    }

    public int postfix(String expression) {
        for (char e : expression.toCharArray()) {
            if (Character.isDigit(e)) {
                operands.add(e);
            } else if (isVariableName(e)) {
                operands.add(e);
            } else {
                if (operands.size() < 2) return 0;

                if (e == '=') {
                    char rhs = operands.pop();
                    char lhs = operands.pop();
                    variables.put(lhs, rhs);
                    continue;
                }

                Integer rhs = getOperand(operands.pop(), variables);
                Integer lhs = getOperand(operands.pop(), variables);

                operation(e, lhs, rhs);
            }
        }
        if (operands.size() != 1) return 0;
        return operands.pop();
    }

    private boolean isVariableName(Character e) {
        return variableSet.contains(e);
    }

    private Integer getOperand(Character operand, Map<Character, Character> variables) {
        if (Character.isDigit(operand)) {
            return Integer.valueOf(operand);
        } else if (variables.containsKey(operand)) {
            return Integer.valueOf(variables.get(operand));
        } else {
            return null;
        }
    }

    private static int operation(char op, int b, int a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b; // assume b is not 0
        }
        return 0;
    }

}
