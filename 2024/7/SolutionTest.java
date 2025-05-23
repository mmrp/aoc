import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class calculatorTest {
    @Test
    void testSimpleAddition() {
        calculator sol = new calculator();
        assertEquals("3", sol.calculate("1+2"));
    }

    @Test
    void testSimpleSubtraction() {
        calculator sol = new calculator();
        assertEquals("2", sol.calculate("4-2"));
    }

    @Test
    void testMultiplication() {
        calculator sol = new calculator();
        assertEquals("12", sol.calculate("3*4"));
    }

    @Test
    void testDivision() {
        calculator sol = new calculator();
        assertEquals("4", sol.calculate("8/2"));
    }

    @Test
    void testParentheses() {
        calculator sol = new calculator();
        assertEquals("9", sol.calculate("(1+2)*3"));
    }

    @Test
    void testNestedParentheses() {
        calculator sol = new calculator();
        assertEquals("23", sol.calculate("(1+(4+5+2)-3)+(6+8)"));
    }

    @Test
    void testUnaryMinus() {
        calculator sol = new calculator();
        assertEquals("1", sol.calculate("-2+3"));
    }

    @Test
    void testDoubleUnaryMinus() {
        calculator sol = new calculator();
        assertEquals("2", sol.calculate("-(-2)"));
    }

    @Test
    void testMixedUnary() {
        calculator sol = new calculator();
        assertEquals("4", sol.calculate("2-(-3+1)"));
    }

    @Test
    void testZero() {
        calculator sol = new calculator();
        assertEquals("0", sol.calculate("0"));
    }

    @Test
    void testOnlyUnary() {
        calculator sol = new calculator();
        assertEquals("-2", sol.calculate("-2"));
    }

    @Test
    void testMultipleUnary() {
        calculator sol = new calculator();
        assertEquals("-2", sol.calculate("-(-(-2))"));
    }

    @Test
    void testNestedParenthesesComplex() {
        calculator sol = new calculator();
        assertEquals("35", sol.calculate("((2+3)*(4+(5-2)))"));
    }

    @Test
    void testAllOperatorsTogether() {
        calculator sol = new calculator();
        // 3+4*2/(1-5)^2^3
        // 1-5 = -4; 2^3 = 8; (-4)^8 = 65536; 4*2=8; 8/65536=0; 3+0=3
        assertEquals("3", sol.calculate("3+4*2/(1-5)^2^3"));
    }

    @Test
    void testLongUnaryChain() {
        calculator sol = new calculator();
        assertEquals("5", sol.calculate("-(-(-(-5)))"));
    }

    @Test
    void testMixUnaryBinary() {
        calculator sol = new calculator();
        assertEquals("-21", sol.calculate("-(3+4)*(-(2-5))"));
    }

    @Test
    void testDivisionByZero() {
        calculator sol = new calculator();
        assertEquals("0", sol.calculate("5/0"));
    }

    @Test
    void testModuloByZero() {
        calculator sol = new calculator();
        assertEquals("0", sol.calculate("5%0"));
    }

    @Test
    void testPowerOperator() {
        calculator sol = new calculator();
        // 2^(3^2) = 2^9 = 512
        assertEquals("512", sol.calculate("2^3^2"));
    }

    @Test
    void testSpacesAndLargeNumbers() {
        calculator sol = new calculator();
        assertEquals("5000", sol.calculate("  1000 + ( 2000 * 3 ) - 4000 / 2 "));
    }

    @Test
    void testNegativeNumbersInParentheses() {
        calculator sol = new calculator();
        assertEquals("6", sol.calculate("(-2)*(-3)"));
    }

    @Test
    void testMultipleConsecutiveOperators() {
        calculator sol = new calculator();
        assertEquals("3", sol.calculate("1--2"));
    }

    @Test
    void testComplexExpression() {
        calculator sol = new calculator();
        assertEquals("-350", sol.calculate("-3 + 4 * 2 / (1 - 5) ^ 2 ^ 3 + (8 + (3 * (4 + 5))) / 2 * -7 * (6 - 3) + 10 ^ (2 + -1)"));
    }
} 
