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

import static com.epam.internetMarket.util.constants.DatabaseConstants.*;

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

    private Cart createCart (ResultSet rs, long userId) throws SQLException {
        Cart cart = new Cart();
        cart.setId(rs.getLong(ID));
        cart.setUserId(userId);
        cart.setProductId(rs.getLong(PRODUCT_ID));
        cart.setCount(rs.getInt(COUNT));
        return cart;
    }

    private void establishConnection () {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
    }

    @Override
    public void create(Cart cart) {
        establishConnection();
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
        establishConnection();
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
        establishConnection();
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
        establishConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_PRODUCTS)) {
            preparedStatement.setLong(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                carts.add(createCart(rs, userId));
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
        establishConnection();
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
        establishConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_SUM_CART)) {
            preparedStatement.setLong(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                sum = rs.getInt(SUM);
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
        establishConnection();
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
