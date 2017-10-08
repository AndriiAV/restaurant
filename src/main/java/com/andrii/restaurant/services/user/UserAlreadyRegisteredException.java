package com.andrii.restaurant.services.user;

import com.andrii.restaurant.services.RestaurantServiceException;

public class UserAlreadyRegisteredException extends RestaurantServiceException {

    public UserAlreadyRegisteredException(String login) {
        super("User with login " + login + " is already registered");
    }
}
