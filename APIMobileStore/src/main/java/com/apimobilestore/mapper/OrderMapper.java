package com.apimobilestore.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.apimobilestore.dto.request.OrderItemRequestDto;
import com.apimobilestore.dto.request.OrderRequestDto;
import com.apimobilestore.dto.response.OrderItemResponseDto;
import com.apimobilestore.dto.response.OrderResponseDto;
import com.apimobilestore.entity.Order;
import com.apimobilestore.entity.OrderItem;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    // Map OrderRequestDto to Order (for placing an order)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "total", ignore = true)
    Order toOrder(OrderRequestDto orderRequestDto);

    // Map OrderItemRequestDto to OrderItem
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "price", ignore = true)
    OrderItem toOrderItem(OrderItemRequestDto orderItemRequestDto);

    // Map Order to OrderResponseDto
    @Mapping(source = "customer.id", target = "customerId")
    OrderResponseDto toOrderResponseDto(Order order);

    // Map OrderItem to OrderItemResponseDto
    @Mapping(source = "product.id", target = "productId")
    OrderItemResponseDto toOrderItemResponseDto(OrderItem orderItem);
}

