package com.andrii.restaurant.controller.validation;

public class ValidationUtils {

    private ValidationUtils() {}

    public static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean validate(String s, String regexp) {
        // TODO
        throw new UnsupportedOperationException();
    }

    public static boolean containsEmbeddedScripts(String s) {
        // TODO
        throw new UnsupportedOperationException();
    }
}
