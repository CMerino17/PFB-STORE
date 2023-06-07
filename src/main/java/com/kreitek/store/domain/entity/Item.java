package com.kreitek.store.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemSequence")
    private Long id;
    @Column(nullable = false, length = 100)
    @Size(min = 3, max = 100)
    private String name;
    @Column(length = 2000)
    private String description;
    @Column(nullable = false)
    @Positive
    private Double price;
    @Lob
    private byte[] image;
    @ManyToOne()
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    @ManyToMany(mappedBy = "favourites")
    private Set<User> users;

    public Item() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass() || id == null) return false;
        Item item = (Item) o;
        return id.equals(item.id);
    }


}
