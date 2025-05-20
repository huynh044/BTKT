package com.apimobilestore.dto.request;

import java.util.List;

import lombok.Data;

@Data
public class OrderRequestDto {
    private List<OrderItemRequestDto> items;
}

