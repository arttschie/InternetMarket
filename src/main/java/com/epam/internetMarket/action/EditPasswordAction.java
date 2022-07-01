package com.epam.internetMarket.action;

import com.epam.internetMarket.dao.UserDao;
import com.epam.internetMarket.dao.impl.UserDaoImpl;
import com.epam.internetMarket.util.hashFunction.MD5;
import com.epam.internetMarket.util.validators.PasswordValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.internetMarket.util.constants.PageConstants.EDIT_PASSWORD_PAGE;
import static com.epam.internetMarket.util.constants.ParameterConstants.*;

public class EditPasswordAction implements Action {
    private final UserDao userDao = new UserDaoImpl();
    private final PasswordValidator validator = new PasswordValidator();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        if (!validator.isValid(request, response)) {
            request.setAttribute(PASSWORD_UPDATING, FILL_ALL_FIELDS);
        } else if (!(request.getParameter(CURRENT_PASSWORD) != null && !request.getParameter(CURRENT_PASSWORD).equals(""))) {
            request.setAttribute(PASSWORD_UPDATING, FILL_ALL_FIELDS);
        } else if (!userDao.checkPassword(Long.parseLong(request.getParameter(USER_ID)), request.getParameter(CURRENT_PASSWORD))) {
            request.setAttribute(PASSWORD_UPDATING, WRONG_CURRENT_PASSWORD);
        } else if (!validator.isPasswordValid(request.getParameter(PASSWORD))) {
            request.setAttribute(PASSWORD_UPDATING, WRONG_CREDENTIALS);
        } else if (!request.getParameter(PASSWORD).equals(request.getParameter(RETYPE_PASSWORD))) {
            request.setAttribute(PASSWORD_UPDATING, PASSWORD_NOT_MATCH);
        } else {
            long id = Long.parseLong(request.getParameter(USER_ID));
            String newPassword = request.getParameter(PASSWORD);

            userDao.updatePassword(id, MD5.getMd5(newPassword));
            request.setAttribute(PASSWORD_UPDATING, POSITIVE);
        }

        request.getRequestDispatcher(EDIT_PASSWORD_PAGE).forward(request, response);
    }
}
