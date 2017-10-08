package com.andrii.restaurant.utils;

import com.andrii.restaurant.controller.IndexServlet;
import com.andrii.restaurant.persistent.JdbcUserDao;
import com.andrii.restaurant.persistent.UserDao;
import com.andrii.restaurant.services.UserService;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;

public class RestaurantUtils {
    private MysqlDataSource dataSource;
    private UserDao userDao;
    private UserService userService;
    private IndexServlet indexServlet;

    RestaurantUtils() {
        dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/restaurant_db");
        dataSource.setUser("root");
        dataSource.setPassword("root");
    }

    public RestaurantUtils setUserDao(UserDao userDao) {
        this.userDao = userDao;
        return this;
    }

    public RestaurantUtils setUserService(UserService userService) {
        this.userService = userService;
        return this;
    }

    public RestaurantUtils setIndexServlet(IndexServlet indexServlet) {
        this.indexServlet = indexServlet;
        return this;
    }
}
