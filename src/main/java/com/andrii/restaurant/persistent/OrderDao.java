package com.andrii.restaurant.persistent;

import com.andrii.restaurant.model.Order;

import java.util.List;

public interface OrderDao {

    Order findById(int id);
    Order save(Order order);
    void update(Order order);

    List<Order> findOrdersWithConfirmation(boolean confirmed);
}
