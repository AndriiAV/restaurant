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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (userId != order.userId) return false;
        if (confirmed != order.confirmed) return false;
        if (orderId != null ? !orderId.equals(order.orderId) : order.orderId != null) return false;
        if (orderDate != null ? !orderDate.equals(order.orderDate) : order.orderDate != null) return false;
        return orderAmount != null ? orderAmount.equals(order.orderAmount) : order.orderAmount == null;
    }

    @Override
    public int hashCode() {
        int result = orderId != null ? orderId.hashCode() : 0;
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        result = 31 * result + (orderAmount != null ? orderAmount.hashCode() : 0);
        result = 31 * result + (confirmed ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", orderDate=" + orderDate +
                ", orderAmount=" + orderAmount +
                ", confirmed=" + confirmed +
                '}';
    }
}
