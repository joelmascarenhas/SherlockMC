package com.example.sdj.sherlockmc.beans;

/**
 * Created by sdj on 4/16/17.
 */

public class User {
    private String name;
    private String eMail;
    private String phone;

    public String getName() {
        return name;
    }

    public String geteMail() {
        return eMail;
    }

    public String getPhone() {
        return phone;
    }

    public User(String name,String eMail,String phone){
        this.name = name;
        this.eMail = eMail;
        this.phone = phone;
    }
}
