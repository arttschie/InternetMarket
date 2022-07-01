package com.epam.internetMarket.util.validators;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import static com.epam.internetMarket.util.constants.ParameterConstants.*;

public class PasswordValidator implements Validator{
    public static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$";

    private final Logger log = Logger.getLogger(this.getClass().getName());
    @Override
    public boolean isValid(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameter(PASSWORD) == null) {
            log.info("Validator 1");
        }
        if (request.getParameter(RETYPE_PASSWORD) == null) {
            log.info("Validator 2");
        }
        boolean newPassword = request.getParameter(PASSWORD) != null && !request.getParameter(PASSWORD).equals("");
        boolean retypePassword = request.getParameter(RETYPE_PASSWORD) != null && !request.getParameter(RETYPE_PASSWORD).equals("");

        return newPassword && retypePassword;
    }

    public boolean isPasswordValid(String password) { return password.matches(PASSWORD_REGEX); }
}
