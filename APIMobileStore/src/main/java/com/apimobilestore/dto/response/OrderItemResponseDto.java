package com.apimobilestore.dto.response;

import lombok.Data;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemResponseDto {
    private Long id;
    private Long productId;
    private Integer quantity;
    private Double price;
}

