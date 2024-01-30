package com.example.constructionmaterialmarketplace.service.impl;

import com.example.constructionmaterialmarketplace.converter.request.OrderRequestConverter;
import com.example.constructionmaterialmarketplace.converter.response.OrderResponseConverter;
import com.example.constructionmaterialmarketplace.dto.request.OrderRequest;
import com.example.constructionmaterialmarketplace.dto.response.OrderResponse;
import com.example.constructionmaterialmarketplace.dto.response.SimpleResponse;
import com.example.constructionmaterialmarketplace.entity.Order;
import com.example.constructionmaterialmarketplace.entity.enums.OrderStatus;
import com.example.constructionmaterialmarketplace.exception.InvalidOrderStateException;
import com.example.constructionmaterialmarketplace.exception.InvalidStatusTransitionException;
import com.example.constructionmaterialmarketplace.exception.NotFoundException;
import com.example.constructionmaterialmarketplace.repository.OrderRepository;
import com.example.constructionmaterialmarketplace.repository.UserRepository;
import com.example.constructionmaterialmarketplace.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final UserRepository userRepository;

    private final OrderRequestConverter orderRequestConverter;

    private final OrderResponseConverter orderResponseConverter;

    @Override
    public OrderResponse saveOrder(OrderRequest orderRequest) {
        Order order = orderRequestConverter.create(orderRequest);
        orderRepository.save(order);
        return orderResponseConverter.viewOrder(order);
    }

    @Override
    public OrderResponse getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException(String.format("Order not found with id: " + orderId)));
        return orderResponseConverter.viewOrder(order);
    }

    @Override
    public List<OrderResponse> getOrderByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException(String.format("User not found with id: " + userId));
        }
        List<Order> orders = orderRepository.findAllByUserId(userId);
        return orderResponseConverter.getAllOrders(orders);
    }

    @Override
    public OrderResponse updateOrderStatus(Long orderId, OrderStatus newStatus) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException(String.format("Order not found with id: " + orderId)));
        if (!isValidStatusTransition(order.getStatus(), newStatus)) {
            throw new InvalidStatusTransitionException("Can't transition from " + order.getStatus() + " to " + newStatus);
        }
        order.setStatus(newStatus);
        return orderResponseConverter.viewOrder(orderRepository.save(order));
    }

    private boolean isValidStatusTransition(OrderStatus currentStatus, OrderStatus newStatus) {
        if (currentStatus == OrderStatus.PENDING && newStatus == OrderStatus.COMPLETED) {
            return true;
        } else if (currentStatus == OrderStatus.PENDING && newStatus == OrderStatus.CANCELLED) {
            return true;
        } else if (currentStatus == OrderStatus.COMPLETED || currentStatus == OrderStatus.CANCELLED) {
            return false;
        }
        return false;
    }

    @Override
    public SimpleResponse cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + orderId));
        if (!order.getStatus().equals(OrderStatus.PENDING)) {
            throw new InvalidOrderStateException("Order can't be cancelled in its current state.");
        }
        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
        return new SimpleResponse("The Order cancelled!");
    }
}
