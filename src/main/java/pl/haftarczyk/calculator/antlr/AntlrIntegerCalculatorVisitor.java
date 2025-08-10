package pl.haftarczyk.calculator.antlr;

public class AntlrIntegerCalculatorVisitor extends IntegerCalculatorBaseVisitor<Integer> {

    @Override
    public Integer visitStart(IntegerCalculatorParser.StartContext ctx) {
        return super.visit(ctx.expression());
    }

    @Override
    public Integer visitNumber(IntegerCalculatorParser.NumberContext ctx) {
        return Integer.parseInt(ctx.NUMBER().getText());
    }

    @Override
    public Integer visitAdditionOrSubtraction(IntegerCalculatorParser.AdditionOrSubtractionContext ctx) {
        if (ctx.operator.getText().equals("+")) {
            return visit(ctx.left) + visit(ctx.right);
        }

        return visit(ctx.left) - visit(ctx.right);
    }

    @Override
    public Integer visitMultiplicationOrDivision(IntegerCalculatorParser.MultiplicationOrDivisionContext ctx) {
        if (ctx.operator.getText().equals("*")) {
            return visit(ctx.left) * visit(ctx.right);
        }

        return visit(ctx.left) / visit(ctx.right);
    }
}
