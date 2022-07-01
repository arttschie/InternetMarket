package com.epam.internetMarket.util.validators;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Validator {
    boolean isValid(HttpServletRequest request, HttpServletResponse response);
}
