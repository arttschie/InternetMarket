package com.epam.internetMarket.action;

import com.epam.internetMarket.dao.LocaleDao;
import com.epam.internetMarket.dao.impl.LocaleDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.internetMarket.util.constants.ParameterConstants.*;

public class ChangeLocaleAction implements Action{
    private static LocaleDao localeDao = new LocaleDaoImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        HttpSession session = request.getSession(true);

        Long localeId = Long.parseLong(request.getParameter(LOCALE_TO_CHANGE));
        String localeShortName = localeDao.getLocaleShortNameById(localeId);

        session.setAttribute(WEB_LOCALE_ID, localeId);
        session.setAttribute(WEB_LOCALE_NAME, localeShortName);

        request.getRequestDispatcher(request.getParameter(PAGE_PATH)).forward(request, response);
    }
}
