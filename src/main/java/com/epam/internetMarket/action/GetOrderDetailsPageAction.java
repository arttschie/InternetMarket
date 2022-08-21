package com.epam.internetMarket.action;

import com.epam.internetMarket.dao.OrderDao;
import com.epam.internetMarket.dao.impl.OrderDaoImpl;
import com.epam.internetMarket.entity.OrderDetail;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static com.epam.internetMarket.util.constants.PageConstants.ORDER_DETAILS_PAGE;
import static com.epam.internetMarket.util.constants.ParameterConstants.*;


public class GetOrderDetailsPageAction implements Action {
    private final OrderDao orderDao = new OrderDaoImpl();

    private long receiveOrderId(HttpServletRequest request) {
        long orderId = Long.parseLong(request.getParameter(ORDER_ID));
        return orderId;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        List<OrderDetail> orderDetailsList = orderDao.getOrderDetails(receiveOrderId(request));
        session.setAttribute(ORDER_DETAIL_LIST, orderDetailsList);
        request.getRequestDispatcher(ORDER_DETAILS_PAGE).forward(request, response);
    }
}
