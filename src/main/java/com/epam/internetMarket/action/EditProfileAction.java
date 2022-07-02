package com.epam.internetMarket.action;

import com.epam.internetMarket.dao.UserDao;
import com.epam.internetMarket.dao.impl.UserDaoImpl;
import com.epam.internetMarket.entity.User;
import com.epam.internetMarket.util.validators.ProfileValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.internetMarket.util.constants.ParameterConstants.*;
import static com.epam.internetMarket.util.constants.PageConstants.*;

public class EditProfileAction implements Action {
    private final UserDao userDao = new UserDaoImpl();
    private final ProfileValidator validator = new ProfileValidator();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        HttpSession session = request.getSession(true);

        User user = null;

        if (!validator.isValid(request, response)) {
            request.setAttribute(PROFILE_UPDATING, NEGATIVE);
        } else {
            user = new User();
            user.setId(Long.parseLong(request.getParameter(USER_ID)));
            user.setFirstName(request.getParameter(FIRST_NAME));
            user.setLastName(request.getParameter(LAST_NAME));
            user.setBirthday(Date.valueOf(request.getParameter(BIRTHDAY)));
            user.setPhoneNumber(request.getParameter(PHONE_NUMBER));
            user.setAddress(request.getParameter(ADDRESS));
            user.setStatusId(INITIAL_STATUS_ID);
            userDao.updateUser(user);
            request.setAttribute(PROFILE_UPDATING, POSITIVE);
        }

        if (request.getParameter(PAGE_NAME).equals(EDIT_PROFILE_PAGE)){
            request.getRequestDispatcher(PROFILE_PAGE).forward(request,response);
            session.setAttribute(LOGGED_USER, userDao.getUserById(user.getId()));
        }
        if (request.getParameter(PAGE_NAME).equals(EDIT_USERS_PAGE)) {
            request.getRequestDispatcher(EDIT_USERS_PAGE).forward(request, response);
        }
    }
}
