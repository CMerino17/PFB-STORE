package com.kreitek.store.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class OrderDTO implements Serializable {

    private Long id;

    private Date date;

    private Long userId;

    private List<ItemDTO> items;

    public OrderDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<ItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,date,userId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        OrderDTO that = (OrderDTO) obj;
        return id.equals(that.id) && date.equals(that.date);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
