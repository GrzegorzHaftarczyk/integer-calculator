package pl.haftarczyk.calculator.antlr;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;
import pl.haftarczyk.calculator.common.IntegerCalculator;

public class AntlrIntegerCalculator implements IntegerCalculator {

    IntegerCalculatorVisitor<Integer> antlrIntegerCalculatorVisitor = new AntlrIntegerCalculatorVisitor();

    private static IntegerCalculatorParser getIntegerCalculatorParser(CharStream chars) {
        Lexer lexer = new IntegerCalculatorLexer(chars);
        lexer.removeErrorListeners();
        lexer.addErrorListener(ThrowingErrorListener.INSTANCE);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        IntegerCalculatorParser parser = new IntegerCalculatorParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(ThrowingErrorListener.INSTANCE);
        return parser;
    }

    @Override
    public int calculate(String expression) {
        return calculateAntlrInteger(expression);
    }

    private int calculateAntlrInteger(String expression) {
        CharStream chars = CharStreams.fromString(expression);
        try {
            IntegerCalculatorParser parser = getIntegerCalculatorParser(chars);

            ParseTree tree = parser.start();
            return antlrIntegerCalculatorVisitor.visit(tree);
        } catch (ParseCancellationException ex) {
            throw new IllegalArgumentException("The expression is invalid.", ex);
        }
    }

}
