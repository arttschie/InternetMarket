package com.epam.internetMarket.action;

import com.epam.internetMarket.dao.UserDao;
import com.epam.internetMarket.dao.impl.UserDaoImpl;
import com.epam.internetMarket.util.hashFunction.MD5;
import com.epam.internetMarket.util.validators.PasswordValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.internetMarket.util.constants.PageConstants.EDIT_USERS_PAGE;
import static com.epam.internetMarket.util.constants.ParameterConstants.*;

public class EditUsersPasswordAction implements Action{
    private final UserDao userDao = new UserDaoImpl();
    private final PasswordValidator validator = new PasswordValidator();

    private boolean isPasswordValid(HttpServletRequest request, HttpServletResponse response) {
        if(!validator.isValid(request, response)) {
            request.setAttribute(PASSWORD_UPDATING, FILL_ALL_FIELDS);
            return false;
        } else if(!validator.isPasswordValid(request.getParameter(PASSWORD))) {
            request.setAttribute(PASSWORD_UPDATING, WRONG_CREDENTIALS);
            return false;
        } else if(!request.getParameter(PASSWORD).equals(request.getParameter(RETYPE_PASSWORD))) {
            request.setAttribute(PASSWORD_UPDATING, PASSWORD_NOT_MATCH);
            return false;
        }
        return true;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(isPasswordValid(request, response)) {
            userDao.updatePassword(Long.parseLong(request.getParameter(USER_ID)), MD5.getMd5(request.getParameter(PASSWORD)));
            request.setAttribute(PASSWORD_UPDATING, POSITIVE);
        }
        request.getRequestDispatcher(EDIT_USERS_PAGE).forward(request, response);
    }
}
