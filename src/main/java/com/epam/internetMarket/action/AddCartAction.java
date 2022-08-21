package com.epam.internetMarket.action;

import com.epam.internetMarket.dao.CartDao;
import com.epam.internetMarket.dao.impl.CartDaoImpl;
import com.epam.internetMarket.entity.Cart;
import com.epam.internetMarket.entity.Product;
import com.epam.internetMarket.entity.User;
import com.epam.internetMarket.util.validators.CartValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.epam.internetMarket.util.constants.PageConstants.*;
import static com.epam.internetMarket.util.validators.NumberParameterValidator.isNumberParameterValid;
import static com.epam.internetMarket.util.constants.ParameterConstants.*;

public class AddCartAction implements Action {
    private final CartDao cartDao = new CartDaoImpl();
    private final CartValidator validator = new CartValidator();

    private boolean validateCartCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!isNumberParameterValid(request.getParameter(CART_COUNT))) {
            request.setAttribute(CART_ADDITION, WRONG_CREDENTIALS);
            request.getRequestDispatcher(MARKET_PAGE).forward(request, response);
            return true;
        }
        return false;
    }

    private int receiveCartCount(HttpServletRequest request) {
        int cartCount = Integer.parseInt(request.getParameter(CART_COUNT));
        return cartCount;
    }

    private User receiveUser(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute(LOGGED_USER);
        return user;
    }

    private Product receiveProduct(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Product product = (Product) session.getAttribute(PRODUCT);
        return product;
    }

    private Cart createCart(HttpServletRequest request) {
        Cart cart = new Cart();
        cart.setUserId(receiveUser(request).getId());
        cart.setProductId(receiveProduct(request).getId());
        cart.setCount(receiveCartCount(request));
        return cart;
    }

    private void setSessionAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        cartDao.create(createCart(request));
        session.setAttribute(USER_CART, cartDao.getCartProducts(receiveUser(request).getId()));
        session.setAttribute(CART_SUM, cartDao.getSumOfCart(receiveUser(request).getId()));
        request.setAttribute(CART_ADDITION, POSITIVE);
    }

    private void validateCart(HttpServletRequest request, HttpServletResponse response) {
        if (!validator.isValid(request, response)) {
            request.setAttribute(CART_ADDITION, BIGGER_THAN_ZERO);
        } else if (!validator.isProductCountValid(receiveProduct(request).getId(), receiveCartCount(request))) {
            request.setAttribute(CART_ADDITION, CART_ERROR);
        } else {
            Cart cart = createCart(request);
            if (cartDao.isProductAlreadyInCart(cart)) {
                request.setAttribute(CART_ADDITION, ALREADY_IN_CART);
            } else {
                setSessionAttributes(request);
            }
        }
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (validateCartCount(request, response))
            return;
        validateCart(request, response);
        request.getRequestDispatcher(PRODUCT_PAGE).forward(request, response);
    }
}
