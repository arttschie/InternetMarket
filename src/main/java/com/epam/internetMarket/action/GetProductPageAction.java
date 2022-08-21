package com.epam.internetMarket.action;

import com.epam.internetMarket.dao.ProductDao;
import com.epam.internetMarket.dao.impl.ProductDaoImpl;
import com.epam.internetMarket.entity.Locale;
import com.epam.internetMarket.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.epam.internetMarket.util.constants.ParameterConstants.*;
import static com.epam.internetMarket.util.constants.PageConstants.*;

public class GetProductPageAction implements Action{
    private final ProductDao productDao = new ProductDaoImpl();

    private long receiveProductId(HttpServletRequest request) {
        long productId = Long.parseLong(request.getParameter(PRODUCT_ID));
        return productId;
    }

    private void setLocale(HttpSession session) {
        Locale locale = new Locale();
        locale.setId((Long)session.getAttribute(WEB_LOCALE_ID));
        locale.setName((String) session.getAttribute(WEB_LOCALE_NAME));
    }

    private void receiveProductPage(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
        setLocale(session);
        if (productDao.getProductById(receiveProductId(request)) == null) {
            request.getRequestDispatcher(ERROR_PAGE);
            return;
        }
        Product product = productDao.getProductById(receiveProductId(request));
        session.setAttribute(PRODUCT, product);
        request.getRequestDispatcher(PRODUCT_PAGE).forward(request, response);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (session.getAttribute(LOGGED_USER) != null) {
            receiveProductPage(request, response, session);
        } else {
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        }
    }
}
