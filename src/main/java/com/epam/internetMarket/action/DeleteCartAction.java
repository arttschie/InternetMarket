package com.epam.internetMarket.action;

import com.epam.internetMarket.dao.CartDao;
import com.epam.internetMarket.dao.impl.CartDaoImpl;
import com.epam.internetMarket.entity.Cart;
import com.epam.internetMarket.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.internetMarket.util.constants.PageConstants.PROFILE_PAGE;
import static com.epam.internetMarket.util.constants.ParameterConstants.*;

public class DeleteCartAction implements Action {
    private final CartDao cartDao = new CartDaoImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        HttpSession session = request.getSession(true);

        User user = (User) session.getAttribute(LOGGED_USER);

        Long productId = Long.parseLong(request.getParameter(PRODUCT_ID));
        Cart cart = new Cart();
        cart.setUserId(user.getId());
        cart.setProductId(productId);
        cartDao.delete(cart);

        session.setAttribute(USER_CART, cartDao.getCartProducts(user.getId()));
        session.setAttribute(CART_SUM, cartDao.getSumOfCart(user.getId()));

        request.getRequestDispatcher(PROFILE_PAGE).forward(request, response);
    }
}
