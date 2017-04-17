package com.example.sdj.sherlockmc.beans;

/**
 * Created by sdj on 4/16/17.
 */

public class User {
    private String email;
    private String password;
    private String name;
    private String primaryPhone;
    private String emergencyPhone;

    public User(String email, String password, String name, String primaryPhone, String emergencyPhone) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.primaryPhone = primaryPhone;
        this.emergencyPhone = emergencyPhone;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPrimaryPhone() {
        return primaryPhone;
    }

    public String getEmergencyPhone() {
        return emergencyPhone;
    }

}
