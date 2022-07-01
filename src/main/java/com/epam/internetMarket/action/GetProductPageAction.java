package com.epam.internetMarket.action;

import com.epam.internetMarket.dao.ProductDao;
import com.epam.internetMarket.dao.impl.ProductDaoImpl;
import com.epam.internetMarket.entity.Locale;
import com.epam.internetMarket.entity.Product;
import com.epam.internetMarket.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.internetMarket.util.constants.ParameterConstants.*;
import static com.epam.internetMarket.util.constants.PageConstants.*;

public class GetProductPageAction implements Action{
    private final ProductDao productDao = new ProductDaoImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        HttpSession session = request.getSession(true);
        if (session.getAttribute(LOGGED_USER) != null) {
            Locale locale = new Locale();

            User user = (User) session.getAttribute(LOGGED_USER);
            long productId = Long.parseLong(request.getParameter(PRODUCT_ID));
            locale.setId((Long)session.getAttribute(WEB_LOCALE_ID));
            locale.setName((String) session.getAttribute(WEB_LOCALE_NAME));

            if (productDao.getProductById(productId) == null) {
                request.getRequestDispatcher(ERROR_PAGE);
                return;
            }

            Product product = productDao.getProductById(productId);

            session.setAttribute(PRODUCT, product);

            request.getRequestDispatcher(PRODUCT_PAGE).forward(request, response);
        } else {
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        }
    }
}
