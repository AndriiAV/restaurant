package com.andrii.restaurant.services.impl;

import com.andrii.restaurant.model.Order;
import com.andrii.restaurant.persistent.OrderDao;
import com.andrii.restaurant.services.AdminService;

public class AdminServiceImpl implements AdminService {

    private final OrderDao orderDao;

    public AdminServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public void confirmOrder(int orderId) {
        Order order = orderDao.findById(orderId);
        order.setConfirmed(true);
        orderDao.update(order);
    }
}
