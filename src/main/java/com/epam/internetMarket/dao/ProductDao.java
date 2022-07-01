package com.epam.internetMarket.dao;

import com.epam.internetMarket.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductDao {
    void addProduct (Product product);
    Product getProductById (long id);
    List<Product> getAllProducts();
    void updateProduct(Product product);
    void deleteProduct(long id);
    String getProductName(String name);
    int getProductCount (long id);
    BigDecimal getProductCost (String name);
    String getCategoryName (long productId, long localeId);
    void setAmountToZero (long id);
}
