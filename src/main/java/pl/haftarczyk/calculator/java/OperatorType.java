package pl.haftarczyk.calculator.java;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.BiFunction;

enum OperatorType {
    ADD("+", 1, Math::addExact),
    SUBTRACT("-", 1, Math::subtractExact),
    MULTIPLY("*", 2, Math::multiplyExact),
    DIVIDE("/", 2, Math::divideExact);

    private final String symbol;
    private final int precedence;
    private final BiFunction<Integer, Integer, Integer> operation;

    OperatorType(String symbol, int precedence, BiFunction<Integer, Integer, Integer> operation) {
        this.symbol = symbol;
        this.precedence = precedence;
        this.operation = operation;
    }

    public static Optional<OperatorType> fromSymbol(String symbol) {
        return Arrays.stream(values())
                .filter(op -> op.symbol.equals(symbol))
                .findFirst();
    }

    public int getPrecedence() {
        return precedence;
    }

    public int apply(int left, int right) {
        return operation.apply(left, right);
    }
}