package com.kreitek.store.infrastructure.rest;

import com.kreitek.store.application.dto.ItemDTO;
import com.kreitek.store.application.dto.OrderDTO;
import com.kreitek.store.application.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users/{userId}")
public class OrderRestController {

    private final OrderService orderService;
    @Autowired
    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }
    @CrossOrigin
    @GetMapping(value = "/orders", produces = "application/json")
    public ResponseEntity<List<OrderDTO>> getOrdersFromUser(@PathVariable Long userId){
        List<OrderDTO> orders = this.orderService.getOrdersFromUser(userId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
    @CrossOrigin
    @PostMapping(value = "/orders", produces = "application/json", consumes = "application/json")
    public ResponseEntity<OrderDTO> createOrderInUser(@PathVariable Long userId, @RequestBody OrderDTO orderDTO){
        orderDTO = this.orderService.createOrder(userId, orderDTO);
        return new ResponseEntity<>(orderDTO, HttpStatus.CREATED);
    }
/*
    @CrossOrigin
    @PutMapping(value = "orders/{orderId}/items", produces = "application/json", consumes = "application/json")
    public ResponseEntity<List<ItemDTO>> addItemsIntoOrder(@PathVariable Long userId, @PathVariable Long orderId, @RequestBody ItemDTO itemDTO){
        List<ItemDTO> itemsDTO = this.orderService.addItemToOrder(userId,orderId,itemDTO);
        return new ResponseEntity<>(itemsDTO, HttpStatus.OK);
    }*/
}
