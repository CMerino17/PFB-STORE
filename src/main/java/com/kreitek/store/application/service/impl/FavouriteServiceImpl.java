package com.kreitek.store.application.service.impl;

import com.kreitek.store.application.dto.UserDTO;
import com.kreitek.store.application.mapper.UserMapper;
import com.kreitek.store.application.service.FavouriteService;
import com.kreitek.store.domain.entity.Item;
import com.kreitek.store.domain.entity.User;
import com.kreitek.store.domain.persistence.ItemPersistence;
import com.kreitek.store.domain.persistence.UserPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class FavouriteServiceImpl implements FavouriteService {
    private final ItemPersistence itemPersistence;
    private final UserPersistence userPersistence;
    private final UserMapper userMapper;

    @Autowired
    public FavouriteServiceImpl(ItemPersistence itemPersistence, UserPersistence userPersistence, UserMapper userMapper) {
        this.itemPersistence = itemPersistence;
        this.userPersistence = userPersistence;
        this.userMapper = userMapper;
    }

    @Override
    public Optional<UserDTO> addItemAsFavouriteToUser(Long itemId, Long userId) {
        Optional<Item> item = this.itemPersistence.getItemById(itemId);
        Optional<User> user = this.userPersistence.getUserById(userId);
        if (item.isPresent() && user.isPresent() && !itemExistsInFavourites(item.get(),user.get().getFavourites())) {
            user.get().getFavourites().add(item.get());
            this.userPersistence.saveUser(user.get());
        }
        return user.map(userMapper::toDto);
    }

    @Override
    public void deleteFavouriteItemFromUser(Long itemId, Long userId) {
        Optional<Item> item = this.itemPersistence.getItemById(itemId);
        Optional<User> user = this.userPersistence.getUserById(userId);

        if (item.isPresent() && user.isPresent()) {
            user.get().getFavourites().removeIf(x -> x.getId().equals(itemId));
            this.userPersistence.saveUser(user.get());
        }
    }

    private boolean itemExistsInFavourites(Item item, Set<Item> favourites){
        return favourites.stream().anyMatch(favItem -> favItem.getId().equals(item.getId()));
    }
}
