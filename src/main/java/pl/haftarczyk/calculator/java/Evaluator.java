package pl.haftarczyk.calculator.java;

import java.util.List;
import java.util.Stack;

class Evaluator {

    public int evaluatePostfix(List<Token> postfixTokens) {
        Stack<Integer> stack = new Stack<>();

        for (Token token : postfixTokens) {
            if (token instanceof NumberToken(int value)) {
                stack.push(value);
            } else if (token instanceof OperatorToken(OperatorType type)) {
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Invalid expression: not enough operands.");
                }
                int right = stack.pop();
                int left = stack.pop();
                int result = type.apply(left, right);
                stack.push(result);
            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Invalid expression.");
        }

        return stack.pop();
    }
}
