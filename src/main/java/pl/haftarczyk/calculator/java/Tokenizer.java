package pl.haftarczyk.calculator.java;

import java.util.ArrayList;
import java.util.List;

class Tokenizer {

    private void validateExpression(String expression) {
        if (expression == null || expression.isBlank()) {
            throw new IllegalArgumentException("Expression cannot be null or blank.");
        }
    }

    private NumberToken parseNumber(String token) {
        if (!token.matches("-?\\d+")) {
            throw new IllegalArgumentException("Invalid number: " + token);
        }
        try {
            return new NumberToken(Integer.parseInt(token));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Number out of range: " + token);
        }
    }

    private OperatorToken parseOperator(String token) {
        return OperatorType.fromSymbol(token)
                .map(OperatorToken::new)
                .orElseThrow(() -> new IllegalArgumentException("Unknown operator: " + token));
    }

    public List<Token> tokenize(String expression) {
        validateExpression(expression);

        String[] parts = expression.trim().split(" ");
        List<Token> tokens = new ArrayList<>();

        boolean expectNumber = true;

        for (String part : parts) {
            if (expectNumber) {
                tokens.add(parseNumber(part));
                expectNumber = false;
            } else {
                tokens.add(parseOperator(part));
                expectNumber = true;
            }
        }

        if (expectNumber) {
            throw new IllegalArgumentException("Expression cannot end with operator.");
        }

        return tokens;
    }
}
