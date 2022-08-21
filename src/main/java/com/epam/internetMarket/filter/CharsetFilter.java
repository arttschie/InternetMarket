package com.epam.internetMarket.filter;

import javax.servlet.*;
import java.io.IOException;

import static com.epam.internetMarket.util.constants.ParameterConstants.UTF_8;

public class CharsetFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(UTF_8);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
}
