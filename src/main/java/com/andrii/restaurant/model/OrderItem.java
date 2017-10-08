package com.andrii.restaurant.model;

import java.math.BigDecimal;

public class OrderItem {

    private Dish dish;
    private int count;

    public OrderItem(Dish dish, int count) {
        this.dish = dish;
        this.count = count;
    }

    public Dish getDish() {
        return dish;
    }

    public int getCount() {
        return count;
    }

    public BigDecimal getAmount() {
        return dish.getPrice().multiply(BigDecimal.valueOf(count));
    }
}
