package com.epam.internetMarket.action;

import com.epam.internetMarket.dao.CartDao;
import com.epam.internetMarket.dao.ProductDao;
import com.epam.internetMarket.dao.impl.CartDaoImpl;
import com.epam.internetMarket.dao.impl.ProductDaoImpl;
import com.epam.internetMarket.entity.Cart;
import com.epam.internetMarket.entity.Product;
import com.epam.internetMarket.entity.User;
import com.epam.internetMarket.util.validators.CartValidator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.internetMarket.util.constants.PageConstants.PROFILE_PAGE;
import static com.epam.internetMarket.util.validators.NumberParameterValidator.isNumberParameterValid;
import static com.epam.internetMarket.util.constants.ParameterConstants.*;

public class EditCartProductCountAction implements Action{
    private final Logger log = Logger.getLogger(this.getClass().getName());
    private final CartDao cartDao = new CartDaoImpl();
    private final ProductDao productDao = new ProductDaoImpl();
    private final CartValidator validator = new CartValidator();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        HttpSession session = request.getSession(true);

        if (!(isNumberParameterValid(request.getParameter(CART_COUNT)))) {
            request.setAttribute(CART_UPDATING, NEGATIVE);
            log.info("1");
        } else if (!(validator.isValid(request, response))) {
            request.setAttribute(CART_UPDATING, NEGATIVE);
            log.info("2");
        } else {
            int productCount = Integer.parseInt(request.getParameter(CART_COUNT));
            Product product = productDao.getProductById(Long.parseLong(request.getParameter(PRODUCT_ID)));
            User user = (User) session.getAttribute(LOGGED_USER);

            if (!(productCount > 0 && productCount <= productDao.getProductCount(product.getId()))) {
                request.setAttribute(CART_UPDATING, NEGATIVE);
                log.info("3");
            } else {
                Cart cart = new Cart();
                cart.setUserId(user.getId());
                cart.setProductId(product.getId());
                cart.setCount(productCount);
                cartDao.updateCount(cart);
                session.setAttribute(USER_CART, cartDao.getCartProducts(user.getId()));
                session.setAttribute(CART_SUM, cartDao.getSumOfCart(user.getId()));
            }
        }
        request.getRequestDispatcher(PROFILE_PAGE).forward(request, response);
    }
}
