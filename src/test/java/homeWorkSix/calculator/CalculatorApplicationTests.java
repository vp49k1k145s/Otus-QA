package homeWorkSix.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class CalculatorApplicationTests {

    private Calculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    @DisplayName("test sum")
    void test1() {
        Integer first = 3;
        Integer second = 7;
        Integer expected = 10;

        assert calculator.sum(first, second).equals(expected);
    }

    @Test
    @DisplayName("test subtraction")
    void test2() {
        Integer first = 12;
        Integer second = 4;
        Integer expected = 8;

        assert calculator.subtraction(first, second).equals(expected);
    }

    @Test
    @DisplayName("test multiply")
    void test3() {
        Integer first = 5;
        Integer second = 3;
        Integer expected = 15;

        assert calculator.multiply(first, second).equals(expected);
    }

    @Test
    @DisplayName("test division")
    void test4() {
        Integer first = 30;
        Integer second = 6;
        Integer expected = 5;

        assert calculator.division(first, second).equals(expected);
    }
    @Test
    @DisplayName("test exponentiation")
    void test5() {
        Integer first = 2;
        Integer second = 2;
        Integer expected = 4;

        assert calculator.exponentiation((double) first, (double)second).equals((double)expected);
    }
    @Test
    @DisplayName("test square")
    void test6() {
        Integer first = 16;
        Integer expected = 4;

        assert calculator.square((double) first).equals((double)expected);
    }
}
