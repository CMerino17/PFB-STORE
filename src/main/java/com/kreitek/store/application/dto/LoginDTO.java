package com.kreitek.store.application.dto;

import java.io.Serializable;

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
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
