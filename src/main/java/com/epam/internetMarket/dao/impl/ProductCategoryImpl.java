package com.epam.internetMarket.dao.impl;

import com.epam.internetMarket.dao.ProductCategoryDao;
import com.epam.internetMarket.database.ConnectionPool;
import com.epam.internetMarket.entity.ProductCategory;
import com.epam.internetMarket.entity.ProductCategoryLocale;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryImpl implements ProductCategoryDao {
    private ConnectionPool connectionPool;
    private Connection connection;
    private final Logger log = Logger.getLogger(this.getClass().getName());

    private final static String GET_ALL_CATEGORIES = "SELECT * FROM product_category ORDER BY id";
    private final static String GET_PRODUCT_CATEGORY_NAME = "SELECT \"name\" FROM product_category_locale WHERE product_category_id = ? AND locale_id = ?";

    @Override
    public List<ProductCategory> getAllProductCategories() {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        List<ProductCategory> productCategoryList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_CATEGORIES)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                ProductCategory productCategory = new ProductCategory();
                productCategory.setId(rs.getLong("id"));
                productCategory.setName(rs.getString("name"));
                productCategoryList.add(productCategory);
            }
        } catch (SQLException e) {
            log.error(e);
        } finally {
           connectionPool.returnConnection(connection);
        }
        return productCategoryList;
    }

    @Override
    public String getProductCategoryName(long categoryId, long localeId) {
        String productCategoryName = null;
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT_CATEGORY_NAME)) {
            preparedStatement.setLong(1, categoryId);
            preparedStatement.setLong(2, localeId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                productCategoryName = rs.getString("name");
            }
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
        return productCategoryName;
    }
}
