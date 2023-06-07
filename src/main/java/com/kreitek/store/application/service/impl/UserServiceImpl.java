package com.kreitek.store.application.service.impl;

import com.kreitek.store.application.dto.ItemDTO;
import com.kreitek.store.application.dto.UserDTO;
import com.kreitek.store.application.mapper.UserMapper;
import com.kreitek.store.application.service.UserService;
import com.kreitek.store.domain.entity.User;
import com.kreitek.store.domain.persistence.ItemPersistence;
import com.kreitek.store.domain.persistence.UserPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserPersistence persistence;
    private final UserMapper mapper;


    @Autowired
    public UserServiceImpl(UserPersistence persistence, UserMapper mapper) {
        this.persistence = persistence;
        this.mapper = mapper;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = this.persistence.getAllUsers();
        return this.mapper.toDto(users);
    }

    @Override
    public Optional<UserDTO> getUserById(Long userId) {
        return this.persistence.getUserById(userId).map(mapper::toDto);

    }

    @Override
    public UserDTO saveUser(UserDTO user) {
        User userSaved = this.persistence.saveUser(mapper.toEntity(user));
        return this.mapper.toDto(userSaved);
    }

    @Override
    public void deleteUser(Long userId) {
        this.persistence.deleteUser(userId);
    }

    @Override
    public List<UserDTO> getUserByNick(String nick) {
        List<User> users = this.persistence.getUserByNick(nick);
        return mapper.toDto(users);

    }

    @Override
    public List<ItemDTO> addItemToCart(Long userId, ItemDTO itemDTO) {
        UserDTO userDto = this.persistence.getUserById(userId).map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userDto.getItems().add(itemDTO);

        User user = this.persistence.saveUser(mapper.toEntity(userDto));
        userDto = this.mapper.toDto(user);

        return userDto.getItems();

    }

    @Override
    public void deleteItemInCart(Long userId, Long itemId) {
        UserDTO userDto = getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));


        userDto.getItems().removeIf(x -> x.getId().equals(itemId));

        this.persistence.saveUser(mapper.toEntity(userDto));



    }

}
