package com.apimobilestore.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.apimobilestore.dto.request.InventoryRequestDto;
import com.apimobilestore.dto.response.InventoryResponseDto;
import com.apimobilestore.entity.Inventory;

@Mapper(componentModel = "spring")
public interface InventoryMapper {

    // Map InventoryRequestDto to Inventory (for updating inventory)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true)
    Inventory toInventory(InventoryRequestDto inventoryRequestDto);

    // Update Inventory from InventoryRequestDto
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true)
    void updateInventoryFromDto(InventoryRequestDto inventoryRequestDto, @MappingTarget Inventory inventory);

    // Map Inventory to InventoryResponseDto
    @Mapping(source = "product.id", target = "productId")
    InventoryResponseDto toInventoryResponseDto(Inventory inventory);
}
