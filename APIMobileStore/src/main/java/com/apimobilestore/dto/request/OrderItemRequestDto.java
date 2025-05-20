package com.apimobilestore.dto.request;

import lombok.Data;

@Data
public class OrderItemRequestDto {
    private Long productId;
    private int quantity;
}