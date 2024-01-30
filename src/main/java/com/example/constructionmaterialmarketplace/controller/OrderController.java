package com.example.constructionmaterialmarketplace.controller;

import com.example.constructionmaterialmarketplace.dto.request.OrderRequest;
import com.example.constructionmaterialmarketplace.dto.response.OrderResponse;
import com.example.constructionmaterialmarketplace.dto.response.SimpleResponse;
import com.example.constructionmaterialmarketplace.entity.enums.OrderStatus;
import com.example.constructionmaterialmarketplace.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/save")
    public OrderResponse saveOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.saveOrder(orderRequest);
    }

    @GetMapping("/{orderId}")
    public OrderResponse getOrderById(@PathVariable Long orderId) {
        return orderService.getOrderById(orderId);
    }

    @GetMapping("/{userId}")
    public List<OrderResponse> getOrderByUserId(@PathVariable Long userId) {
        return orderService.getOrderByUserId(userId);
    }

    @PutMapping("/{orderId}")
    public OrderResponse updateOrderData(@PathVariable Long orderId, @RequestBody OrderStatus newStatus) {
        return orderService.updateOrderStatus(orderId, newStatus);
    }

    @DeleteMapping("/{orderId}")
    public SimpleResponse cancelOrder(@PathVariable Long orderId) {
        return orderService.cancelOrder(orderId);
    }
}
