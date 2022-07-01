package com.epam.internetMarket.controller;

import com.epam.internetMarket.action.Action;
import com.epam.internetMarket.action.ActionFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class InternetMarketController extends HttpServlet {
    private final Logger log = Logger.getLogger(this.getClass().getName());
    private final ActionFactory actionFactory = ActionFactory.getInstance();
    Action action;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doService(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doService(request, response);
    }

    protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        action = actionFactory.getAction(request.getRequestURI());

        try {
            action.execute(request, response);
        } catch (SQLException | ParseException e) {
            log.error(e);
        }
    }
}
