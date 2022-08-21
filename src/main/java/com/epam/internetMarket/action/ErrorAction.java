package com.epam.internetMarket.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.internetMarket.util.constants.PageConstants.ERROR_PAGE;

public class ErrorAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
    }
}