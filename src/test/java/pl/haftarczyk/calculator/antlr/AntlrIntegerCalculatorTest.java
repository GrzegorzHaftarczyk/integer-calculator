package pl.haftarczyk.calculator.antlr;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AntlrIntegerCalculatorTest {

    protected AntlrIntegerCalculator antlrIntegerCalculator;

    @BeforeEach
    void setUp() {
        antlrIntegerCalculator = new AntlrIntegerCalculator();
    }

    @ParameterizedTest
    @CsvSource({
            "2 + 3, 5, adding positive numbers",
            "3 * 2 + 1, 7, multiplication and addition",
            "3 * -2 + 6, 0, multiplying by a negative number",
            "3 + -2 * 4, -5, Sequence of actions performed",
            "2 - -3, 5, Subtracting a negative number",
            "3 / 2, 1, Integer division",
            "3 * 3 / 2 * 4, 16, Division with multiplication",
            "0 * -1, 0, Zero multiplied by a negative number",
            "6 / 7, 0, Dividing an integer by a larger number",
            "0 / 7, 0, Dividing zero"
    })
    void testCalculate(String expression, int expectedResult, String message) {
        int result = antlrIntegerCalculator.calculate(expression);

        assertEquals(expectedResult, result, message + ": " + expression);
    }

    @Test()
    void testShouldThrowExceptionWhenDivideByZero() {
        Exception exception = assertThrows(ArithmeticException.class, () ->
                antlrIntegerCalculator.calculate("3 / 0"));
        assertEquals("/ by zero", exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "2/3, don't contains space",
            "2 + 3a, contain character 'a'",
            "2 + 3 - b + 5, contain character 'b'",
            "2 -- 3, double minus",
            "2 --3, double negative",
            "2 * +3, unnecessary plus sign",
            "2 * + 3, additional plus sign",
            "2 * ( 2 + 3 ), curly brackets not supported",
            "2 ^ 3, power sign not supported"
    })
    void testShouldThrowExceptionWhenExpressionIsInvalid(String expression, String message) {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                antlrIntegerCalculator.calculate(expression), message);
        assertEquals("The expression is invalid.", exception.getMessage());
    }
}