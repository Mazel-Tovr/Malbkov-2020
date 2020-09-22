package com.epam.library.businesslayer;

public class Validation {

    public static boolean isPositiveNumber(String number) {
        try {
            return Double.parseDouble(number) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
