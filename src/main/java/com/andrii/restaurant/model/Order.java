package com.andrii.restaurant.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

public class Order {
    private Integer orderId;
    private long userId;
    private LocalDateTime orderDate;
    private BigDecimal orderAmount;
    private boolean confirmed;

    public boolean isConfirmed() {
        return confirmed;
    }

    public Order setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
        return this;
    }

    public Order() {
    }

    public Integer getOrderId() {
        return orderId;
    }

    public Order setOrderId(Integer orderId) {
        this.orderId = orderId;
        return this;
    }

    public long getUserId() {
        return userId;
    }

    public Order setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public Order setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public Order setOrderTimestamp(long orderTimestamp) {
        this.orderDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(orderTimestamp), TimeZone.getDefault().toZoneId());
        return this;
    }

    public long getOrderTimestamp() {
        return orderDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public Order setOrderDate(Date orderDate) {
        this.orderDate = LocalDateTime.ofInstant(orderDate.toInstant(), ZoneId.systemDefault());
        return this;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public Order setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
        return this;
    }
}
