package com.kreitek.store.infrastructure.persistence;

import com.kreitek.store.domain.entity.Order;
import com.kreitek.store.domain.persistence.OrderPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderPersistenceImpl implements OrderPersistence {
    private final OrderRepository orderRepository;
    @Autowired
    public OrderPersistenceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public List<Order> getOrdersFromUser(Long userId) {
        return this.orderRepository.findAllByUserId(userId);
    }

    @Override
    public Order createOrder(Order order) {
        order.setDate(new Date());
        return this.orderRepository.save(order);
    }

    @Override
    public Optional<Order> getOrderByIdAndUserId(Long orderId, Long userId) {
        return this.orderRepository.findOneByIdAndUserId(orderId, userId);
    }

    @Override
    public Order save(Order order) {
        return this.orderRepository.save(order);
    }
}
