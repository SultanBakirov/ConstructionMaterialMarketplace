package com.example.constructionmaterialmarketplace.converter.response;

import com.example.constructionmaterialmarketplace.dto.response.OrderDetailResponse;
import com.example.constructionmaterialmarketplace.entity.OrderDetail;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderDetailResponseConverter {

    public OrderDetailResponse viewOrderDetail(OrderDetail orderDetail) {
        if (orderDetail == null) {
            return null;
        }
        OrderDetailResponse orderDetailResponse = new OrderDetailResponse();
        orderDetailResponse.setQuantity(orderDetail.getQuantity());
        orderDetailResponse.setPrice(orderDetail.getPrice());
        return orderDetailResponse;
    }

    public List<OrderDetailResponse> getAllOrderDetails(List<OrderDetail> orderDetails) {
        List<OrderDetailResponse> orderDetailResponses = new ArrayList<>();
        for (OrderDetail orderDetail : orderDetails) {
            orderDetailResponses.add(viewOrderDetail(orderDetail));
        }
        return orderDetailResponses;
    }
}
