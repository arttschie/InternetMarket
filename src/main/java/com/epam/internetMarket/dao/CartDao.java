package com.epam.internetMarket.dao;

import com.epam.internetMarket.entity.Cart;

import java.util.List;

public interface CartDao {
    void create (Cart cart);
    void updateCount (Cart cart);
    void delete (Cart cart);
    List<Cart> getCartProducts(long userId);
    boolean isProductAlreadyInCart (Cart cart);
    int getSumOfCart (long userId);
    void deleteAllUserProducts(long userId);
}