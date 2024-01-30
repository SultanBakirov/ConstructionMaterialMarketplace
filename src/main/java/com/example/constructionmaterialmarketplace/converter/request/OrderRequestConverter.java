package com.example.constructionmaterialmarketplace.converter.request;

import com.example.constructionmaterialmarketplace.dto.request.OrderRequest;
import com.example.constructionmaterialmarketplace.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderRequestConverter {

    public Order create(OrderRequest orderRequest) {
        if (orderRequest == null) {
            return null;
        }
        Order order = new Order();
        order.setOrderDate(orderRequest.getOrderDate());
        order.setTotalAmount(orderRequest.getTotalAmount());
        order.setStatus(orderRequest.getStatus());
        return order;
    }
}
