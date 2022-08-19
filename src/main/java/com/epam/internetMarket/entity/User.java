package com.epam.internetMarket.entity;

import java.io.Serializable;
import java.sql.Date;

import static com.epam.internetMarket.util.constants.ParameterConstants.INITIAL_STATUS_ID;

public class User implements Serializable {
    private long id;
    private String firstName;
    private String lastName;
    private Date birthday;
    private String phoneNumber;
    private String address;
    private String password;
    private boolean isAdmin = false;
    private long statusId = INITIAL_STATUS_ID;
    private String username;
    private String email;

    public User() {
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

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

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
