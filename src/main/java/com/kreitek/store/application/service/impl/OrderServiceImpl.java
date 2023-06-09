package com.kreitek.store.application.service.impl;

import com.kreitek.store.application.dto.ItemDTO;
import com.kreitek.store.application.dto.OrderDTO;
import com.kreitek.store.application.mapper.OrderMapper;
import com.kreitek.store.application.service.OrderService;
import com.kreitek.store.domain.entity.Order;
import com.kreitek.store.domain.persistence.OrderPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderPersistence orderPersistence;
    private final OrderMapper orderMapper;
    @Autowired
    public OrderServiceImpl(OrderPersistence orderPersistence, OrderMapper orderMapper) {
        this.orderPersistence = orderPersistence;
        this.orderMapper = orderMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDTO> getOrdersFromUser(Long userId) {
        List<Order> orders = this.orderPersistence.getOrdersFromUser(userId);
        return this.orderMapper.toDto(orders);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OrderDTO> getOrderFromUser(Long userId, Long orderId) {
        return this.orderPersistence.getOrderByIdAndUserId(orderId, userId).map(orderMapper::toDto);
    }

    @Override
    @Transactional
    public OrderDTO createOrder(Long userId, OrderDTO orderDTO) {
        orderDTO.setUserId(userId);
        Order order = this.orderMapper.toEntity(orderDTO);
        order = this.orderPersistence.createOrder(order);
        return this.orderMapper.toDto(order);
    }

    @Override
    @Transactional
    public List<ItemDTO> addItemToOrder(Long userId, Long orderId, ItemDTO itemDTO) {
        OrderDTO orderDTO = getOrderFromUser(userId,orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        orderDTO.getItems().add(itemDTO);
        Order order = this.orderPersistence.save(this.orderMapper.toEntity(orderDTO));
        orderDTO = this.orderMapper.toDto(order);
        return orderDTO.getItems();
    }
}
