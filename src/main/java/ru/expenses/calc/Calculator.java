package ru.expenses.calc;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculator {

    /** Число знаков после запятой в результате деления. */
    static final int SCALE = 10;

    public BigDecimal add(BigDecimal a, BigDecimal b) {
        return a.add(b);
    }

    public BigDecimal subtract(BigDecimal a, BigDecimal b) {
        return a.subtract(b);
    }

    public BigDecimal multiply(BigDecimal a, BigDecimal b) {
        return a.multiply(b);
    }

    public BigDecimal divide(BigDecimal a, BigDecimal b) {
        if (b.signum() == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return a.divide(b, SCALE, RoundingMode.HALF_UP);
    }

    public BigDecimal calculate(String expression) {
        return new ExpressionParser(this).evaluate(expression);
    }
}
