package com.epam.internetMarket.util.validators;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.internetMarket.util.constants.ParameterConstants.*;

public class ProfileValidator implements Validator{
    private final Logger log = Logger.getLogger(this.getClass().getName());
    @Override
    public boolean isValid(HttpServletRequest request, HttpServletResponse response) {
        boolean firstName = request.getParameter(FIRST_NAME) != null && !request.getParameter(FIRST_NAME).equals("");
        boolean lastName = request.getParameter(LAST_NAME) != null && !request.getParameter(LAST_NAME).equals("");
        boolean birthday = request.getParameter(BIRTHDAY) != null && !request.getParameter(BIRTHDAY).equals("");

        if (request.getParameter(FIRST_NAME) == null) {
            log.info("Profile validator 1");
        } else if (request.getParameter(LAST_NAME) == null) {
            log.info("Profile validator 2");
        } else if (request.getParameter(BIRTHDAY) == null) {
            log.info("Profile validator 3");
        }

        return firstName && lastName && birthday;
    }
}
