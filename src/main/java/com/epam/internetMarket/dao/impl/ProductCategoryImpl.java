package com.epam.internetMarket.dao.impl;

import com.epam.internetMarket.dao.ProductCategoryDao;
import com.epam.internetMarket.database.ConnectionPool;
import com.epam.internetMarket.entity.ProductCategory;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.internetMarket.util.constants.DatabaseConstants.*;

public class ProductCategoryImpl implements ProductCategoryDao {
    private ConnectionPool connectionPool;
    private Connection connection;
    private final Logger log = Logger.getLogger(this.getClass().getName());

    private final static String GET_ALL_CATEGORIES = "SELECT * FROM product_category ORDER BY id";
    private final static String GET_PRODUCT_CATEGORY_NAME = "SELECT \"name\" FROM product_category_locale WHERE product_category_id = ? AND locale_id = ?";

    private void establishConnection() {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
    }

    private ProductCategory createProductCategory(ResultSet rs) throws SQLException {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setId(rs.getLong(ID));
        productCategory.setName(rs.getString(NAME));
        return productCategory;
    }

    @Override
    public List<ProductCategory> getAllProductCategories() {
        establishConnection();
        List<ProductCategory> productCategoryList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_CATEGORIES)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                productCategoryList.add(createProductCategory(rs));
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
        establishConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT_CATEGORY_NAME)) {
            preparedStatement.setLong(1, categoryId);
            preparedStatement.setLong(2, localeId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                productCategoryName = rs.getString(NAME);
            }
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
        return productCategoryName;
    }
}
