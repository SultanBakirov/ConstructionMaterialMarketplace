package com.example.constructionmaterialmarketplace.converter.request;

import com.example.constructionmaterialmarketplace.dto.request.OrderDetailRequest;
import com.example.constructionmaterialmarketplace.entity.OrderDetail;
import org.springframework.stereotype.Component;

@Component
public class OrderDetailRequestConverter {

    public OrderDetail create(OrderDetailRequest orderDetailRequest) {
        if (orderDetailRequest == null) {
            return null;
        }
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setQuantity(orderDetailRequest.getQuantity());
        orderDetail.setPrice(orderDetailRequest.getPrice());
        return orderDetail;
    }

    public void update(OrderDetail orderDetail, OrderDetailRequest orderDetailRequest) {
        orderDetail.setQuantity(orderDetailRequest.getQuantity());
        orderDetail.setPrice(orderDetailRequest.getPrice());
    }
}
