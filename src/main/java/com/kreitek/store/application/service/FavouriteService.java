package com.kreitek.store.application.service;

import com.kreitek.store.application.dto.UserDTO;

import java.util.Optional;

public interface FavouriteService {

    Optional<UserDTO> addItemAsFavouriteToUser(Long itemId, Long userId);
    void deleteFavouriteItemFromUser(Long itemId, Long userId);

}
