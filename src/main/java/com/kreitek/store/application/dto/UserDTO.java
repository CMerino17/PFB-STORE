package com.kreitek.store.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

public class UserDTO implements Serializable {

    private Long id;

    private String nick;

    private String name;

    private String surname;

    private Long phoneNumber;

    private String email;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;

    private List<ItemDTO> favourites;

    public UserDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ItemDTO> getFavourites() {
        return favourites;
    }

    public void setFavourites(List<ItemDTO> favourites) {
        this.favourites = favourites;
    }
}
