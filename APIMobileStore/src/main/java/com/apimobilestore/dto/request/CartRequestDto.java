package com.apimobilestore.dto.request;

import java.util.List;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartRequestDto {
    private List<CartItemRequestDto> items;
}
