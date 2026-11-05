package ru.expenses.calc;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
