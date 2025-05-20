package com.apimobilestore.entity;

import java.time.Instant;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String brand;
    private String model;

    private String cpu;
    private String gpu;
    private String ram; // ví dụ: 16GB DDR4
    private String storage; // ví dụ: 512GB SSD
    private String display; // ví dụ: 15.6" FHD
    private String os; // Windows, Linux, macOS...
    private String battery; // thông tin pin
    private Double weight; // kg

    private Double price;
    private String imageUrl;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

