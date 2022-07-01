package com.epam.internetMarket.dao;

import com.epam.internetMarket.entity.User;

import java.util.List;

public interface UserDao {
    void createUser (User user);
    void updateUser (User user);
    void updatePassword (long id, String newPassword);
    List<User> getAllUsers();
    boolean checkPassword (long id, String currentPassword);
    User getUserByUsernameAndPassword(String username, String password);
    long getIdByUsername (String username);
    boolean userExists (String username);
    User getUserById (long id);
    String getUserStatusName (User user, long localeId);
}
