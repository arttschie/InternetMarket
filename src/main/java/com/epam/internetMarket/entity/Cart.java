package com.epam.internetMarket.entity;

import java.io.Serializable;

public class Cart implements Serializable {
    private long id;
    private long userId;
    private long productId;
    private int count;

    public Cart() {
    }

    public Cart(long userId, long productId, int count) {
        this.userId = userId;
        this.productId = productId;
        this.count = count;
    }

    public Cart(long id, long userId, long productId, int count) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.count = count;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

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
}
