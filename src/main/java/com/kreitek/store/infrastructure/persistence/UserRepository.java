package com.kreitek.store.infrastructure.persistence;

import com.kreitek.store.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByNickContainsIgnoreCase(String nick);

}
