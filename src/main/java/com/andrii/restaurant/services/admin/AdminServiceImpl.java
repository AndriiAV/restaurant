package com.andrii.restaurant.services.admin;

import com.andrii.restaurant.model.Order;
import com.andrii.restaurant.persistent.order.OrderDao;

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
