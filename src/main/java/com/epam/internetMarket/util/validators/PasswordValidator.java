package com.epam.internetMarket.util.validators;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.internetMarket.util.constants.ParameterConstants.*;

public class PasswordValidator implements Validator{
    public static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$";

    @Override
    public boolean isValid(HttpServletRequest request, HttpServletResponse response) {
        boolean newPassword = request.getParameter(PASSWORD) != null && !request.getParameter(PASSWORD).equals("");
        boolean retypePassword = request.getParameter(RETYPE_PASSWORD) != null && !request.getParameter(RETYPE_PASSWORD).equals("");

        return newPassword && retypePassword;
    }

    public boolean isPasswordValid(String password) { return password.matches(PASSWORD_REGEX); }
}
