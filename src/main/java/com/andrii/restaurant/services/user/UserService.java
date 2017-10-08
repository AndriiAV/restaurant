package com.andrii.restaurant.services.user;

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
