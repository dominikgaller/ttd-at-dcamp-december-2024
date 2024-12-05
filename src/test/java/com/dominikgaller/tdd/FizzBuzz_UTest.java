package com.dominikgaller.tdd;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class FizzBuzz_UTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4, 16, 32})
    void testFizzBuzz_normalNumbersArePrintedAsStrings(int number) {
        assertThat(FizzBuzz.fizzBuzz(number)).isEqualTo(Integer.toString(number));
    }
    @ParameterizedTest
    @ValueSource(ints = {3, 6, 9, 21})
    void testFizzBuzz_multiplesOfThreeReturnFizz(int number) {
        assertThat(FizzBuzz.fizzBuzz(number)).isEqualTo("Fizz");
    }
    @ParameterizedTest
    @ValueSource(ints = {5, 10, 20, 40})
    void testFizzBuzz_multiplesOfFiveReturnBuzz(int number) {
        assertThat(FizzBuzz.fizzBuzz(number)).isEqualTo("Buzz");
    }
    @ParameterizedTest
    @ValueSource(ints = {15, 30, 45, 150})
    void testFizzBuzz_multiplesOfBothThreeAndFiveReturnFizzBuzz(int number) {
        assertThat(FizzBuzz.fizzBuzz(number)).isEqualTo("FizzBuzz");
    }
}