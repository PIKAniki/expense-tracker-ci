package ru.expenses.calc;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Калькулятор: четыре арифметические операции над {@link BigDecimal} и вычисление выражений из строки.
 */
public class Calculator {

    /** Число знаков после запятой в результате деления. */
    static final int SCALE = 10;

    /** Сумма {@code a + b}. */
    public BigDecimal add(BigDecimal a, BigDecimal b) {
        return a.add(b);
    }

    /** Разность {@code a - b}. */
    public BigDecimal subtract(BigDecimal a, BigDecimal b) {
        return a.subtract(b);
    }

    /** Произведение {@code a * b}. */
    public BigDecimal multiply(BigDecimal a, BigDecimal b) {
        return a.multiply(b);
    }

    /**
     * Частное {@code a / b}, округлённое до {@value #SCALE} знаков после запятой.
     *
     * @throws ArithmeticException при делении на ноль
     */
    public BigDecimal divide(BigDecimal a, BigDecimal b) {
        if (b.signum() == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return a.divide(b, SCALE, RoundingMode.HALF_UP);
    }

    /**
     * Вычисляет выражение вида {@code (2 + 3) * -4}: числа, операции + - * /, скобки и унарный минус.
     *
     * @throws IllegalArgumentException если выражение записано неверно
     */
    public BigDecimal calculate(String expression) {
        return new ExpressionParser(this, expression).evaluate();
    }
}
