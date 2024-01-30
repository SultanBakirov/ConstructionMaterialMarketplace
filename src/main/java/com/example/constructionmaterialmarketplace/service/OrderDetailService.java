package com.example.constructionmaterialmarketplace.service;

import com.example.constructionmaterialmarketplace.dto.request.OrderDetailRequest;
import com.example.constructionmaterialmarketplace.dto.response.OrderDetailResponse;
import com.example.constructionmaterialmarketplace.dto.response.SimpleResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderDetailService {
    OrderDetailResponse saveOrderDetail(OrderDetailRequest orderDetailRequest);

    OrderDetailResponse getOrderDetailById(Long orderDetailId);

    List<OrderDetailResponse> getOrderDetailsByOrderId(Long orderId);

    OrderDetailResponse updateOrderDetailData(Long orderDetailId, OrderDetailRequest orderDetailRequest);

    SimpleResponse deleteOrderDetailById(Long orderDetailId);
}
