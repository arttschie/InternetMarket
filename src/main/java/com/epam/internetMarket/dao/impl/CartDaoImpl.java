package com.epam.internetMarket.dao.impl;

import com.epam.internetMarket.dao.CartDao;
import com.epam.internetMarket.database.ConnectionPool;
import com.epam.internetMarket.entity.Cart;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDaoImpl implements CartDao {
    private final Logger log = Logger.getLogger(this.getClass().getName());
    private ConnectionPool connectionPool;
    private Connection connection;

    private static final String ADD_PRODUCT = "INSERT INTO cart (user_id, product_id, \"count\") VALUES (?, ?, ?);";
    private static final String UPDATE_COUNT = "UPDATE cart SET \"count\" = ? WHERE user_id = ? AND product_id = ?;";
    private static final String DELETE_PRODUCT = "DELETE FROM cart WHERE user_id = ? AND product_id = ?";
    private static final String DELETE_USER_PRODUCTS = "DELETE FROM cart WHERE user_id = ?";
    private static final String GET_USER_PRODUCTS = "SELECT id, product_id, \"count\" FROM cart WHERE user_id = ?";
    private static final String GET_USER_PRODUCT = "SELECT id, \"count\" FROM cart WHERE user_id = ? AND product_id = ?";
    private static final String GET_SUM_CART = "SELECT sum(cost * cart.count) FROM cart JOIN product C on C.id = cart.product_id WHERE user_id = ?";

    @Override
    public void create(Cart cart) {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_PRODUCT)) {
            preparedStatement.setLong(1, cart.getUserId());
            preparedStatement.setLong(2, cart.getProductId());
            preparedStatement.setLong(3, cart.getCount());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void updateCount(Cart cart) {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COUNT)) {
            preparedStatement.setInt(1, cart.getCount());
            preparedStatement.setLong(2, cart.getUserId());
            preparedStatement.setLong(3, cart.getProductId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void delete(Cart cart) {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT)) {
            preparedStatement.setLong(1, cart.getUserId());
            preparedStatement.setLong(2, cart.getProductId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public List<Cart> getCartProducts(long userId) {
        List<Cart> carts = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_PRODUCTS)) {
            preparedStatement.setLong(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Cart cart = new Cart();
                cart.setId(rs.getLong("id"));
                cart.setUserId(userId);
                cart.setProductId(rs.getLong("product_id"));
                cart.setCount(rs.getInt("count"));
                carts.add(cart);
            }
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
        return carts;
    }

    @Override
    public boolean isProductAlreadyInCart(Cart cart) {
        boolean productInCart = false;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_PRODUCT)) {
            preparedStatement.setLong(1, cart.getUserId());
            preparedStatement.setLong(2, cart.getProductId());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                productInCart = true;
            }
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
        return productInCart;
    }

    @Override
    public int getSumOfCart(long userId) {
        int sum = 0;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_SUM_CART)) {
            preparedStatement.setLong(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                sum = rs.getInt("sum");
            }
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
        return sum;
    }

    @Override
    public void deleteAllUserProducts(long userId) {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_PRODUCTS)) {
            preparedStatement.setLong(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
    }
}
