package com.epam.internetMarket.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Product implements Serializable {
    private long id;
    private String name;
    private String description;
    private BigDecimal cost;
    private int count;
    private long productCategoryId;
    private String imageUrl;

    public Product() {
    }

    public Product(String name, String description, BigDecimal cost, int count, long productCategoryId) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.count = count;
        this.productCategoryId = productCategoryId;
    }

    public Product(long id, String name, String description, BigDecimal cost, int count, long productCategoryId, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.count = count;
        this.productCategoryId = productCategoryId;
        this.imageUrl = imageUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getImageUrl() { return imageUrl; }

    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                ", count=" + count +
                ", productCategoryId=" + productCategoryId +
                '}';
    }
}
