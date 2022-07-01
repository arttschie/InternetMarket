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
import org.apache.log4j.Logger;

import static com.epam.internetMarket.util.constants.PageConstants.EDIT_USERS_PAGE;
import static com.epam.internetMarket.util.constants.ParameterConstants.*;

public class EditUsersPasswordAction implements Action{
    private final UserDao userDao = new UserDaoImpl();
    private final PasswordValidator validator = new PasswordValidator();
    private final Logger log = Logger.getLogger(this.getClass().getName());
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        if(!validator.isValid(request, response)) {
            request.setAttribute(PASSWORD_UPDATING, FILL_ALL_FIELDS);
            log.info("EditUsersPassword 1");
        } else if(!validator.isPasswordValid(request.getParameter(PASSWORD))) {
            request.setAttribute(PASSWORD_UPDATING, WRONG_CREDENTIALS);
            log.info("EditUsersPassword 2");
        } else if(!request.getParameter(PASSWORD).equals(request.getParameter(RETYPE_PASSWORD))) {
            request.setAttribute(PASSWORD_UPDATING, PASSWORD_NOT_MATCH);
            log.info("EditUsersPassword 3");
        } else {
            userDao.updatePassword(Long.parseLong(request.getParameter(USER_ID)), MD5.getMd5(request.getParameter(PASSWORD)));
            request.setAttribute(PASSWORD_UPDATING, POSITIVE);
            log.info("EditUsersPassword 4");
        }

        request.getRequestDispatcher(EDIT_USERS_PAGE).forward(request, response);
    }
}
