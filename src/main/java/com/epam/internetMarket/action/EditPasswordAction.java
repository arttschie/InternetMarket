package com.epam.internetMarket.action;

import com.epam.internetMarket.dao.UserDao;
import com.epam.internetMarket.dao.impl.UserDaoImpl;
import com.epam.internetMarket.util.hashFunction.MD5;
import com.epam.internetMarket.util.validators.PasswordValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.internetMarket.util.constants.PageConstants.EDIT_PASSWORD_PAGE;
import static com.epam.internetMarket.util.constants.ParameterConstants.*;

public class EditPasswordAction implements Action {
    private final UserDao userDao = new UserDaoImpl();
    private final PasswordValidator validator = new PasswordValidator();

    private boolean isCurrentPasswordValid(HttpServletRequest request, HttpServletResponse response) {
        if (!validator.isValid(request, response)) {
            request.setAttribute(PASSWORD_UPDATING, FILL_ALL_FIELDS);
            return false;
        } else if (!(request.getParameter(CURRENT_PASSWORD) != null && !request.getParameter(CURRENT_PASSWORD).equals(""))) {
            request.setAttribute(PASSWORD_UPDATING, FILL_ALL_FIELDS);
            return false;
        } else if (!userDao.checkPassword(Long.parseLong(request.getParameter(USER_ID)), request.getParameter(CURRENT_PASSWORD))) {
            request.setAttribute(PASSWORD_UPDATING, WRONG_CURRENT_PASSWORD);
            return false;
        }
        return true;
    }

    private boolean isNewPasswordValid(HttpServletRequest request) {
        if (!validator.isPasswordValid(request.getParameter(PASSWORD))) {
            request.setAttribute(PASSWORD_UPDATING, WRONG_CREDENTIALS);
            return false;
        } else if (!request.getParameter(PASSWORD).equals(request.getParameter(RETYPE_PASSWORD))) {
            request.setAttribute(PASSWORD_UPDATING, PASSWORD_NOT_MATCH);
            return false;
        }
        return true;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isCurrentPasswordValid(request, response) && isNewPasswordValid(request)) {
            long id = Long.parseLong(request.getParameter(USER_ID));
            String newPassword = request.getParameter(PASSWORD);
            userDao.updatePassword(id, MD5.getMd5(newPassword));
            request.setAttribute(PASSWORD_UPDATING, POSITIVE);
        }
        request.getRequestDispatcher(EDIT_PASSWORD_PAGE).forward(request, response);
    }
}
