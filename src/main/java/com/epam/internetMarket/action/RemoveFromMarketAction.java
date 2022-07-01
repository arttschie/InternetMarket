package com.epam.internetMarket.action;

import com.epam.internetMarket.dao.ProductDao;
import com.epam.internetMarket.dao.impl.ProductDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.internetMarket.util.constants.ParameterConstants.*;
import static com.epam.internetMarket.util.constants.PageConstants.EDIT_PRODUCTS_PAGE;

public class RemoveFromMarketAction implements Action {
    private final ProductDao productDao = new ProductDaoImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        Long productId = Long.parseLong(request.getParameter(PRODUCT_ID));
        productDao.setAmountToZero(productId);
        request.setAttribute(DELETE_PRODUCT, POSITIVE);
        request.getRequestDispatcher(EDIT_PRODUCTS_PAGE).forward(request, response);
    }
}
