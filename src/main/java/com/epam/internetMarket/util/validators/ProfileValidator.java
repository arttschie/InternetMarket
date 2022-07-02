package com.epam.internetMarket.util.validators;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.internetMarket.util.constants.ParameterConstants.*;

public class ProfileValidator implements Validator{
    @Override
    public boolean isValid(HttpServletRequest request, HttpServletResponse response) {
        boolean firstName = request.getParameter(FIRST_NAME) != null && !request.getParameter(FIRST_NAME).equals("");
        boolean lastName = request.getParameter(LAST_NAME) != null && !request.getParameter(LAST_NAME).equals("");
        boolean birthday = request.getParameter(BIRTHDAY) != null && !request.getParameter(BIRTHDAY).equals("");

        return firstName && lastName && birthday;
    }
}
