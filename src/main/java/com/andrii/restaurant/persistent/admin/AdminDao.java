package com.andrii.restaurant.persistent.admin;

import com.andrii.restaurant.model.Admin;

public interface AdminDao {
    /**
     * @return user or null
     */
    Admin findByLogin(String login);
}
