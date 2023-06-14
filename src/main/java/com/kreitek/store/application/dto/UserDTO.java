package com.kreitek.store.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    private List<ItemDTO> items;

    private List<OrderDTO> orders;

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
        } else {
            this.password = password;
        }
    }

    public List<ItemDTO> getFavourites() {
        return favourites;
    }

    public void setFavourites(List<ItemDTO> favourites) {
        this.favourites = favourites;
    }

    public List<ItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }

    private boolean isMD5Hashed(String password) {
        return password.matches("[a-fA-F0-9]{32}");
    }


}
