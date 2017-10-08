package com.andrii.restaurant.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cart implements Iterable<OrderItem> {

    private final long userId;
    private List<OrderItem> orderItems = new ArrayList<>();

    public Cart(long userId) {
        this.userId = userId;
    }

    public Cart add(OrderItem orderItem) {
        orderItems.add(orderItem);
        return this;
    }

    public boolean isEmpty() {
        return orderItems.isEmpty();
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public Cart setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
        return this;
    }

    public Order buildOrder() {
        return new Order()
                .setOrderAmount(getAmount())
                .setUserId(userId)
                .setOrderDate(LocalDateTime.now())
                .setConfirmed(false);
    }

    public BigDecimal getAmount() {
        BigDecimal sum = BigDecimal.ZERO;
        for (OrderItem orderItem : orderItems) {
            sum = sum.add(orderItem.getAmount());
        }
        return sum;
    }

    @Override
    public Iterator<OrderItem> iterator() {
        return orderItems.iterator();
    }
}
