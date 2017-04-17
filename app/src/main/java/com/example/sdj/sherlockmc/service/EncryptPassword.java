package com.example.sdj.sherlockmc.service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Joel on 17-04-2017.
 */

public class EncryptPassword {
    public static String convertPasswordMD5(String password) throws NoSuchAlgorithmException {
        String encrPass;
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        encrPass = new String(md.digest());
        return encrPass;
    }
}
