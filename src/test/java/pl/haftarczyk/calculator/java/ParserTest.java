package pl.haftarczyk.calculator.java;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {

    private final Parser parser = new Parser();

    @Test
    void shouldConvertSimpleExpressionToPostfix() {
        List<Token> infix = List.of(
                new NumberToken(3),
                new OperatorToken(OperatorType.ADD),
                new NumberToken(5)
        );
        List<Token> postfix = parser.toPostfix(infix);

        assertEquals(3, ((NumberToken) postfix.get(0)).value());
        assertEquals(5, ((NumberToken) postfix.get(1)).value());
        assertEquals(OperatorType.ADD, ((OperatorToken) postfix.get(2)).type());
    }

    @Test
    void shouldRespectOperatorPrecedence() {
        List<Token> infix = List.of(
                new NumberToken(3),
                new OperatorToken(OperatorType.ADD),
                new NumberToken(4),
                new OperatorToken(OperatorType.MULTIPLY),
                new NumberToken(2)
        );
        List<Token> postfix = parser.toPostfix(infix);

        assertEquals(3, ((NumberToken) postfix.get(0)).value());
        assertEquals(4, ((NumberToken) postfix.get(1)).value());
        assertEquals(2, ((NumberToken) postfix.get(2)).value());
        assertEquals(OperatorType.MULTIPLY, ((OperatorToken) postfix.get(3)).type());
        assertEquals(OperatorType.ADD, ((OperatorToken) postfix.get(4)).type());
    }
}