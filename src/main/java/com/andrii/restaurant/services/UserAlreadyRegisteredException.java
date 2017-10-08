package com.andrii.restaurant.services;

public class UserAlreadyRegisteredException extends ServiceException {

    public UserAlreadyRegisteredException(String login) {
        super("User with login " + login + " is already registered");
    }
}
