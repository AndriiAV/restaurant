package com.andrii.restaurant.persistent;

import com.andrii.restaurant.model.Admin;

public interface AdminDao {
    /**
     * @return user or null
     */
    Admin findByLogin(String login);
}
