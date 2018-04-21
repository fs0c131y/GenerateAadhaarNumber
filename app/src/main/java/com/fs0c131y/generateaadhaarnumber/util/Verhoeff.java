package com.fs0c131y.generateaadhaarnumber.util;

/**
 * The Verhoeff algorithm, a checksum formula for error detection first
 * published in 1969, was developed by Dutch mathematician Jacobus Verhoeff.
 * Like the more widely known Luhn algorithm, it works with strings of decimal
 * digits of any length. It detects all single-digit errors and all
 * transposition errors involving two adjacent digits.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Verhoeff_algorithm/">More Info</a>
 * @see <a href="http://en.wikipedia.org/wiki/Dihedral_group">Dihedral Group</a>
 * @see <a href="http://mathworld.wolfram.com/DihedralGroupD5.html">Dihedral Group Order 10</a>
 * @author Colm Rice
 */
public class Verhoeff {
    /**
     * The multiplication table.
     */
    private static int[][] d = new int[][] {
            { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 },
            { 1, 2, 3, 4, 0, 6, 7, 8, 9, 5 },
            { 2, 3, 4, 0, 1, 7, 8, 9, 5, 6 },
            { 3, 4, 0, 1, 2, 8, 9, 5, 6, 7 },
            { 4, 0, 1, 2, 3, 9, 5, 6, 7, 8 },
            { 5, 9, 8, 7, 6, 0, 4, 3, 2, 1 },
            { 6, 5, 9, 8, 7, 1, 0, 4, 3, 2 },
            { 7, 6, 5, 9, 8, 2, 1, 0, 4, 3 },
            { 8, 7, 6, 5, 9, 3, 2, 1, 0, 4 },
            { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 }
    };

    /**
     * The inverse table.
     */
    private static int[] inv = new int[] { 0, 4, 3, 2, 1, 5, 6, 7, 8, 9 };

    /**
     * The permutation table.
     */
    private static int[][] p = new int[][] {
            { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 },
            { 1, 5, 7, 6, 2, 8, 3, 0, 9, 4 },
            { 5, 8, 0, 3, 7, 9, 6, 1, 4, 2 },
            { 8, 9, 1, 6, 0, 4, 3, 5, 2, 7 },
            { 9, 4, 5, 3, 1, 2, 6, 8, 7, 0 },
            { 4, 2, 8, 6, 5, 7, 3, 9, 0, 1 },
            { 2, 7, 9, 3, 8, 0, 6, 4, 1, 5 },
            { 7, 0, 4, 6, 9, 1, 3, 2, 5, 8 }
    };

    /**
     * Generates the Verhoeff digit for the provided numeric string.
     *
     * @param number The numeric string data for Verhoeff compliance check.
     * @return The generated Verhoeff digit for the provided numeric string.
     */
    public static String generateVerhoeff(String number) {
        int checksum = 0;
        int[] StringToReversedIntArray = StringToReversedIntArray(number);
        for (int i = 0; i < StringToReversedIntArray.length; i++) {
            checksum = d[checksum][p[(i + 1) % 8][StringToReversedIntArray[i]]];
        }
        return Integer.toString(inv[checksum]);
    }

    /**
     * Validates that an entered number is Verhoeff compliant.
     *
     * @param number The numeric string data for Verhoeff compliance check.
     * @return TRUE if the provided number is Verhoeff compliant.
     */
    public static boolean validateVerhoeff(String number) {
        int checksum = 0;
        int[] StringToReversedIntArray = StringToReversedIntArray(number);
        for (int i = 0; i < StringToReversedIntArray.length; i++) {
            checksum = d[i][p[i % 8][StringToReversedIntArray[i]]];
        }
        return checksum == 0;
    }

    /**
     * Converts a string to a reversed integer array.
     *
     * @param number The numeric string data converted to reversed int array.
     * @return Integer array containing the digits in the numeric string
     * provided in reverse.
     */
    private static int[] StringToReversedIntArray(String number) {
        int[] myArray = new int[number.length()];
        for (int i = 0; i < number.length(); i++) {
            myArray[i] = Integer.parseInt(number.substring(i, i + 1));
        }
        return Reverse(myArray);
    }

    /**
     * Reverses an int array.
     *
     * @param myArray The input array which needs to be reversed
     * @return The array provided in reverse order.
     */
    private static int[] Reverse(int[] myArray) {
        int[] reversed = new int[myArray.length];
        for (int i = 0; i < myArray.length; i++) {
            reversed[i] = myArray[myArray.length - (i + 1)];
        }
        return reversed;
    }
}
