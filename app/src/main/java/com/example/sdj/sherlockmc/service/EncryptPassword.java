package com.example.sdj.sherlockmc.service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Joel on 17-04-2017.
 */

public class EncryptPassword {
    public static String convertPasswordMD5(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        StringBuffer sbufferObject = new StringBuffer();
        byte[] arr = md.digest();
        for (int i = 0; i < arr.length; i++) {
            sbufferObject.append(Integer.toString((arr[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sbufferObject.toString();
    }
}
