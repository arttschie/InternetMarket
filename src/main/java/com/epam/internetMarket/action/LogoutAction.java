package com.epam.internetMarket.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.internetMarket.util.constants.PageConstants.INDEX_PAGE;

public class LogoutAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        response.sendRedirect(INDEX_PAGE);
    }
}
