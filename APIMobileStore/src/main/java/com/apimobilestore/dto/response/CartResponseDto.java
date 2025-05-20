package com.apimobilestore.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartResponseDto {
    private Long id;
    private Long userId;
    private LocalDateTime updatedAt;
    private List<CartItemResponseDto> items;
}
