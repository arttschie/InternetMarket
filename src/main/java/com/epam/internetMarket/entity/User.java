package com.epam.internetMarket.entity;

import java.io.Serializable;
import java.sql.Date;

public class User implements Serializable {
    private long id;
    private String firstName;
    private String lastName;
    private Date birthday;
    private String phoneNumber;
    private String address;
    private String password;
    private boolean isAdmin = false;
    private long statusId = 1L;
    private String username;

    public User() {
    }

    public User(String firstName, String lastName, Date birthday, String phoneNumber, String address, String password, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.password = password;
        this.username = username;
    }

    public User(String firstName, String lastName, Date birthday, String phoneNumber, String address, String password, boolean isAdmin, long statusId, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.password = password;
        this.isAdmin = isAdmin;
        this.statusId = statusId;
        this.username = username;
    }

    public User(long id, String firstName, String lastName, Date birthday, String phoneNumber, String address, long statusId, String username) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.statusId = statusId;
        this.username = username;
    }

    public User(long id, String firstName, String lastName, Date birthday, String phoneNumber, String address, String password, boolean isAdmin, long statusId, String username) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.password = password;
        this.isAdmin = isAdmin;
        this.statusId = statusId;
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsAdmin() { return isAdmin; }

    public void setIsAdmin(boolean isAdmin) { this.isAdmin = isAdmin; }

    public long getStatusId() { return statusId; }

    public void setStatusId(long statusId) { this.statusId = statusId; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                ", statusId=" + statusId +
                ", username='" + username + '\'' +
                '}';
    }
}
