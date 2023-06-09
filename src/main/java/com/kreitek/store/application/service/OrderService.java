package com.kreitek.store.application.service;

import com.kreitek.store.application.dto.ItemDTO;
import com.kreitek.store.application.dto.OrderDTO;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<OrderDTO> getOrdersFromUser(Long userId);
    OrderDTO createOrder(Long userId, OrderDTO orderDTO);

    List<ItemDTO> addItemToOrder(Long userId, Long orderId, ItemDTO itemDTO);

    Optional<OrderDTO> getOrderFromUser(Long userId, Long orderId);
}
