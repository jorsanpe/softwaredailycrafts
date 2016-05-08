import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class StringCalculatorTest {
    @Test
    public void shouldReturnZeroWhenStringPassedIsEmpty() {
        StringCalculator stringCalculator = new StringCalculator(",");

        int ret = stringCalculator.add("");

        assertThat(ret, is(0));
    }

    @Test
    public void shouldReturnIntegerValueWhenStringPassedContainsOneNumber() {
        StringCalculator stringCalculator = new StringCalculator(",");

        int ret = stringCalculator.add("1");

        assertThat(ret, is(1));
    }

    @Test
    public void shouldReturnSumWhenStringPassedContainsTwoNumbers() {
        StringCalculator stringCalculator = new StringCalculator(",");

        int ret = stringCalculator.add("1,2");

        assertThat(ret, is(3));
    }

    @Test
    public void shouldReturnSumWhenStringPassedContainsManyNumbers() {
        StringCalculator stringCalculator = new StringCalculator(",");

        int ret = stringCalculator.add("1,2,3,4,5");

        assertThat(ret, is(15));
    }

    @Test
    public void shouldReturnSumWhenStringPassedContainsManyNumbersAndNewlines() {
        StringCalculator stringCalculator = new StringCalculator(",");

        int ret = stringCalculator.add("1\n2,3,4,5");

        assertThat(ret, is(15));
    }

    @Test
    public void shouldReturnSumWhenStringPassedContainsManyNumbersAndDifferentDelimiter() {
        StringCalculator stringCalculator = new StringCalculator("+");

        int ret = stringCalculator.add("5+6+10");

        assertThat(ret, is(21));
    }
}