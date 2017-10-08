package com.andrii.restaurant.services.impl;

import com.andrii.restaurant.model.Cart;
import com.andrii.restaurant.model.Order;
import com.andrii.restaurant.persistent.OrderDao;
import com.andrii.restaurant.services.OrderService;

public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;

    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public void processOrder(Cart cart) {
        Order order = cart.buildOrder();
        orderDao.save(order);
    }
}
