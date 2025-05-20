package com.apimobilestore.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.apimobilestore.dto.request.ProductRequestDto;
import com.apimobilestore.dto.response.ProductResponseDto;
import com.apimobilestore.entity.Product;



@Mapper(componentModel = "spring")
public interface ProductMapper {

    // Map ProductRequestDto to Product (for creating/updating products)
    @Mapping(target = "id", ignore = true)
    Product toProduct(ProductRequestDto productRequestDto);

    // Update Product from ProductRequestDto
    @Mapping(target = "id", ignore = true)
    void updateProductFromDto(ProductRequestDto productRequestDto, @MappingTarget Product product);

    // Map Product to ProductResponseDto
    ProductResponseDto toProductResponseDto(Product product);
}
