import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class CalculatorTests {

    Calculator suite;

    @BeforeAll
    public static void initSuite() {
        System.out.println("Running CalculatorTests");
    }

    @AfterAll
    public static void completeSuite() {
        System.out.println("CalculatorTests completed");
    }

    @BeforeEach
    public void init() {
        System.out.println("Test started");
        suite = new Calculator();
    }

    @AfterEach
    public void finished() {
        System.out.println("Test finished");
    }

    @Test
    public void testPlus() {
        int a = 4, b = 3, expected = 7;
        int result = suite.plus.apply(a, b);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testDivide_ThrowsException() {
        int a = 3, b = 0;
        Assertions.assertThrows(ArithmeticException.class, () -> suite.divide.apply(a, b));
    }

    @Test
    public void testAbs() {
        int a = -1, trueExpected = 1, falseExpected = -1;
        int trueResult = suite.abs.apply(a);
        int falseResult = suite.abs.apply(a);
        Assertions.assertEquals(trueExpected, trueResult);
        Assertions.assertNotEquals(falseExpected, falseResult);
    }

    @ParameterizedTest
    @MethodSource("sourse")
    public void testIsPositive(int a) {
        boolean expected = suite.isPositive.test(a);
        Assertions.assertTrue(expected);
    }

    public static Stream<Arguments> sourse() {
        return Stream.of(Arguments.of(1), Arguments.of(3), Arguments.of(89));
    }
}