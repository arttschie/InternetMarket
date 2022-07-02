package com.epam.internetMarket.util.validators;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.internetMarket.util.constants.ParameterConstants.*;

public class ProductValidator implements Validator{

    @Override
    public boolean isValid(HttpServletRequest request, HttpServletResponse response) {
        boolean name = request.getParameter(PRODUCT_NAME) != null && !request.getParameter(PRODUCT_NAME).equals("");
        boolean cost = request.getParameter(PRODUCT_COST) != null && !request.getParameter(PRODUCT_COST).equals("");
        boolean categoryId = request.getParameter(CATEGORY_ID) != null && !request.getParameter(CATEGORY_ID).equals("");
        boolean count = request.getParameter(PRODUCT_COUNT) != null && !request.getParameter(PRODUCT_COUNT).equals("");

        return name && cost && categoryId && count;
    }
}
