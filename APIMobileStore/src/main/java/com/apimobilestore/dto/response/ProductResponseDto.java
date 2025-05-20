package com.apimobilestore.dto.response;


import java.time.LocalDateTime;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDto {
    private Long id;
    private String name;
    private String brand;
    private String model;
    private String cpu;
    private String gpu;
    private String ram;
    private String storage;
    private String display;
    private String os;
    private String battery;
    private Double weight;
    private Double price;
    private String imageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}


