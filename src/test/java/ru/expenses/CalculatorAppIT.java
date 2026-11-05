package ru.expenses;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Проверяет команду calc приложения целиком, через {@link Main#main(String[])}.
 */
class CalculatorAppIT {

    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final ByteArrayOutputStream err = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(out, true, StandardCharsets.UTF_8));
        System.setErr(new PrintStream(err, true, StandardCharsets.UTF_8));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void calcCommandPrintsResult() {
        Main.main(new String[] {"calc", "(2 + 3) * 4"});

        assertEquals("20", out.toString(StandardCharsets.UTF_8).strip());
    }

    @Test
    void calcCommandPrintsRoundedQuotient() {
        Main.main(new String[] {"calc", "1 / 3"});

        assertEquals("0.3333333333", out.toString(StandardCharsets.UTF_8).strip());
    }

    @Test
    void calcCommandReportsInvalidExpression() {
        Main.main(new String[] {"calc", "2 + + 3"});

        assertEquals("Error: Invalid expression: 2 + + 3", err.toString(StandardCharsets.UTF_8).strip());
    }
}
