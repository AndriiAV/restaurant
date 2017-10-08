package com.andrii.restaurant.persistent;

public class NotFoundPersistenceException extends RestaurantPersistenceException {
    public NotFoundPersistenceException(String message) {
        super(message);
    }

    public NotFoundPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundPersistenceException(Throwable cause) {
        super(cause);
    }
}
