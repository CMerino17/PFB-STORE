package com.kreitek.store.application.mapper;

import com.kreitek.store.application.dto.ItemDTO;
import com.kreitek.store.application.dto.OrderDTO;
import com.kreitek.store.application.dto.UserDTO;
import com.kreitek.store.domain.entity.Item;
import com.kreitek.store.domain.entity.Order;
import com.kreitek.store.domain.entity.User;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-14T16:14:36+0200",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 17.0.7 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<User> toEntity(List<UserDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( dtoList.size() );
        for ( UserDTO userDTO : dtoList ) {
            list.add( toEntity( userDTO ) );
        }

        return list;
    }

    @Override
    public List<UserDTO> toDto(List<User> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<UserDTO> list = new ArrayList<UserDTO>( entityList.size() );
        for ( User user : entityList ) {
            list.add( toDto( user ) );
        }

        return list;
    }

    @Override
    public User toEntity(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDTO.getId() );
        user.setNick( userDTO.getNick() );
        user.setName( userDTO.getName() );
        user.setSurname( userDTO.getSurname() );
        user.setPhoneNumber( userDTO.getPhoneNumber() );
        user.setEmail( userDTO.getEmail() );
        user.setPassword( userDTO.getPassword() );
        user.setFavourites( itemDTOListToItemSet( userDTO.getFavourites() ) );
        user.setItems( itemDTOListToItemSet( userDTO.getItems() ) );
        user.setOrders( orderDTOListToOrderSet( userDTO.getOrders() ) );

        return user;
    }

    @Override
    public UserDTO toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( user.getId() );
        userDTO.setNick( user.getNick() );
        userDTO.setName( user.getName() );
        userDTO.setSurname( user.getSurname() );
        userDTO.setPhoneNumber( user.getPhoneNumber() );
        userDTO.setEmail( user.getEmail() );
        userDTO.setPassword( user.getPassword() );
        userDTO.setFavourites( itemSetToItemDTOList( user.getFavourites() ) );
        userDTO.setItems( itemSetToItemDTOList( user.getItems() ) );
        userDTO.setOrders( orderSetToOrderDTOList( user.getOrders() ) );

        return userDTO;
    }

    protected Set<Item> itemDTOListToItemSet(List<ItemDTO> list) {
        if ( list == null ) {
            return null;
        }

        Set<Item> set = new HashSet<Item>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( ItemDTO itemDTO : list ) {
            set.add( itemMapper.toEntity( itemDTO ) );
        }

        return set;
    }

    protected Set<Order> orderDTOListToOrderSet(List<OrderDTO> list) {
        if ( list == null ) {
            return null;
        }

        Set<Order> set = new HashSet<Order>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( OrderDTO orderDTO : list ) {
            set.add( orderMapper.toEntity( orderDTO ) );
        }

        return set;
    }

    protected List<ItemDTO> itemSetToItemDTOList(Set<Item> set) {
        if ( set == null ) {
            return null;
        }

        List<ItemDTO> list = new ArrayList<ItemDTO>( set.size() );
        for ( Item item : set ) {
            list.add( itemMapper.toDto( item ) );
        }

        return list;
    }

    protected List<OrderDTO> orderSetToOrderDTOList(Set<Order> set) {
        if ( set == null ) {
            return null;
        }

        List<OrderDTO> list = new ArrayList<OrderDTO>( set.size() );
        for ( Order order : set ) {
            list.add( orderMapper.toDto( order ) );
        }

        return list;
    }
}
