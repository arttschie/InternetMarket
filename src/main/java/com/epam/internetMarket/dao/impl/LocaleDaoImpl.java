package com.epam.internetMarket.dao.impl;

import com.epam.internetMarket.dao.LocaleDao;
import com.epam.internetMarket.database.ConnectionPool;
import com.epam.internetMarket.entity.Locale;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocaleDaoImpl implements LocaleDao {
    private final Logger log = Logger.getLogger(this.getClass().getName());

    private ConnectionPool connectionPool;
    private Connection connection;

    private static final String GET_ALL_LOCALES = "SELECT * FROM locale";
    private static final String GET_LOCALE_NAME = "SELECT short_name FROM locale WHERE id = ?";

    private void establishConnection() {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
    }

    private Locale createLocale(ResultSet rs) throws SQLException {
        Locale locale = new Locale();
        locale.setId(rs.getLong("id"));
        locale.setShortName(rs.getString("short_name"));
        locale.setName(rs.getString("name"));
        return locale;
    }

    @Override
    public List<Locale> getAllLocales() {
        List<Locale> locales = new ArrayList<>();
        establishConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_LOCALES)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                locales.add(createLocale(rs));
            }
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
        return locales;
    }

    @Override
    public String getLocaleShortNameById(Long id) {
        String locale = null;
        establishConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_LOCALE_NAME)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
             while (rs.next()) {
                 locale = rs.getString("short_name");
             }
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
        return locale;
    }
}
