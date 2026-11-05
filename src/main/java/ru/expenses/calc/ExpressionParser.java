package ru.expenses.calc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Разбирает арифметическое выражение из строки и вычисляет его с помощью {@link Calculator}.
 */
class ExpressionParser {

    private final Calculator calculator;

    ExpressionParser(Calculator calculator) {
        this.calculator = calculator;
    }

    BigDecimal evaluate(String expression) {
        List<String> tokens = tokenize(expression);
        if (tokens.size() != 3 || !isNumber(tokens.get(0)) || !isNumber(tokens.get(2))) {
            throw invalid(expression);
        }
        BigDecimal a = new BigDecimal(tokens.get(0));
        BigDecimal b = new BigDecimal(tokens.get(2));
        return switch (tokens.get(1)) {
            case "+" -> calculator.add(a, b);
            case "*" -> calculator.multiply(a, b);
            default -> throw invalid(expression);
        };
    }

    private static boolean isNumber(String token) {
        return token.matches("\\d+(\\.\\d+)?");
    }

    private static IllegalArgumentException invalid(String expression) {
        return new IllegalArgumentException("Invalid expression: " + expression);
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
