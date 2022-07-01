package com.epam.internetMarket.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.internetMarket.util.constants.PageConstants.INDEX_PAGE;

public class LogoutAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        request.getSession().invalidate();
        response.sendRedirect(INDEX_PAGE);
    }
}
