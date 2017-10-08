package com.andrii.restaurant.services;

import com.andrii.restaurant.model.User;

public interface UserService {

    /**
     * @throws UserAlreadyRegisteredException
     */
    void register(User user);

    /**
     * @return registered user or null
     */
    User findRegisteredUser(String login, String password);

    void delete(int id);

}
