package com.epam.internetMarket.dao.impl;

import org.apache.log4j.Logger;

import com.epam.internetMarket.dao.ProductDao;
import com.epam.internetMarket.database.ConnectionPool;
import com.epam.internetMarket.entity.Product;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.internetMarket.util.constants.DatabaseConstants.*;

public class ProductDaoImpl implements ProductDao {
    private final Logger log = Logger.getLogger(this.getClass().getName());
    private ConnectionPool connectionPool;
    private Connection connection;

    private final static String ADD_PRODUCT = "INSERT INTO product " +
            "(\"name\", description, cost, \"count\", product_category_id, image_url) " +
            "VALUES (?,?,?,?,?,?);";
    private final static String SELECT_PRODUCT_BY_ID = "SELECT * FROM product WHERE id = ?;";
    private final static String SELECT_ALL_PRODUCTS = "SELECT * FROM product ORDER BY id;";
    private final static String UPDATE_PRODUCT = "UPDATE product SET " +
            "\"name\" = ?," +
            "description = ?," +
            "cost = ?," +
            "\"count\" = ?," +
            "product_category_id = ?," +
            "image_url = ? " +
            "WHERE id = ?";
    private final static String DELETE_PRODUCT = "DELETE FROM product WHERE id = ?;";
    private final static String GET_PRODUCT_COUNT = "SELECT \"count\" FROM product WHERE id = ?";
    private static final String GET_PRODUCT_NAME = "SELECT \"name\" FROM product WHERE \"name\" = ?";
    private static final String GET_PRODUCT_COST = "SELECT cost FROM product WHERE \"name\" = ?";
    private static final String GET_PRODUCT_CATEGORY = "SELECT \"name\" FROM product_category_locale WHERE product_category_id = ? AND locale_id = ?";
    private static final String SET_COUNT_TO_ZERO = "UPDATE product SET count = 0 WHERE id = ?";

    private void establishConnection() {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
    }

    private Product createProduct(ResultSet rs, Product product) throws SQLException {
        product = new Product();
        product.setId(rs.getLong(ID));
        product.setCost(rs.getBigDecimal(COST));
        product.setCount(rs.getInt(COUNT));
        product.setDescription(rs.getString(DESCRIPTION));
        product.setName(rs.getString(NAME));
        product.setProductCategoryId(rs.getInt(PRODUCT_CATEGORY_ID));
        product.setImageUrl(rs.getString(IMAGE_URL));
        return product;
    }

    @Override
    public void addProduct(Product product) {
        establishConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_PRODUCT)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setBigDecimal(3, product.getCost());
            preparedStatement.setInt(4, product.getCount());
            preparedStatement.setLong(5, product.getProductCategoryId());
            preparedStatement.setString(6, product.getImageUrl());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public Product getProductById(long id) {
        Product product = null;
        establishConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                product = createProduct(rs, product);
            }
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        establishConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Product product = null;
                products.add(createProduct(rs, product));
            }
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
        return products;
    }

    @Override
    public void updateProduct(Product product) {
        establishConnection();
        try (PreparedStatement preparedStatement = connectionPool.getConnection().prepareStatement(UPDATE_PRODUCT)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setBigDecimal(3, product.getCost());
            preparedStatement.setInt(4, product.getCount());
            preparedStatement.setLong(5, product.getProductCategoryId());
            preparedStatement.setString(6, product.getImageUrl());
            preparedStatement.setLong(7, product.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void deleteProduct(long id) {
        establishConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public String getProductName(String name) {
        String productName = null;
        establishConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT_NAME)) {
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                productName = rs.getString(NAME);
            }
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
        return productName;
    }

    @Override
    public int getProductCount(long id) {
        int count = 0;
        establishConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT_COUNT)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                count = rs.getInt(COUNT);
            }
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
        return count;
    }

    @Override
    public BigDecimal getProductCost(String name) {
        BigDecimal cost = null;
        establishConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT_COST)) {
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                cost = rs.getBigDecimal(COST);
            }
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
        return cost;
    }

    @Override
    public String getCategoryName(long productId, long localeId) {
        String categoryName = null;
        establishConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT_CATEGORY)) {
            preparedStatement.setLong(1, productId);
            preparedStatement.setLong(2, localeId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                categoryName = rs.getString(NAME);
            }
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
        return categoryName;
    }

    @Override
    public void setAmountToZero(long id) {
        establishConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SET_COUNT_TO_ZERO)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
    }
}
