package ru.expenses.calc;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

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
        List<String> tokens = tokenize(expression);
        BigDecimal a = new BigDecimal(tokens.get(0));
        BigDecimal b = new BigDecimal(tokens.get(2));
        return switch (tokens.get(1)) {
            case "+" -> add(a, b);
            case "*" -> multiply(a, b);
            default -> throw new IllegalArgumentException("Unknown operator: " + tokens.get(1));
        };
    }

    private static List<String> tokenize(String expression) {
        List<String> tokens = new ArrayList<>();
        int i = 0;
        while (i < expression.length()) {
            char ch = expression.charAt(i);
            if (Character.isWhitespace(ch)) {
                i++;
            } else if (Character.isDigit(ch) || ch == '.') {
                int start = i;
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    i++;
                }
                tokens.add(expression.substring(start, i));
            } else {
                tokens.add(String.valueOf(ch));
                i++;
            }
        }
        return tokens;
    }
}
