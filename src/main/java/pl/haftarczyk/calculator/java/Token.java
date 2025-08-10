package pl.haftarczyk.calculator.java;

sealed interface Token permits NumberToken, OperatorToken {
}

record NumberToken(int value) implements Token {
}

record OperatorToken(OperatorType type) implements Token {
}
