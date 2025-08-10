package pl.haftarczyk.calculator.antlr;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AntlrIntegerCalculatorVisitorTest {

    private final AntlrIntegerCalculatorVisitor visitor = new AntlrIntegerCalculatorVisitor();

    private Integer evaluate(String expr) {
        var lexer = new IntegerCalculatorLexer(CharStreams.fromString(expr));
        var parser = new IntegerCalculatorParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.start();
        return visitor.visit(tree);
    }

    @ParameterizedTest()
    @CsvSource({
            "5, 5, single number",
            "3 + 5, 8, simple addition",
            "3 * 4 / 2, 6, multiplication and division",
            "3 - 4, -1, subtraction resulting in negative",
            "-2 * 3, -6, multiplication with negative number",
            "7, 7, positive number",
            "-5, -5, negative number",
            "4 + 5, 9, addition of two positive numbers",
            "4 - 5, -1, subtraction of two positive numbers",
            "4 * 5, 20, multiplication of two positive numbers",
            "10 / 5, 2, division of two positive numbers",
            "3 * 4 + 2, 14, operator precedence without parentheses",
            "-3 * -2 + 6, 12, handling negative numbers in expression"
    })
    void shouldEvaluateExpressions(String input, int expected, String description) {
        assertEquals(expected, evaluate(input), description);
    }

    @Test
    void shouldThrowWhenDividingByZero() {
        assertThrows(ArithmeticException.class, () -> evaluate("5 / 0"), "division by zero");
    }
}
