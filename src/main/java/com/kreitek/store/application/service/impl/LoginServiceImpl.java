package com.kreitek.store.application.service.impl;

import com.kreitek.store.application.dto.LoginDTO;
import com.kreitek.store.application.service.LoginService;
import com.kreitek.store.domain.persistence.UserPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {


    @Autowired
    UserPersistence userPersistence;

    @Override
    public boolean checkLoginWithNickAndPassword(LoginDTO loginDTO) {
        return this.userPersistence.getUserByNickAndPassword(loginDTO.getNick(), loginDTO.getPassword()).isPresent();
    }
}
