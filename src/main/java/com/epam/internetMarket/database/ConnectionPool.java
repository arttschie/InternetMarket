package com.epam.internetMarket.database;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static com.epam.internetMarket.util.constants.DatabaseConstants.*;

public class ConnectionPool {
    private final Logger log = Logger.getLogger(this.getClass().getName());
    private String url;
    private String user;
    private String password;
    private String driverDB;
    private Properties properties = getProperties(CONFIGURATION_FILE);
    private final int maxConnection = Integer.parseInt(properties.getProperty(MAX_CONNECTION));
    private static volatile ConnectionPool instance;
    private static Object mutex = new Object();
    private BlockingQueue<Connection> freeConnections = new ArrayBlockingQueue<>(maxConnection);

    private ConnectionPool() { init(); }

    private void init() {
        setDataForConnection();
        loadDrivers();
        createConnections();
    }

    public static ConnectionPool getInstance() {
        ConnectionPool result = instance;
        if (result == null){
            synchronized (mutex) {
                result = instance;
                if (result == null)
                    instance = result = new ConnectionPool();
            }
        }
        return result;
    }

    private void setDataForConnection() {
        this.url = properties.getProperty(URL);
        this.user = properties.getProperty(USER);
        this.password = properties.getProperty(PASSWORD);
        this.driverDB = properties.getProperty(DRIVER);
    }

    private Properties getProperties(String configurationFile) {
        Properties properties = new Properties();
        InputStream inputStream = ConnectionPool.class.getClassLoader().getResourceAsStream(configurationFile);
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            log.error(e);
        }
        return properties;
    }

    private void loadDrivers() {
        try {
            Driver driver = (Driver) Class.forName(driverDB).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            log.warn(e);
        }
    }

    public synchronized Connection getConnection() {
        Connection connection = null;
        try {
            connection = freeConnections.take();
        } catch (InterruptedException e) {
            log.warn(e);
            e.printStackTrace();
        }
        return connection;
    }

     private BlockingQueue<Connection> createConnections() {
        Connection connection;
        while(freeConnections.size() < maxConnection) {
            try {
                connection = DriverManager.getConnection(url, user, password);
                freeConnections.put(connection);
            } catch (SQLException | InterruptedException e) {
                log.warn(e);
                e.printStackTrace();
            }
        }
        return freeConnections;
     }

     public synchronized void returnConnection(Connection connection) {
        if ((connection != null) && (freeConnections.size() <= maxConnection)){
            try {
                freeConnections.put(connection);
            } catch (InterruptedException e) {
                log.warn(e);
                e.printStackTrace();
            }
        }
     }
}
