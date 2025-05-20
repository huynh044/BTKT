package com.apimobilestore.dto.response;

import java.time.LocalDateTime;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryResponseDto {
    private Long id;
    private Long productId;
    private Integer stock;
    private LocalDateTime lastUpdated;
}