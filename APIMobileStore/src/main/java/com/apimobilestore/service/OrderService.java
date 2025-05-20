package com.apimobilestore.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.apimobilestore.dto.request.OrderItemRequestDto;
import com.apimobilestore.dto.request.OrderRequestDto;
import com.apimobilestore.dto.response.OrderResponseDto;
import com.apimobilestore.entity.Order;
import com.apimobilestore.entity.OrderItem;
import com.apimobilestore.entity.Product;
import com.apimobilestore.entity.User;
import com.apimobilestore.enums.Roles;
import com.apimobilestore.exception.AppException;
import com.apimobilestore.exception.ErrorCode;
import com.apimobilestore.mapper.OrderMapper;
import com.apimobilestore.repository.OrderRepository;
import com.apimobilestore.repository.ProductRepository;
import com.apimobilestore.repository.UserRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

public interface OrderService {
	OrderResponseDto createOrder(@Valid OrderRequestDto orderRequestDto, String currentUsername);
    OrderResponseDto getOrderById(Long id, String currentUsername);
    List<OrderResponseDto> getOrdersByUser(String currentUsername);
    List<OrderResponseDto> getAllOrders(String currentUsername);
}

@Service
@RequiredArgsConstructor
@Validated
class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;

    @SuppressWarnings("unlikely-arg-type")
	@Override
    @Transactional
    public OrderResponseDto createOrder(@Valid OrderRequestDto orderRequestDto, String currentUsername) {
        if (currentUsername == null) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        User customer = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        if (!customer.getRoles().stream().anyMatch(role -> role.getName().equals(Roles.CUSTOMER))) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }

        Order order = orderMapper.toOrder(orderRequestDto);
        order.setCustomer(customer);
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus("PENDING");
        order.setTotal(0.0);

        double totalAmount = 0.0;
        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemRequestDto itemRequest : orderRequestDto.getItems()) {
            Product product = productRepository.findById(itemRequest.getProductId())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

            OrderItem orderItem = orderMapper.toOrderItem(itemRequest);
            orderItem.setProduct(product);
            orderItem.setOrder(order);
            orderItem.setPrice(product.getPrice());

            totalAmount += product.getPrice() * itemRequest.getQuantity();
            orderItems.add(orderItem);
        }

        order.setItems(orderItems);
        order.setTotal(totalAmount);
        order = orderRepository.save(order);
        return orderMapper.toOrderResponseDto(order);
    }

    @Override
    public OrderResponseDto getOrderById(Long id, String currentUsername) {
        if (currentUsername == null) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        User user = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));

        @SuppressWarnings("unlikely-arg-type")
		boolean isAdmin = user.getRoles().stream().anyMatch(role -> role.getName().equals(Roles.ADMIN));
        if (!isAdmin && !order.getCustomer().getId().equals(user.getId())) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }

        return orderMapper.toOrderResponseDto(order);
    }

    @Override
    public List<OrderResponseDto> getOrdersByUser(String currentUsername) {
        if (currentUsername == null) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        User user = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return orderRepository.findByCustomerId(user.getId()).stream()
                .map(orderMapper::toOrderResponseDto)
                .collect(Collectors.toList());
    }

    @SuppressWarnings("unlikely-arg-type")
	@Override
    public List<OrderResponseDto> getAllOrders(String currentUsername) {
        if (currentUsername == null) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        User user = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        if (!user.getRoles().stream().anyMatch(role -> role.getName().equals(Roles.ADMIN))) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }

        return orderRepository.findAll().stream()
                .map(orderMapper::toOrderResponseDto)
                .collect(Collectors.toList());
    }
}
