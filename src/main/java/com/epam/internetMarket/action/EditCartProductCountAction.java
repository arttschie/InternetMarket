package com.epam.internetMarket.action;

import com.epam.internetMarket.dao.CartDao;
import com.epam.internetMarket.dao.ProductDao;
import com.epam.internetMarket.dao.impl.CartDaoImpl;
import com.epam.internetMarket.dao.impl.ProductDaoImpl;
import com.epam.internetMarket.entity.Cart;
import com.epam.internetMarket.entity.Product;
import com.epam.internetMarket.entity.User;
import com.epam.internetMarket.util.validators.CartValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.epam.internetMarket.util.constants.PageConstants.PROFILE_PAGE;
import static com.epam.internetMarket.util.validators.NumberParameterValidator.isNumberParameterValid;
import static com.epam.internetMarket.util.constants.ParameterConstants.*;

public class EditCartProductCountAction implements Action {
    private final CartDao cartDao = new CartDaoImpl();
    private final ProductDao productDao = new ProductDaoImpl();
    private final CartValidator validator = new CartValidator();

    private int receiveProductCount(HttpServletRequest request) {
        int productCount = Integer.parseInt(request.getParameter(CART_COUNT));
        return productCount;
    }

    private Product receiveProduct(HttpServletRequest request) {
        Product product = productDao.getProductById(Long.parseLong(request.getParameter(PRODUCT_ID)));
        return product;
    }

    private User receiveUser(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute(LOGGED_USER);
        return user;
    }

    private Cart createCart(HttpServletRequest request) {
        Cart cart = new Cart();
        cart.setUserId(receiveUser(request).getId());
        cart.setProductId(receiveProduct(request).getId());
        cart.setCount(receiveProductCount(request));
        return cart;
    }

    private void setSessionAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.setAttribute(USER_CART, cartDao.getCartProducts(receiveUser(request).getId()));
        session.setAttribute(CART_SUM, cartDao.getSumOfCart(receiveUser(request).getId()));
    }

    private void validateCart(HttpServletRequest request, HttpServletResponse response) {
        if (!(isNumberParameterValid(request.getParameter(CART_COUNT)))) {
            request.setAttribute(CART_UPDATING, NEGATIVE);
        } else if (!(validator.isValid(request, response))) {
            request.setAttribute(CART_UPDATING, NEGATIVE);
        } else {
            if (!(receiveProductCount(request) > 0 && receiveProductCount(request) <= productDao.getProductCount(receiveProduct(request).getId()))) {
                request.setAttribute(CART_UPDATING, NEGATIVE);
            } else {
                cartDao.updateCount(createCart(request));
                setSessionAttributes(request);
            }
        }
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validateCart(request, response);
        request.getRequestDispatcher(PROFILE_PAGE).forward(request, response);
    }
}
