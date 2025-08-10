package pl.haftarczyk.calculator.java;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.haftarczyk.calculator.common.IntegerCalculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JavaIntegerCalculatorTest {

    private final IntegerCalculator javaIntegerCalculator = new JavaIntegerCalculator();

    @ParameterizedTest(name = "{2}")
    @CsvSource({
            "'5', 5, 'Single number'",
            "'3 + 5', 8, 'Simple addition'",
            "'10 - 3', 7, 'Simple subtraction'",
            "'3 * 4', 12, 'Simple multiplication'",
            "'8 / 2', 4, 'Simple division'",
            "'3 * 4 + 2', 14, 'Operator precedence - multiply before add'",
            "'3 + 4 * 2', 11, 'Operator precedence - multiply after add'",
            "'3 * -2', -6, 'Multiplication with negative number'",
            "'-3 * -2', 6, 'Multiplication of two negative numbers'",
            "'3 - -2', 5, 'Subtraction with negative number'",
            "'3 * 3 / 2 * 4', 16, 'Multiple operations'",
            "'0 * -1', 0, 'Multiplication by zero'",
            "'6 / 7', 0, 'Integer division result'",
            "'0 / 7', 0, 'Zero divided by positive number'",
            "'0 / -7', 0, 'Zero divided by negative number'"
    })
    void shouldCalculateValidExpressions(String input, int expected, String description) {
        assertEquals(expected, javaIntegerCalculator.calculate(input), description);
    }

    @Test
    @DisplayName("Should throw when dividing by zero")
    void shouldThrowDivideByZero() {
        ArithmeticException ex = assertThrows(ArithmeticException.class,
                () -> javaIntegerCalculator.calculate("3 / 0"));
        assertEquals("/ by zero", ex.getMessage());
    }

    @ParameterizedTest(name = "{1}")
    @CsvSource({
            "'', 'Empty expression'",
            "'   ', 'Blank expression'",
            "'2/3', 'Missing space between number and operator'",
            "'2 + 3a', 'Unexpected character in number'",
            "'2 -- 3', 'Double minus without operand'",
            "'2 * +3', 'Plus sign without space'",
            "'2 * + 3', 'Additional plus sign treated as number error'",
            "'2 2', 'Two numbers without operator'",
            "'3 +', 'Expression ends with operator'",
            "'+', 'Expression starts with operator'"
    })
    void shouldThrowOnInvalidExpression(String input, String description) {
        assertThrows(IllegalArgumentException.class,
                () -> javaIntegerCalculator.calculate(input),
                description);
    }
}