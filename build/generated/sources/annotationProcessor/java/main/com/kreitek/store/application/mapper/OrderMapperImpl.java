package com.kreitek.store.application.mapper;

import com.kreitek.store.application.dto.ItemDTO;
import com.kreitek.store.application.dto.OrderDTO;
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
    date = "2023-06-08T14:43:16+0200",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 17.0.7 (Amazon.com Inc.)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public List<Order> toEntity(List<OrderDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Order> list = new ArrayList<Order>( dtoList.size() );
        for ( OrderDTO orderDTO : dtoList ) {
            list.add( toEntity( orderDTO ) );
        }

        return list;
    }

    @Override
    public List<OrderDTO> toDto(List<Order> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<OrderDTO> list = new ArrayList<OrderDTO>( entityList.size() );
        for ( Order order : entityList ) {
            list.add( toDto( order ) );
        }

        return list;
    }

    @Override
    public OrderDTO toDto(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setUserId( orderUserId( order ) );
        orderDTO.setId( order.getId() );
        orderDTO.setDate( order.getDate() );
        orderDTO.setItems( itemSetToItemDTOList( order.getItems() ) );

        return orderDTO;
    }

    @Override
    public Order toEntity(OrderDTO orderDTO) {
        if ( orderDTO == null ) {
            return null;
        }

        Order order = new Order();

        order.setUser( orderDTOToUser( orderDTO ) );
        order.setId( orderDTO.getId() );
        order.setDate( orderDTO.getDate() );
        order.setItems( itemDTOListToItemSet( orderDTO.getItems() ) );

        return order;
    }

    private Long orderUserId(Order order) {
        if ( order == null ) {
            return null;
        }
        User user = order.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
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

    protected User orderDTOToUser(OrderDTO orderDTO) {
        if ( orderDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( orderDTO.getUserId() );

        return user;
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
}
