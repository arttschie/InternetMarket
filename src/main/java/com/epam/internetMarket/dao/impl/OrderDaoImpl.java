package com.epam.internetMarket.dao.impl;

import com.epam.internetMarket.entity.OrderDetail;
import org.apache.log4j.Logger;

import com.epam.internetMarket.dao.OrderDao;
import com.epam.internetMarket.dao.ProductDao;
import com.epam.internetMarket.database.ConnectionPool;
import com.epam.internetMarket.entity.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.epam.internetMarket.util.constants.DatabaseConstants.*;

public class OrderDaoImpl implements OrderDao {
    private final Logger log = Logger.getLogger(this.getClass().getName());
    private ConnectionPool connectionPool;
    private Connection connection;

    private static final String INSERT_ORDER = "INSERT INTO \"order\" (user_id, status_id, total_cost, date_start, date_finish) VALUES (?,?,?,?::date,?::date) RETURNING id";
    private static final String GET_USER_ORDERS = "SELECT id, status_id, total_cost, date_start, date_finish FROM \"order\" WHERE user_id = ?";
    private static final String ADD_ORDER_DETAILS = "INSERT INTO order_detail (order_id, product_id, count, cost) VALUES (?,?,?,?)";
    private static final String GET_ORDER_DETAILS = "SELECT * FROM order_detail WHERE order_id = ?";
    private static final String REDUCE_COUNT = "UPDATE product SET \"count\" = ? WHERE id = ?";
    private static final String GET_STATUS_NAME = "SELECT name FROM status_locale WHERE status_id = ? AND locale_id = ?";
    private static final String UPDATE_ORDER_STATUS = "UPDATE \"order\" SET status_id = ? WHERE id = ?";

    private void establishConnection() {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
    }

    private Order createOrder(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(rs.getLong(ID));
        order.setStatusId(rs.getLong(STATUS_ID));
        order.setTotalCost(rs.getBigDecimal(TOTAL_COST));
        order.setDateStart(rs.getDate(DATE_START));
        order.setDateFinish(rs.getDate(DATE_FINISH));
        return order;
    }

    private OrderDetail createOrderDetail(ResultSet rs) throws SQLException {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setId(rs.getLong(ID));
        orderDetail.setOrderId(rs.getLong(ORDER_ID));
        orderDetail.setProductId(rs.getLong(PRODUCT_ID));
        orderDetail.setCount(rs.getInt(COUNT));
        orderDetail.setCost(rs.getBigDecimal(COST));
        return orderDetail;
    }

    @Override
    public long addOrder(Order order) {
        Long id = null;
        establishConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER)) {
            preparedStatement.setLong(1, order.getUserId());
            preparedStatement.setLong(2, order.getStatusId());
            preparedStatement.setBigDecimal(3, order.getTotalCost());
            preparedStatement.setDate(4, order.getDateStart());
            preparedStatement.setDate(5, order.getDateFinish());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                id = rs.getLong(ID);
            }
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
        return id;
    }

    @Override
    public List<Order> getUserOrders(long userId) {
        List<Order> orders = new ArrayList<>();
        establishConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_ORDERS)) {
            preparedStatement.setLong(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                orders.add(createOrder(rs));
            }
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
        return orders;
    }

    @Override
    public void addOrderDetail(List<OrderDetail> orderDetailList) {
        establishConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_ORDER_DETAILS)) {
            for (OrderDetail orderDetail : orderDetailList) {
                preparedStatement.setLong(1, orderDetail.getOrderId());
                preparedStatement.setLong(2, orderDetail.getProductId());
                preparedStatement.setInt(3, orderDetail.getCount());
                preparedStatement.setBigDecimal(4, orderDetail.getCost());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public List<OrderDetail> getOrderDetails(long orderId) {
        List<OrderDetail> orderDetailList = new ArrayList<>();
        establishConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_DETAILS)) {
             preparedStatement.setLong(1, orderId);
             ResultSet rs = preparedStatement.executeQuery();
             while (rs.next()) {
                 orderDetailList.add(createOrderDetail(rs));
             }
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
        return orderDetailList;
    }

    @Override
    public void reduceCountOfProduct (List<OrderDetail> orderDetailList) {
        establishConnection();
        ProductDao productDao = new ProductDaoImpl();
        try (PreparedStatement preparedStatement = connection.prepareStatement(REDUCE_COUNT)) {
            for (OrderDetail orderDetail : orderDetailList) {
                int count = productDao.getProductCount(orderDetail.getProductId());
                preparedStatement.setLong(1, count - orderDetail.getCount());
                preparedStatement.setLong(2, orderDetail.getProductId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public String getStatusName(long statusId, long localeId) {
        String statusName = null;
        establishConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_STATUS_NAME)) {
            preparedStatement.setLong(1, statusId);
            preparedStatement.setLong(2, localeId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                statusName = rs.getString(NAME);
            }
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
        return statusName;
    }

    @Override
    public void updateOrderStatus(long orderId, long statusId) {
        establishConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER_STATUS)) {
            preparedStatement.setLong(1, statusId);
            preparedStatement.setLong(2, orderId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
    }
}
