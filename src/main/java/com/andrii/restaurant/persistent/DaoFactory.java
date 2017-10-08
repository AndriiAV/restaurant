package com.andrii.restaurant.persistent;

import com.andrii.restaurant.persistent.admin.AdminDao;
import com.andrii.restaurant.persistent.admin.JdbcAdminDao;
import com.andrii.restaurant.persistent.dish.DishDao;
import com.andrii.restaurant.persistent.dish.JdbcDishDao;
import com.andrii.restaurant.persistent.order.JdbcOrderDao;
import com.andrii.restaurant.persistent.order.OrderDao;
import com.andrii.restaurant.persistent.user.JdbcUserDao;
import com.andrii.restaurant.persistent.user.UserDao;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;

public class DaoFactory {

    private static DaoFactory instance = new DaoFactory();

    public synchronized static DaoFactory getInstance() {
        return instance;
    }

    private DaoFactory() {
    }

    private DataSource dataSource;

    public OrderDao getOrderDao() {
        return new JdbcOrderDao(getDataSource());
    }

    public UserDao getUserDao() {
        return new JdbcUserDao(getDataSource());
    }

    public AdminDao getAdminDao() {
        return new JdbcAdminDao(getDataSource());
    }

    public DishDao getDishDao() {
        return new JdbcDishDao(getDataSource());
    }

    private DataSource getDataSource() {
        if (dataSource == null) {
            dataSource = createDataSource();
        }
        return dataSource;
    }

    private DataSource createDataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/restaurant_db");
        dataSource.setUser("root");
        dataSource.setPassword("root");
        return dataSource;
    }
}
