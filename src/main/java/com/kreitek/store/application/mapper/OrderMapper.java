package com.kreitek.store.application.mapper;

import com.kreitek.store.application.dto.OrderDTO;
import com.kreitek.store.domain.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { UserMapper.class, ItemMapper.class })
public interface OrderMapper extends EntityMapper<OrderDTO, Order>{

    @Override
    @Mapping(source = "user.id", target = "userId")
    OrderDTO toDto(Order order);

    @Override
    @Mapping(source = "userId", target = "user.id")
    Order toEntity(OrderDTO orderDTO);

}