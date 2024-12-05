package com.dominikgaller.tdd;

/**
 * Utility class for transforming numbers into their FizzBuzz representation.
 * This class provides a static method to compute the FizzBuzz value of an integer
 * and does not support instantiation.
 */
public final class FizzBuzz {

    private FizzBuzz() {
        throw new UnsupportedOperationException("Suppress default constructor for noninstantiability");
    }

    /**
     * Returns "Fizz" if the given number is a multiple of three, "Buzz" if it is a multiple of five,
     * or "FizzBuzz" if it is a multiple of both three and five. If the number is neither, the method
     * returns the number as a string.
     *
     * @param number the number to be converted into a FizzBuzz string.
     * @return a string representing the FizzBuzz value of the given number.
     */
    public static String fizzBuzz(int number) {
        StringBuilder result = new StringBuilder();

        if (isMultipleOfThree(number)) {
            result.append("Fizz");
        }
        if (isMultipleOfFive(number)) {
            result.append("Buzz");
        }

        return !result.isEmpty() ? result.toString() : Integer.toString(number);
    }

    private static boolean isMultipleOfFive(int number) {
        return number % 5 == 0;
    }

    private static boolean isMultipleOfThree(int number) {
        return number % 3 == 0;
    }


}