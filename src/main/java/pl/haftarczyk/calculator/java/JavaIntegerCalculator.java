package pl.haftarczyk.calculator.java;

import pl.haftarczyk.calculator.common.IntegerCalculator;

import java.util.List;

public class JavaIntegerCalculator implements IntegerCalculator {

    private final Tokenizer tokenizer = new Tokenizer();
    private final Parser parser = new Parser();
    private final Evaluator evaluator = new Evaluator();

    @Override
    public int calculate(String expression) {
        List<Token> tokens = tokenizer.tokenize(expression);
        List<Token> postfix = parser.toPostfix(tokens);
        return evaluator.evaluatePostfix(postfix);
    }
}
