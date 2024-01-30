package com.example.constructionmaterialmarketplace.service;

import com.example.constructionmaterialmarketplace.dto.request.OrderRequest;
import com.example.constructionmaterialmarketplace.dto.response.OrderResponse;
import com.example.constructionmaterialmarketplace.dto.response.SimpleResponse;
import com.example.constructionmaterialmarketplace.entity.enums.OrderStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    OrderResponse saveOrder(OrderRequest orderRequest);

    OrderResponse getOrderById(Long orderId);

    List<OrderResponse> getOrderByUserId(Long userId);

    OrderResponse updateOrderStatus(Long orderId, OrderStatus orderStatus);

    SimpleResponse cancelOrder(Long orderId);
}
