package com.kreitek.store.application.dto;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class LoginDTO implements Serializable {

    private String nick;
    private String password;

    public LoginDTO() {
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        if (!isMD5Hashed(password)) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");

                byte[] passwordBytes = password.getBytes();

                byte[] hashedBytes = md.digest(passwordBytes);

                StringBuilder sb = new StringBuilder();
                for (byte b : hashedBytes) {
                    sb.append(String.format("%02x", b));
                }

                this.password = sb.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    private boolean isMD5Hashed(String password) {
        return password.matches("[a-fA-F0-9]{32}");
    }
}
