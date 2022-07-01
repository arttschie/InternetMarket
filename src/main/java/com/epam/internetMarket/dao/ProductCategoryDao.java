package com.epam.internetMarket.dao;

import com.epam.internetMarket.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryDao {
    List<ProductCategory> getAllProductCategories();
    String getProductCategoryName(long categoryId, long localeId);
}
