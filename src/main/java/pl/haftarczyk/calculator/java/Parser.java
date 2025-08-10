package pl.haftarczyk.calculator.java;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class Parser {

    public List<Token> toPostfix(List<Token> infixTokens) {
        List<Token> output = new ArrayList<>();
        Deque<OperatorToken> operators = new ArrayDeque<>();

        for (Token token : infixTokens) {
            switch (token) {
                case NumberToken number -> handleNumber(number, output);

                case OperatorToken operator -> handleOperator(operator, output, operators);

                default -> throw new IllegalArgumentException("Unsupported token type: " + token);
            }
        }

        while (!operators.isEmpty()) {
            output.add(operators.pop());
        }

        return output;
    }

    void handleNumber(NumberToken number, List<Token> output) {
        output.add(number);
    }

    void handleOperator(OperatorToken operator, List<Token> output, Deque<OperatorToken> operators) {
        while (!operators.isEmpty() &&
                operators.peek().type().getPrecedence() >= operator.type().getPrecedence()) {
            output.add(operators.pop());
        }
        operators.push(operator);
    }

}
