package ru.expenses.calc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Разбирает арифметическое выражение из строки и вычисляет его с помощью {@link Calculator}.
 * Разбор выполняется рекурсивным спуском: выражение – сумма слагаемых, слагаемое – произведение чисел,
 * поэтому умножение и деление выполняются раньше сложения и вычитания.
 */
class ExpressionParser {

    private final Calculator calculator;
    private final String expression;
    private final List<String> tokens;
    private int pos;

    ExpressionParser(Calculator calculator, String expression) {
        this.calculator = calculator;
        this.expression = expression;
        this.tokens = tokenize(expression);
    }

    BigDecimal evaluate() {
        BigDecimal result = parseExpression();
        if (pos != tokens.size()) {
            throw invalid();
        }
        return result;
    }

    private BigDecimal parseExpression() {
        BigDecimal result = parseTerm();
        while (peek("+") || peek("-")) {
            String op = tokens.get(pos++);
            BigDecimal right = parseTerm();
            result = op.equals("+") ? calculator.add(result, right) : calculator.subtract(result, right);
        }
        return result;
    }

    private BigDecimal parseTerm() {
        BigDecimal result = parseNumber();
        while (peek("*") || peek("/")) {
            String op = tokens.get(pos++);
            BigDecimal right = parseNumber();
            result = op.equals("*") ? calculator.multiply(result, right) : calculator.divide(result, right);
        }
        return result;
    }

    private BigDecimal parseNumber() {
        if (pos >= tokens.size() || !isNumber(tokens.get(pos))) {
            throw invalid();
        }
        return new BigDecimal(tokens.get(pos++));
    }

    private boolean peek(String token) {
        return pos < tokens.size() && tokens.get(pos).equals(token);
    }

    private static boolean isNumber(String token) {
        return token.matches("\\d+(\\.\\d+)?");
    }

    private IllegalArgumentException invalid() {
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
