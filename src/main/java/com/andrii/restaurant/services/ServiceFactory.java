package com.andrii.restaurant.services;

import com.andrii.restaurant.persistent.DaoFactory;
import com.andrii.restaurant.services.impl.AdminServiceImpl;
import com.andrii.restaurant.services.impl.AdminUserServiceImpl;
import com.andrii.restaurant.services.impl.OrderServiceImpl;
import com.andrii.restaurant.services.impl.UserServiceImpl;

public class ServiceFactory {

    private static ServiceFactory instance = new ServiceFactory();

    public synchronized static ServiceFactory getInstance() {
        return instance;
    }

    private ServiceFactory() {
    }

    private final DaoFactory daoFactory = DaoFactory.getInstance();

    public UserService getUserService() {
        return new UserServiceImpl(daoFactory.getUserDao());
    }

    public AdminUserService getAdminUserService() {
        return new AdminUserServiceImpl(daoFactory.getAdminDao());
    }

    public AdminService getAdminService() {
        return new AdminServiceImpl(daoFactory.getOrderDao());
    }

    public OrderService getOrderService() {
        return new OrderServiceImpl(daoFactory.getOrderDao());
    }
}
