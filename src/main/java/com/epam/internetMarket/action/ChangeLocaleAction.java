package com.epam.internetMarket.action;

import com.epam.internetMarket.dao.LocaleDao;
import com.epam.internetMarket.dao.impl.LocaleDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.epam.internetMarket.util.constants.ParameterConstants.*;

public class ChangeLocaleAction implements Action{
    private static LocaleDao localeDao = new LocaleDaoImpl();

    private Long receiveLocaleId(HttpServletRequest request) {
        Long localeId = Long.parseLong(request.getParameter(LOCALE_TO_CHANGE));
        return localeId;
    }

    private String receiveLocaleShortName(HttpServletRequest request) {
        String localeShortName = localeDao.getLocaleShortNameById(receiveLocaleId(request));
        return localeShortName;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        session.setAttribute(WEB_LOCALE_ID, receiveLocaleId(request));
        session.setAttribute(WEB_LOCALE_NAME, receiveLocaleShortName(request));

        request.getRequestDispatcher(request.getParameter(PAGE_PATH)).forward(request, response);
    }
}
