package com.example.constructionmaterialmarketplace.controller;

import com.example.constructionmaterialmarketplace.dto.request.OrderDetailRequest;
import com.example.constructionmaterialmarketplace.dto.response.OrderDetailResponse;
import com.example.constructionmaterialmarketplace.dto.response.SimpleResponse;
import com.example.constructionmaterialmarketplace.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order/detail")
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    @PostMapping("/save")
    public OrderDetailResponse saveOrderDetail(@RequestBody OrderDetailRequest orderDetailRequest) {
        return orderDetailService.saveOrderDetail(orderDetailRequest);
    }

    @GetMapping("/{orderDetailId}")
    public OrderDetailResponse getOrderDetailById(@PathVariable Long orderDetailId) {
        return orderDetailService.getOrderDetailById(orderDetailId);
    }

    @GetMapping("/{orderId}")
    public List<OrderDetailResponse> getOrderDetailsByOrderId(@PathVariable Long orderId) {
        return orderDetailService.getOrderDetailsByOrderId(orderId);
    }

    @PutMapping("/{orderDetailId}")
    public OrderDetailResponse updateOrderDetailData(@PathVariable Long orderDetailId, @RequestBody OrderDetailRequest orderDetailRequest) {
        return orderDetailService.updateOrderDetailData(orderDetailId, orderDetailRequest);
    }

    @DeleteMapping("/{orderDetailId}")
    public SimpleResponse deleteOrderDetailById(@PathVariable Long orderDetailId) {
        return orderDetailService.deleteOrderDetailById(orderDetailId);
    }
}
