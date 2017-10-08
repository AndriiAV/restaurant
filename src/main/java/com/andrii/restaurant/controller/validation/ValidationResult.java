package com.andrii.restaurant.controller.validation;

public class ValidationResult {

    private final boolean success;
    private final String errorMessage;

    private ValidationResult(boolean success, String errorMessage) {
        this.errorMessage = errorMessage;
        this.success = success;
    }

    public static ValidationResult success() {
        return new ValidationResult(true, null);
    }

    public static ValidationResult error(String errorMessage) {
        return new ValidationResult(false, errorMessage);
    }

    public boolean isSuccess() {
        return success;
    }

    public boolean isError() {
        return !success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return "ValidationResult{" +
                "success=" + success +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
