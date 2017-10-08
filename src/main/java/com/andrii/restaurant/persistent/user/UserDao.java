package com.andrii.restaurant.persistent.user;

import com.andrii.restaurant.model.User;
import com.andrii.restaurant.persistent.NotFoundPersistenceException;

public interface UserDao {
    /**
     * @throws NotFoundPersistenceException
     */
    User findById(long userId);
    User save(User user);

    /**
     * @return user or null
     */
    User findByLogin(String login);
}
