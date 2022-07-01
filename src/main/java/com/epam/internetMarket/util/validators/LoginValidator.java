package com.epam.internetMarket.util.validators;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.internetMarket.util.constants.ParameterConstants.USERNAME;
import static com.epam.internetMarket.util.constants.ParameterConstants.PASSWORD;

public class LoginValidator implements Validator{
    @Override
    public boolean isValid(HttpServletRequest request, HttpServletResponse response) {
        boolean username = request.getParameter(USERNAME) != null && !request.getParameter(USERNAME).equals("");
        boolean password = request.getParameter(PASSWORD) != null && !request.getParameter(PASSWORD).equals("");
        return username && password;
    }
}
