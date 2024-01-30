package com.example.constructionmaterialmarketplace.service.impl;

import com.example.constructionmaterialmarketplace.converter.request.OrderDetailRequestConverter;
import com.example.constructionmaterialmarketplace.converter.response.OrderDetailResponseConverter;
import com.example.constructionmaterialmarketplace.dto.request.OrderDetailRequest;
import com.example.constructionmaterialmarketplace.dto.response.OrderDetailResponse;
import com.example.constructionmaterialmarketplace.dto.response.SimpleResponse;
import com.example.constructionmaterialmarketplace.entity.OrderDetail;
import com.example.constructionmaterialmarketplace.exception.NotFoundException;
import com.example.constructionmaterialmarketplace.repository.OrderDetailRepository;
import com.example.constructionmaterialmarketplace.repository.OrderRepository;
import com.example.constructionmaterialmarketplace.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    private final OrderRepository orderRepository;

    private final OrderDetailRequestConverter orderDetailRequestConverter;

    private final OrderDetailResponseConverter orderDetailResponseConverter;

    @Override
    public OrderDetailResponse saveOrderDetail(OrderDetailRequest orderDetailRequest) {
        OrderDetail orderDetail = orderDetailRequestConverter.create(orderDetailRequest);
        orderDetailRepository.save(orderDetail);
        return orderDetailResponseConverter.viewOrderDetail(orderDetail);
    }

    @Override
    public OrderDetailResponse getOrderDetailById(Long orderDetailId) {
        OrderDetail orderDetail = orderDetailRepository.findById(orderDetailId)
                .orElseThrow(() -> new NotFoundException(String.format("Order detail not found with id: " + orderDetailId)));
        return orderDetailResponseConverter.viewOrderDetail(orderDetail);
    }

    @Override
    public List<OrderDetailResponse> getOrderDetailsByOrderId(Long orderDetailId) {
        if (!orderRepository.existsById(orderDetailId)) {
            throw new IllegalArgumentException(String.format("Order detail not found with id: " + orderDetailId));
        }
        List<OrderDetail> orderDetails = orderDetailRepository.findAllByOrderId(orderDetailId);
        return orderDetailResponseConverter.getAllOrderDetails(orderDetails);
    }

    @Override
    public OrderDetailResponse updateOrderDetailData(Long orderDetailId, OrderDetailRequest orderDetailRequest) {
        OrderDetail orderDetail = orderDetailRepository.findById(orderDetailId)
                .orElseThrow(() -> new NotFoundException(String.format("Order detail not found with id: " + orderDetailId)));
        orderDetailRequestConverter.update(orderDetail, orderDetailRequest);
        return orderDetailResponseConverter.viewOrderDetail(orderDetailRepository.save(orderDetail));
    }

    @Override
    public SimpleResponse deleteOrderDetailById(Long orderDetailId) {
        OrderDetail orderDetail = orderDetailRepository.findById(orderDetailId)
                .orElseThrow(() -> new NotFoundException(String.format("Order detail not found with id: " + orderDetailId)));
        orderDetailRepository.delete(orderDetail);
        return new SimpleResponse("The Order detail removed!");
    }
}
