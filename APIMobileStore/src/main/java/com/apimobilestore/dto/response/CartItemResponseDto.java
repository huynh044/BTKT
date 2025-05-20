package com.apimobilestore.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemResponseDto {
    private Long id;
    private Long productId;
    private Integer quantity;
    private Double price;
}

