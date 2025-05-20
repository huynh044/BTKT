package com.apimobilestore.dto.request;

import java.time.LocalDateTime;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryRequestDto {
    private Long productId;
    private Integer stock;
    private LocalDateTime lastUpdated;
}
