package com.example.constructionmaterialmarketplace.converter.response;

import com.example.constructionmaterialmarketplace.dto.response.OrderResponse;
import com.example.constructionmaterialmarketplace.entity.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderResponseConverter {

    public OrderResponse viewOrder(Order order) {
        if (order == null) {
            return null;
        }
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderDate(order.getOrderDate());
        orderResponse.setTotalAmount(order.getTotalAmount());
        orderResponse.setStatus(order.getStatus());
        return orderResponse;
    }

    public List<OrderResponse> getAllOrders(List<Order> orders) {
        List<OrderResponse> orderResponses = new ArrayList<>();
        for (Order order : orders) {
            orderResponses.add(viewOrder(order));
        }
        return orderResponses;
    }
}
