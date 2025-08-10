package pl.haftarczyk.calculator.java;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EvaluatorTest {

    private final Evaluator evaluator = new Evaluator();

    @Test
    void shouldEvaluatePostfixExpression() {
        List<Token> postfix = List.of(
                new NumberToken(3),
                new NumberToken(5),
                new OperatorToken(OperatorType.ADD)
        );
        assertEquals(8, evaluator.evaluatePostfix(postfix));
    }

    @Test
    void shouldThrowWhenNotEnoughOperands() {
        List<Token> postfix = List.of(
                new NumberToken(3),
                new OperatorToken(OperatorType.ADD)
        );
        assertThrows(IllegalArgumentException.class, () -> evaluator.evaluatePostfix(postfix));
    }

    @Test
    void shouldThrowWhenExtraOperandsRemain() {
        List<Token> postfix = List.of(
                new NumberToken(3),
                new NumberToken(4)
        );
        assertThrows(IllegalArgumentException.class, () -> evaluator.evaluatePostfix(postfix));
    }

    @Test
    void shouldThrowWhenDivideByZero() {
        List<Token> postfix = List.of(
                new NumberToken(3),
                new NumberToken(0),
                new OperatorToken(OperatorType.DIVIDE)
        );
        assertThrows(ArithmeticException.class, () -> evaluator.evaluatePostfix(postfix));
    }
}

