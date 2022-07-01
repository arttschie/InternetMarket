package com.epam.internetMarket.filter;

import javax.servlet.*;
import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import com.epam.internetMarket.dao.impl.LocaleDaoImpl;
import com.epam.internetMarket.entity.Locale;
import com.epam.internetMarket.dao.LocaleDao;

import static com.epam.internetMarket.util.constants.ParameterConstants.*;

public class LocaleFilter implements Filter {
    private final LocaleDao localeDao = new LocaleDaoImpl();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession(true);

        if (session.getAttribute(ALL_LOCALES) == null) {
            session.setAttribute(ALL_LOCALES, localeDao.getAllLocales());
        }
        Locale locale = new Locale();

        Long locale_id = (Long) session.getAttribute(WEB_LOCALE_ID);
        String locale_name = (String) session.getAttribute(WEB_LOCALE_NAME);

        locale.setId((locale_id));
        locale.setName(locale_name);

        if (locale_id == null || locale_name == null) {
            session.setAttribute(WEB_LOCALE, locale);
            session.setAttribute(WEB_LOCALE_ID, RU_LOCALE_ID);
            session.setAttribute(WEB_LOCALE_NAME, RUSSIAN);
        } else if (locale_id.equals(EN_LOCALE_ID) && locale_name.equals(ENGLISH)) {
            session.setAttribute(WEB_LOCALE, locale);
            session.setAttribute(WEB_LOCALE_ID, EN_LOCALE_ID);
            session.setAttribute(WEB_LOCALE_NAME, ENGLISH);
        } else if (locale_id.equals(RU_LOCALE_ID) && locale_name.equals(RUSSIAN)) {
            session.setAttribute(WEB_LOCALE, locale);
            session.setAttribute(WEB_LOCALE_ID, RU_LOCALE_ID);
            session.setAttribute(WEB_LOCALE_NAME, RUSSIAN);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
}
