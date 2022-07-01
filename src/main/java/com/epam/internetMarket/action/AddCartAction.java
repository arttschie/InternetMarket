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
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.internetMarket.util.constants.PageConstants.*;
import static com.epam.internetMarket.util.validators.NumberParameterValidator.isNumberParameterValid;
import static com.epam.internetMarket.util.constants.ParameterConstants.*;

public class AddCartAction implements Action {
    private final CartDao cartDao = new CartDaoImpl();
    private final CartValidator validator = new CartValidator();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        HttpSession session = request.getSession(true);

        if (!isNumberParameterValid(request.getParameter(CART_COUNT))) {
            request.setAttribute(CART_ADDITION, WRONG_CREDENTIALS);
            request.getRequestDispatcher(MARKET_PAGE).forward(request, response);
            return;
        }

        int cartCount = Integer.parseInt(request.getParameter(CART_COUNT));

        User user = (User) session.getAttribute(LOGGED_USER);
        Product product = (Product) session.getAttribute(PRODUCT);

        if (!validator.isValid(request, response)) {
            request.setAttribute(CART_ADDITION, BIGGER_THAN_ZERO);
        } else if (!validator.isProductCountValid(product.getId(), cartCount)) {
            request.setAttribute(CART_ADDITION, CART_ERROR);
        } else {
            Cart cart = new Cart();
            cart.setUserId(user.getId());
            cart.setProductId(product.getId());
            cart.setCount(cartCount);
            if (cartDao.isProductAlreadyInCart(cart)) {
                request.setAttribute(CART_ADDITION, ALREADY_IN_CART);
            } else {
                cartDao.create(cart);
                session.setAttribute(USER_CART, cartDao.getCartProducts(user.getId()));
                session.setAttribute(CART_SUM, cartDao.getSumOfCart(user.getId()));
                request.setAttribute(CART_ADDITION, POSITIVE);
            }
        }
        request.getRequestDispatcher(PRODUCT_PAGE).forward(request, response);
    }
}
