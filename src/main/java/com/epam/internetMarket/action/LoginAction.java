package com.epam.internetMarket.action;

import com.epam.internetMarket.dao.CartDao;
import com.epam.internetMarket.dao.OrderDao;
import com.epam.internetMarket.dao.UserDao;
import com.epam.internetMarket.dao.impl.CartDaoImpl;
import com.epam.internetMarket.dao.impl.OrderDaoImpl;
import com.epam.internetMarket.dao.impl.UserDaoImpl;
import com.epam.internetMarket.entity.User;
import com.epam.internetMarket.util.validators.LoginValidator;
import com.epam.internetMarket.util.validators.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.internetMarket.util.constants.PageConstants.LOGIN_PAGE;
import static com.epam.internetMarket.util.constants.PageConstants.PROFILE_PAGE;
import static com.epam.internetMarket.util.constants.ParameterConstants.*;

public class LoginAction implements Action{
    private final UserDao userDao = new UserDaoImpl();
    private final CartDao cartDao = new CartDaoImpl();
    private final OrderDao orderDao = new OrderDaoImpl();
    private final Validator validator = new LoginValidator();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        HttpSession session = request.getSession(true);

        User user = userDao.getUserByUsernameAndPassword(request.getParameter(USERNAME), request.getParameter(PASSWORD));
        if (!validator.isValid(request, response)) {
            request.setAttribute(ERROR_LOGIN, FILL_ALL_FIELDS);
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        } else if (user == null) {
            request.setAttribute(ERROR_LOGIN, WRONG_CREDENTIALS);
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        } else if (user.getStatusId() != INITIAL_STATUS_ID) {
            request.setAttribute(ERROR_LOGIN, USER_BLOCKED);
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        } else {
            session.setAttribute(LOGGED_USER, user);

            if (cartDao.getCartProducts(user.getId()).size() > 0) {
                session.setAttribute(USER_CART, cartDao.getCartProducts(user.getId()));
            } else {
                session.setAttribute(USER_CART, null);
            }

            session.setAttribute(CART_SUM, cartDao.getSumOfCart(user.getId()));
            session.setAttribute(USER_ORDERS, orderDao.getUserOrders(user.getId()));
            request.getRequestDispatcher(PROFILE_PAGE).forward(request, response);
        }
    }
}
