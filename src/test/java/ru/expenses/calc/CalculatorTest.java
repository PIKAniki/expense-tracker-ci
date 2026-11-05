package ru.expenses.calc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
    @CsvSource({"12, 4, 3"})
    void dividesNumbers(String a, String b, String expected) {
        assertResult(expected, calculator.divide(n(a), n(b)));
    }

    @Test
    void rejectsDivisionByZero() {
        ArithmeticException e = assertThrows(ArithmeticException.class, () -> calculator.divide(n("1"), n("0")));
        assertEquals("Division by zero", e.getMessage());
    }
}
