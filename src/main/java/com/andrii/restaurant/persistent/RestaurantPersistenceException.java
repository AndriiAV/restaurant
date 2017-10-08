package com.andrii.restaurant.persistent;

public class RestaurantPersistenceException extends RuntimeException{
    public RestaurantPersistenceException(String message) {
        super(message);
    }

    public RestaurantPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestaurantPersistenceException(Throwable cause) {
        super(cause);
    }
}
