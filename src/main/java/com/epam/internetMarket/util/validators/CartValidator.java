package com.epam.internetMarket.util.validators;

import com.epam.internetMarket.dao.ProductDao;
import com.epam.internetMarket.dao.impl.ProductDaoImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.internetMarket.util.constants.ParameterConstants.*;

public class CartValidator implements Validator{
    private final ProductDao productDao = new ProductDaoImpl();

    @Override
    public boolean isValid(HttpServletRequest request, HttpServletResponse response) {
        boolean cartCount = request.getParameter(CART_COUNT) != null && !request.getParameter(CART_COUNT).equals("");
        boolean countValidator = Integer.parseInt(request.getParameter(CART_COUNT)) > 0;
        return cartCount && countValidator;
    }

    public boolean isProductCountValid (long productId, int cartCount) {
        boolean isProductCountEnough = cartCount <= productDao.getProductCount(productId);
        return isProductCountEnough;
    }
}
