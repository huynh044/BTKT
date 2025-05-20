package com.apimobilestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.apimobilestore.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}

