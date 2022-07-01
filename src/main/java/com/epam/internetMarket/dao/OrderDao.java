package com.epam.internetMarket.dao;

import com.epam.internetMarket.entity.Order;
import com.epam.internetMarket.entity.OrderDetail;

import java.util.List;

public interface OrderDao {
    long addOrder (Order order);
    List<Order> getUserOrders (long userId);
    void addOrderDetail (List<OrderDetail> orderDetailList);
    List<OrderDetail> getOrderDetails(long orderId);
    void reduceCountOfProduct (List<OrderDetail> orderDetailList);
    String getStatusName(long statusId, long localeId);
    void updateOrderStatus(long orderId, long statusId);
}
