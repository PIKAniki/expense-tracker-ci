package ru.expenses.calc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class CalculatorTest {

    private final Calculator calculator = new Calculator();

    private static BigDecimal n(String value) {
        return new BigDecimal(value);
    }

    private static void assertResult(String expected, BigDecimal actual) {
        assertEquals(n(expected).stripTrailingZeros(), actual.stripTrailingZeros());
    }

    @ParameterizedTest
    @CsvSource({"2, 3, 5", "-4, 1, -3", "0.1, 0.2, 0.3"})
    void addsNumbers(String a, String b, String expected) {
        assertResult(expected, calculator.add(n(a), n(b)));
    }

    @ParameterizedTest
    @CsvSource({"10, 4, 6"})
    void subtractsNumbers(String a, String b, String expected) {
        assertResult(expected, calculator.subtract(n(a), n(b)));
    }

    @ParameterizedTest
    @CsvSource({"3, -5, -15"})
    void multipliesNumbers(String a, String b, String expected) {
        assertResult(expected, calculator.multiply(n(a), n(b)));
    }

    @ParameterizedTest
    @CsvSource({"12, 4, 3", "1, 3, 0.3333333333"})
    void dividesNumbers(String a, String b, String expected) {
        assertResult(expected, calculator.divide(n(a), n(b)));
    }

    @Test
    void rejectsDivisionByZero() {
        ArithmeticException e = assertThrows(ArithmeticException.class, () -> calculator.divide(n("1"), n("0")));
        assertEquals("Division by zero", e.getMessage());
    }

    @Test
    void calculatesSimpleExpression() {
        assertResult("5", calculator.calculate("2 + 3"));
    }

    @Test
    void calculatesExpressionWithoutSpacesBetweenTokens() {
        assertResult("14", calculator.calculate("  7*2 "));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "2 + + 3", "abc"})
    void rejectsInvalidExpression(String expression) {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> calculator.calculate(expression));
        assertTrue(e.getMessage().startsWith("Invalid expression"), e.getMessage());
    }

    @ParameterizedTest
    @CsvSource({"2 + 3 * 4, 14", "10 - 4 / 2, 8"})
    void respectsOperatorPrecedence(String expression, String expected) {
        assertResult(expected, calculator.calculate(expression));
    }

    @ParameterizedTest
    @CsvSource({"(2 + 3) * 4, 20", "2 * (3 + (4 - 1)), 12"})
    void calculatesExpressionWithParentheses(String expression, String expected) {
        assertResult(expected, calculator.calculate(expression));
    }

    @ParameterizedTest
    @CsvSource({"-5 + 2, -3", "2 * -3, -6", "-(2 + 3), -5"})
    void supportsUnaryMinus(String expression, String expected) {
        assertResult(expected, calculator.calculate(expression));
    }
}
