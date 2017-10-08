package com.andrii.restaurant.persistent;

import com.andrii.restaurant.model.User;

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
