package com.epam.internetMarket.action;

import com.epam.internetMarket.dao.CartDao;
import com.epam.internetMarket.dao.OrderDao;
import com.epam.internetMarket.dao.ProductDao;
import com.epam.internetMarket.dao.impl.CartDaoImpl;
import com.epam.internetMarket.dao.impl.OrderDaoImpl;
import com.epam.internetMarket.dao.impl.ProductDaoImpl;
import com.epam.internetMarket.entity.Cart;
import com.epam.internetMarket.entity.Order;
import com.epam.internetMarket.entity.OrderDetail;
import com.epam.internetMarket.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.epam.internetMarket.util.constants.PageConstants.PROFILE_PAGE;
import static com.epam.internetMarket.util.constants.ParameterConstants.*;

public class MakeOrderAction implements Action {
    private final CartDao cartDao = new CartDaoImpl();
    private final ProductDao productDao = new ProductDaoImpl();
    private final OrderDao orderDao = new OrderDaoImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        HttpSession session = request.getSession(true);

        long orderId;
        User user = (User) session.getAttribute(LOGGED_USER);
        List<Cart> userCartProducts = cartDao.getCartProducts(user.getId());

        for (Cart cartProduct: userCartProducts) {
            if (cartProduct.getCount() > productDao.getProductCount(cartProduct.getProductId())) {
                request.setAttribute(ORDER_COMPLETION, NEGATIVE);
                request.getRequestDispatcher(PROFILE_PAGE).forward(request, response);
                return;
            }
        }

        Order order = new Order();
        order.setUserId(user.getId());
        order.setStatusId(INITIAL_ORDER_STATUS_ID);
        order.setDateStart(Date.valueOf(LocalDate.now()));
        order.setTotalCost(BigDecimal.valueOf(Double.parseDouble(request.getParameter(TOTAL_COST))));
        order.setDateFinish(Date.valueOf(LocalDate.now()));
        orderId = orderDao.addOrder(order);

        List<OrderDetail> orderDetailList = new ArrayList<>();
        for (Cart cart : userCartProducts) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(orderId);
            orderDetail.setProductId(cart.getProductId());
            orderDetail.setCount(cart.getCount());
            orderDetail.setCost(productDao.getProductById(cart.getProductId()).getCost());
            orderDetailList.add(orderDetail);
        }

        orderDao.addOrderDetail(orderDetailList);
        orderDao.reduceCountOfProduct(orderDetailList);
        cartDao.deleteAllUserProducts(user.getId());

        session.setAttribute(USER_CART, null);
        session.setAttribute(CART_SUM, null);
        session.setAttribute(USER_ORDERS, orderDao.getUserOrders(user.getId()));
        request.setAttribute(ORDER_COMPLETION, POSITIVE);

        request.getRequestDispatcher(PROFILE_PAGE).forward(request, response);
    }
}
