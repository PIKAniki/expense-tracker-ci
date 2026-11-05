package ru.expenses.calc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @Test
    void addsTwoPositiveNumbers() {
        assertEquals(5, calculator.add(2, 3));
    }

    @Test
    void addsNegativeAndPositiveNumber() {
        assertEquals(-3, calculator.add(-4, 1));
    }

    @Test
    void subtractsNumbers() {
        assertEquals(6, calculator.subtract(10, 4));
    }

    @Test
    void multipliesNumbers() {
        assertEquals(-15, calculator.multiply(3, -5));
    }

    @Test
    void dividesNumbers() {
        assertEquals(3, calculator.divide(12, 4));
    }

    @Test
    void rejectsDivisionByZero() {
        ArithmeticException e = assertThrows(ArithmeticException.class, () -> calculator.divide(1, 0));
        assertEquals("Division by zero", e.getMessage());
    }
}
