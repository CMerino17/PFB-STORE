package com.kreitek.store.infrastructure.rest;

import com.kreitek.store.application.dto.ItemDTO;
import com.kreitek.store.application.dto.UserDTO;
import com.kreitek.store.application.service.FavouriteService;
import com.kreitek.store.application.service.UserService;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserRestController {

    private final UserService userService;
    private final FavouriteService favouriteService;

    @Autowired
    public UserRestController(UserService userService, FavouriteService favouriteService) {
        this.userService = userService;
        this.favouriteService = favouriteService;
    }

    /*@CrossOrigin
    @GetMapping(value = "/users", produces = "application/json")
    ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = this.userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }*/

    @CrossOrigin
    @PostMapping(value = "/users", produces = "application/json", consumes = "application/json")
    ResponseEntity<UserDTO> insertUser(@RequestBody UserDTO userDTO) {
        userDTO = this.userService.saveUser(userDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @CrossOrigin
    @GetMapping(value = "/users/{userId}")
    ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
        Optional<UserDTO> user = this.userService.getUserById(userId);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @DeleteMapping(value = "/users/{userId}")
    ResponseEntity<?> deleteUserById(@PathVariable Long userId) {
        this.userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/users", produces = "application/json")
    ResponseEntity<List<UserDTO>> getAllUsers(@RequestParam(name = "partialName", required = false) String partialName) {
        List<UserDTO> users;

        if (partialName == null) {
            users = this.userService.getAllUsers();
        } else {
            users = this.userService.getUserByNick(partialName);
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @CrossOrigin
    @PatchMapping(value = "/users", produces = "application/json", consumes = "application/json")
    ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
        UserDTO userUpdated = this.userService.saveUser(userDTO);
        return new ResponseEntity<>(userUpdated, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(value = "/users/{userId}/favourites", produces = "application/json", consumes = "application/json")
    ResponseEntity<UserDTO> insertFavourite(@PathVariable Long userId, @RequestBody ItemDTO itemDTO) {
        Optional<UserDTO> userDTO = this.favouriteService.addItemAsFavouriteToUser(itemDTO.getId(), userId);
        if (userDTO.isPresent()){
            return new ResponseEntity<>(userDTO.get(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @DeleteMapping(value = "/users/{userId}/favourites/{itemId}")
    ResponseEntity<?> deleteFavourite(@PathVariable Long userId, @PathVariable Long itemId) {
        this.favouriteService.deleteFavouriteItemFromUser(itemId,userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @CrossOrigin
    @PostMapping(value = "/users/{userId}/items", produces = "application/json", consumes = "application/json")
    public ResponseEntity<List<ItemDTO>> addItemToUserCart(@PathVariable Long userId, @RequestBody ItemDTO itemDTO){
        List<ItemDTO> itemsDto = this.userService.addItemToCart(userId, itemDTO);
        return new ResponseEntity<>(itemsDto, HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping(value = "/users/{userId}/items/{itemId}")
    ResponseEntity<?> deleteItemInUserCart(@PathVariable Long userId, @PathVariable Long itemId) {
        this.userService.deleteItemInCart(userId,itemId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
