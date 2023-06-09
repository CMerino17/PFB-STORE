package com.kreitek.store.domain.persistence;

import com.kreitek.store.domain.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderPersistence {
    List<Order> getOrdersFromUser(Long userId);
    Order createOrder(Order order);
    Optional<Order> getOrderByIdAndUserId(Long orderId, Long userId);

    Order save(Order order);
}
