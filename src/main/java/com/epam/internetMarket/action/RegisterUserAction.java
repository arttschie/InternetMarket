package com.epam.internetMarket.action;

import com.epam.internetMarket.dao.UserDao;
import com.epam.internetMarket.dao.impl.UserDaoImpl;
import com.epam.internetMarket.entity.User;
import com.epam.internetMarket.util.hashFunction.MD5;
import com.epam.internetMarket.util.validators.PasswordValidator;
import com.epam.internetMarket.util.validators.RegisterValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;

import static com.epam.internetMarket.util.constants.PageConstants.PROFILE_PAGE;
import static com.epam.internetMarket.util.constants.PageConstants.REGISTER_PAGE;
import static com.epam.internetMarket.util.constants.ParameterConstants.*;

public class RegisterUserAction implements Action{
    private final UserDao userDao = new UserDaoImpl();
    private final RegisterValidator registerValidator = new RegisterValidator();
    private final PasswordValidator passwordValidator = new PasswordValidator();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!registerValidator.isValid(request, response)) {
            request.setAttribute(ERROR_REGISTER, FILL_ALL_FIELDS);
            request.getRequestDispatcher(REGISTER_PAGE).forward(request, response);
        } else if (!registerValidator.isUsernameValid(request.getParameter(USERNAME))) {
            request.setAttribute(ERROR_REGISTER, WRONG_USERNAME);
            request.getRequestDispatcher(REGISTER_PAGE).forward(request, response);
        } else if (!passwordValidator.isPasswordValid(request.getParameter(PASSWORD))) {
            request.setAttribute(ERROR_REGISTER, WRONG_PASSWORD);
            request.getRequestDispatcher(REGISTER_PAGE).forward(request, response);
        } else if (!registerValidator.isEmailValid(request.getParameter(EMAIL))) {
            request.setAttribute(ERROR_REGISTER, WRONG_EMAIL);
            request.getRequestDispatcher(REGISTER_PAGE).forward(request, response);
        } else if (userDao.userExists(request.getParameter(USERNAME))) {
            request.setAttribute(ERROR_REGISTER, USERNAME_TAKEN);
            request.getRequestDispatcher(REGISTER_PAGE).forward(request, response);
        } else if (!request.getParameter(PASSWORD).equals(request.getParameter(RETYPE_PASSWORD))) {
            request.setAttribute(ERROR_REGISTER, PASSWORD_NOT_MATCH);
            request.getRequestDispatcher(REGISTER_PAGE).forward(request, response);
        } else {
            User user = new User();
            user.setFirstName(request.getParameter(FIRST_NAME));
            user.setLastName(request.getParameter(LAST_NAME));
            user.setBirthday(Date.valueOf(request.getParameter(BIRTHDAY)));
            user.setPhoneNumber(request.getParameter(PHONE_NUMBER));
            user.setAddress(request.getParameter(ADDRESS));
            user.setPassword(MD5.getMd5(request.getParameter(PASSWORD)));
            user.setUsername(request.getParameter(USERNAME));
            user.setEmail(request.getParameter(EMAIL));
            userDao.addUser(user);
            user.setId(userDao.getIdByUsername(request.getParameter(USERNAME)));
            session.setAttribute(LOGGED_USER, user);
            request.getRequestDispatcher(PROFILE_PAGE).forward(request, response);
        }
    }
}
