package com.kreitek.store.application.service;

import com.kreitek.store.application.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDTO> getAllUsers();

    Optional<UserDTO> getUserById(Long userId);

    UserDTO saveUser(UserDTO user);

    void deleteUser(Long userId);

    List<UserDTO> getUserByNick(String nick);



}
