package com.epam.internetMarket.action;

import com.epam.internetMarket.dao.OrderDao;
import com.epam.internetMarket.dao.impl.OrderDaoImpl;
import com.epam.internetMarket.entity.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.internetMarket.util.constants.PageConstants.EDIT_USERS_PAGE;
import static com.epam.internetMarket.util.constants.ParameterConstants.*;

public class EditOrderStatusAction implements Action{
    OrderDao orderDao = new OrderDaoImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        long orderId = Long.parseLong(request.getParameter(ORDER_ID));
        long statusId = Long.parseLong(request.getParameter(STATUS_ID));

        orderDao.updateOrderStatus(orderId, statusId);

        request.setAttribute(ORDER_STATUS_UPDATING, POSITIVE);

        request.getRequestDispatcher(EDIT_USERS_PAGE).forward(request, response);
    }
}
