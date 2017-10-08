package com.andrii.restaurant.services;

import com.andrii.restaurant.model.Admin;

public interface AdminUserService {
    /**
     * @return registered admin or null
     */
    Admin findRegisteredAdmin(String login, String password);
}
