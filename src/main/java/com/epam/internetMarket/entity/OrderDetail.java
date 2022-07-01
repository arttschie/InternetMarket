package com.epam.internetMarket.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderDetail implements Serializable {
    private long id;
    private long orderId;
    private long productId;
    private int count;
    private BigDecimal cost;

    public OrderDetail() {
    }

    public OrderDetail(long id, long orderId, long productId, int count, BigDecimal cost) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.count = count;
        this.cost = cost;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrderId() { return orderId; }

    public void setOrderId(long orderId) { this.orderId = orderId; }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", productId=" + productId +
                ", count=" + count +
                ", cost=" + cost +
                '}';
    }
}
