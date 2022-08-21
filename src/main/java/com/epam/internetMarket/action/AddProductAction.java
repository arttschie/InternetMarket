package com.epam.internetMarket.action;

import com.epam.internetMarket.dao.ProductDao;
import com.epam.internetMarket.dao.impl.ProductDaoImpl;
import com.epam.internetMarket.entity.Product;
import com.epam.internetMarket.util.validators.ProductValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

import static com.epam.internetMarket.util.constants.PageConstants.ADD_PRODUCT_PAGE;
import static com.epam.internetMarket.util.constants.ParameterConstants.*;
import static com.epam.internetMarket.util.validators.NumberParameterValidator.isNumberParameterValid;

public class AddProductAction implements Action{
    private final ProductDao productDao = new ProductDaoImpl();
    private final ProductValidator validator = new ProductValidator();

    private Product createProduct(HttpServletRequest request) {
        Product product = new Product();
        product.setName(request.getParameter(PRODUCT_NAME));
        product.setCost(BigDecimal.valueOf(Double.parseDouble(request.getParameter(PRODUCT_COST))));
        product.setCount(Integer.parseInt(request.getParameter(PRODUCT_COUNT)));
        product.setProductCategoryId(Long.parseLong(request.getParameter(CATEGORY_ID)));
        product.setDescription(request.getParameter(PRODUCT_DESCRIPTION));
        product.setImageUrl(request.getParameter(PRODUCT_IMAGE_URL));
        return product;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!(validator.isValid(request, response) && isNumberParameterValid(request.getParameter(PRODUCT_COST)))) {
            request.setAttribute(PRODUCT_ADDITION, NEGATIVE);
        } else {
            productDao.addProduct(createProduct(request));
            request.setAttribute(PRODUCT_ADDITION, POSITIVE);
        }
        request.getRequestDispatcher(ADD_PRODUCT_PAGE).forward(request, response);
    }
}
