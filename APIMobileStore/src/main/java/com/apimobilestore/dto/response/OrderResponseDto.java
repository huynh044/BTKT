package com.apimobilestore.dto.response;


import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponseDto {
    private Long id;
    private Long customerId;
    private LocalDateTime createdAt;
    private String status;
    private Double total;
    private List<OrderItemResponseDto> items;
}

