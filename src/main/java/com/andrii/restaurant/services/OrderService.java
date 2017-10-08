package com.andrii.restaurant.services;

import com.andrii.restaurant.model.Cart;

public interface OrderService {

    void processOrder(Cart cart);
}
