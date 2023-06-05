package com.kreitek.store.application.service;

import com.kreitek.store.application.dto.LoginDTO;

public interface LoginService {

    boolean checkLoginWithNickAndPassword(LoginDTO loginDTO);
}
