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

    private Long receiveLocaleId(HttpSession session) {
        Long localeId = (Long) session.getAttribute(WEB_LOCALE_ID);
        return localeId;
    }

    private String receiveLocaleName(HttpSession session) {
        String localeName = (String) session.getAttribute(WEB_LOCALE_NAME);
        return localeName;
    }

    private Locale createLocale(HttpSession session) {
        Locale locale = new Locale();
        locale.setId(receiveLocaleId(session));
        locale.setName(receiveLocaleName(session));
        return locale;
    }

    private void setAllLocalesAttribute(HttpSession session) {
        if (session.getAttribute(ALL_LOCALES) == null) {
            session.setAttribute(ALL_LOCALES, localeDao.getAllLocales());
        }
    }

    private void setRussianLocaleAttribute(HttpSession session, Locale locale) {
        session.setAttribute(WEB_LOCALE, locale);
        session.setAttribute(WEB_LOCALE_ID, RU_LOCALE_ID);
        session.setAttribute(WEB_LOCALE_NAME, RUSSIAN);
    }

    private void setEnglishLocaleAttribute(HttpSession session, Locale locale) {
        session.setAttribute(WEB_LOCALE, locale);
        session.setAttribute(WEB_LOCALE_ID, EN_LOCALE_ID);
        session.setAttribute(WEB_LOCALE_NAME, ENGLISH);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession(true);
        setAllLocalesAttribute(session);
        Locale locale = createLocale(session);
        if (locale.getId() == null || locale.getName() == null) {
            setRussianLocaleAttribute(session, locale);
        } else if (locale.getId().equals(EN_LOCALE_ID) && locale.getName().equals(ENGLISH)) {
            setEnglishLocaleAttribute(session, locale);
        } else if (locale.getId().equals(RU_LOCALE_ID) && locale.getName().equals(RUSSIAN)) {
            setRussianLocaleAttribute(session, locale);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
}
