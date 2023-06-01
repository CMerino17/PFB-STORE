package com.kreitek.store.domain.persistence;

import com.kreitek.store.domain.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserPersistence {

    List<User> getAllUsers();

    Optional<User> getUserById(Long userId);

    User saveUser(User user);

    void deleteUser(Long userId);

    List<User> getUserByNick(String nick);

    Optional<User> getUserByNickAndPassword(String nick, String password);

}
