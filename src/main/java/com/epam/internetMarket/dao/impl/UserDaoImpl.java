package com.epam.internetMarket.dao.impl;

import com.epam.internetMarket.util.hashFunction.MD5;
import org.apache.log4j.Logger;

import com.epam.internetMarket.dao.UserDao;
import com.epam.internetMarket.entity.User;
import com.epam.internetMarket.database.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.epam.internetMarket.util.constants.DatabaseConstants.*;

public class UserDaoImpl implements UserDao {
    private final Logger log = Logger.getLogger(this.getClass().getName());
    private ConnectionPool connectionPool;
    private Connection connection;

    private static final String ADD_USER = "INSERT INTO \"user\" (username, password, first_name, last_name, birthday, phone_number, address, is_admin) VALUES (?,?,?,?,?,?,?,?)";
    private static final String UPDATE_USER = "UPDATE \"user\" SET first_name = ?, last_name = ?, birthday = ?, phone_number = ?, address = ?, status_id = ? WHERE id = ?";
    private static final String GET_ALL_USERS = "SELECT * FROM \"user\" ORDER BY id";
    private static final String UPDATE_PASSWORD = "UPDATE \"user\" SET password = ? WHERE id = ?";
    private static final String CHECK_PASSWORD = "SELECT password FROM \"user\" WHERE id = ?";
    private static final String GET_USER_BY_USERNAME = "SELECT id FROM \"user\" WHERE username = ?";
    private static final String GET_USER_BY_ID = "SELECT username, first_name, last_name, birthday, phone_number, address, status_id, is_admin FROM \"user\" WHERE id = ?";
    private static final String GET_USER_BY_USERNAME_AND_PASSWORD = "SELECT username, first_name, last_name, birthday, phone_number, address, status_id, is_admin FROM \"user\" WHERE username = ? AND password = ?";
    private static final String CHECK_USER = "SELECT * FROM \"user\" WHERE username = ?";
    private static final String GET_USER_STATUS_NAME = "SELECT \"name\" FROM status_locale WHERE locale_id = ? AND status_id = ?";

    private void establishConnection() {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
    }

    private User createUser(ResultSet rs, User user) throws SQLException {
        user.setUsername(rs.getString(USERNAME));
        user.setFirstName(rs.getString(FIRST_NAME));
        user.setLastName(rs.getString(LAST_NAME));
        user.setBirthday(rs.getDate(BIRTHDAY));
        user.setPhoneNumber(rs.getString(PHONE_NUMBER));
        user.setAddress(rs.getString(ADDRESS));
        user.setIsAdmin(rs.getBoolean(IS_ADMIN));
        user.setStatusId(rs.getLong(STATUS_ID));
        return user;
    }

    @Override
    public void addUser(User user) {
        establishConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setDate(5, user.getBirthday());
            preparedStatement.setString(6, user.getPhoneNumber());
            preparedStatement.setString(7, user.getAddress());
            preparedStatement.setBoolean(8, user.getIsAdmin());
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            log.error(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void updateUser(User user) {
        establishConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setDate(3, user.getBirthday());
            preparedStatement.setString(4, user.getPhoneNumber());
            preparedStatement.setString(5, user.getAddress());
            preparedStatement.setLong(6, user.getStatusId());
            preparedStatement.setLong(7, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void updatePassword(long id, String newPassword) {
        establishConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PASSWORD)) {
            preparedStatement.setString(1, newPassword);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        establishConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USERS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong(ID));
                allUsers.add(createUser(rs, user));
            }
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
        return allUsers;
    }

    @Override
    public boolean checkPassword(long id, String currentPassword) {
        boolean checkPassword = false;
        establishConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CHECK_PASSWORD)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                checkPassword = rs.getString(PASSWORD).equals(MD5.getMd5(currentPassword));
            }
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
        return checkPassword;
    }

    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        User user = null;
        establishConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_USERNAME_AND_PASSWORD)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, MD5.getMd5(password));
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(getIdByUsername(username));
                user = createUser(rs, user);
            }
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
        return user;
    }

    @Override
    public long getIdByUsername(String username) {
        long userId = 0;
        establishConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_USERNAME)) {
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                userId = rs.getLong(ID);
            }
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
        return userId;
    }

    @Override
    public boolean userExists(String username) {
        boolean userExists = false;
        establishConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CHECK_USER)) {
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                userExists = true;
            }
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
        return userExists;
    }

    @Override
    public User getUserById(long id) {
        User user = null;
        establishConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(id);
                user = createUser(rs, user);
            }
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
        return user;
    }

    @Override
    public String getUserStatusName(User user, long localeId) {
        String userStatus = null;
        establishConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_STATUS_NAME)) {
            preparedStatement.setLong(1, localeId);
            preparedStatement.setLong(2, user.getStatusId());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                userStatus = rs.getString(NAME);
            }
        } catch (SQLException e) {
            log.error(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
        return userStatus;
    }
}

