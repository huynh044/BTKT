package com.apimobilestore.dto.request;

import lombok.*;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemRequestDto {
    private Long productId;
    private Integer quantity;
}
