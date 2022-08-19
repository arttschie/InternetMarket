package com.epam.internetMarket.util.validators;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.internetMarket.util.constants.ParameterConstants.*;

public class RegisterValidator implements Validator {
    private static final String USERNAME_REGEX = "^[a-zA-Z0-9._\\-]{3,}$";
    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    @Override
    public boolean isValid(HttpServletRequest request, HttpServletResponse response) {
        boolean username = request.getParameter(USERNAME) != null && !request.getParameter(USERNAME).equals("");
        boolean password = request.getParameter(PASSWORD) != null && !request.getParameter(PASSWORD).equals("");
        boolean retypePassword = request.getParameter(RETYPE_PASSWORD) != null && !request.getParameter(RETYPE_PASSWORD).equals("");
        boolean firstName = request.getParameter(FIRST_NAME) != null && !request.getParameter(FIRST_NAME).equals("");
        boolean lastName = request.getParameter(LAST_NAME) != null && !request.getParameter(LAST_NAME).equals("");
        boolean birthday = request.getParameter(BIRTHDAY) != null && !request.getParameter(BIRTHDAY).equals("");
        boolean phoneNumber = request.getParameter(PHONE_NUMBER) != null && !request.getParameter(PHONE_NUMBER).equals("");
        boolean address = request.getParameter(ADDRESS) != null && !request.getParameter(ADDRESS).equals("");
        boolean email = request.getParameter(EMAIL) != null && !request.getParameter(EMAIL).equals("");

        return username && password && retypePassword && firstName && lastName && birthday && phoneNumber && address && email;
    }

    public boolean isUsernameValid (String username) { return username.matches(USERNAME_REGEX); }
    public boolean isEmailValid (String email) { return email.matches(EMAIL_REGEX); }
}
