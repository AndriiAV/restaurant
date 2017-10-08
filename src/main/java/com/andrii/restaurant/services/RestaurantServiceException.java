package com.andrii.restaurant.services;

public class RestaurantServiceException extends RuntimeException {

    public RestaurantServiceException(String message) {
        super(message);
    }

    public RestaurantServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestaurantServiceException(Throwable cause) {
        super(cause);
    }
}
