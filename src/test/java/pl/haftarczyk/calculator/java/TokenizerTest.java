package pl.haftarczyk.calculator.java;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TokenizerTest {

    private final Tokenizer tokenizer = new Tokenizer();

    @Test
    void shouldTokenizeValidExpression() {
        List<Token> tokens = tokenizer.tokenize("3 + -2");
        assertEquals(3, ((NumberToken) tokens.get(0)).value());
        assertEquals(OperatorType.ADD, ((OperatorToken) tokens.get(1)).type());
        assertEquals(-2, ((NumberToken) tokens.get(2)).value());
    }

    @ParameterizedTest(name = "{1}")
    @CsvSource({
            ", 'Null expression'",
            "'   ', 'Blank expression'",
            "'2 2', 'Two numbers without operator'",
            "'2 +', 'Expression ends with operator'",
            "'2 ^ 3', 'Unsupported operator'"
    })
    void shouldThrowOnInvalidExpression(String input, String description) {
        assertThrows(IllegalArgumentException.class, () -> tokenizer.tokenize(input), description);
    }
}