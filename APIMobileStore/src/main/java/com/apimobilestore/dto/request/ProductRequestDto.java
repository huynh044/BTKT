package com.apimobilestore.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequestDto {
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
}

