package com.epam.internetMarket.entity;

import java.io.Serializable;

public class ProductCategoryLocale implements Serializable {
    private long id;
    private long localeId;
    private long productCategoryId;
    private String name;

    public ProductCategoryLocale() {
    }

    public ProductCategoryLocale(long id, long localeId, long productCategoryId, String name) {
        this.id = id;
        this.localeId = localeId;
        this.productCategoryId = productCategoryId;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLocaleId() {
        return localeId;
    }

    public void setLocaleId(long localeId) {
        this.localeId = localeId;
    }

    public long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
