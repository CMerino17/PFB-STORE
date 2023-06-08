package com.kreitek.store.infrastructure.persistence;

import com.kreitek.store.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserId(Long userId);

    Optional<Order> findOneByIdAndUserId(Long orderId, Long userId);
}
