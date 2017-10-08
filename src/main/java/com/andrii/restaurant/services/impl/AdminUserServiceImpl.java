package com.andrii.restaurant.services.impl;

import com.andrii.restaurant.model.Admin;
import com.andrii.restaurant.persistent.AdminDao;
import com.andrii.restaurant.services.AdminUserService;

public class AdminUserServiceImpl implements AdminUserService {
    @Override
    public Admin findRegisteredAdmin(String login, String password) {
        Admin userByLogin = adminDao.findByLogin(login);
        boolean isRegistered = userByLogin != null && userByLogin.getPassword().equals(password);
        return isRegistered ? userByLogin : null;
    }

    private final AdminDao adminDao;

    public AdminUserServiceImpl(AdminDao userDao) {
        this.adminDao = userDao;
    }

}
