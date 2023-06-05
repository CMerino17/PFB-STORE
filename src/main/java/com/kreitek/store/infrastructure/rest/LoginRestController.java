package com.kreitek.store.infrastructure.rest;

import com.kreitek.store.application.dto.LoginDTO;
import com.kreitek.store.application.dto.UserDTO;
import com.kreitek.store.application.service.LoginService;
import com.kreitek.store.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginRestController {

    private final LoginService loginService;

    @Autowired
    public LoginRestController(LoginService loginService) {
        this.loginService = loginService;
    }

    @CrossOrigin
    @PostMapping(value = "/login", produces = "application/json", consumes = "application/json")
    ResponseEntity<Boolean> loginUser(@RequestBody LoginDTO loginDTO) {
        boolean success = this.loginService.checkLoginWithNickAndPassword(loginDTO);
        return new ResponseEntity<>(success, HttpStatus.OK);
    }
}
