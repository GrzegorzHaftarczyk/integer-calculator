# Integer Calculator

Two implementations of simple calculators. One is in pure Java, the other uses antlr.

The calculator supports basic mathematical operations on integers: `+`, `-`, `*`, `/`.

---

## Features

- Supported operators:
    - Addition (`+`)
    - Subtraction (`-`)
    - Multiplication (`*`)
    - Integer division (`/`) – result of `3 / 2` is `1`
- Supports negative numbers (e.g., `3 * -2`, `3 - -2`)
- Requires each number and operator to be separated by a space (e.g., `3 + 4`, not `3+4`)
- Detects and reports errors:
    - Null or blank expressions
    - Missing operator between numbers (`3 4`)
    - Unexpected tokens (e.g., `3 * +3`)
    - Expression ending with an operator (`3 +`)

---

## Usage Examples

### JavaIntegerCalculator

```java
public static void main(String[] args) {
    IntegerCalculator integerCalculator = new JavaIntegerCalculator();
    
    System.out.println(integerCalculator.calculate("3 + 4 * 2"));     // 11
    System.out.println(integerCalculator.calculate("3 - -2"));        // 5
}
```

### AntlrIntegerCalculator

```java
public static void main(String[] args) {
    IntegerCalculator antlrIntegerCalculator = new AntlrIntegerCalculator();

    System.out.println(antlrIntegerCalculator.calculate("3 + 4 * 2"));     // 11
    System.out.println(antlrIntegerCalculator.calculate("3 - -2"));        // 5
}
```

# Input Format

Each element in the expression must be separated by a single space:

## Valid:

```
3 + 4
3 * -2
```

## Invalid:

```
3 + 4
3 * -2
```

# Tests

Run tests (Maven):

```bash
mvn test
```

# Requirements

The project uses Java 24, but the minimum Java version required to run the project is Java 21 (due to sealed types and switch pattern matching).
