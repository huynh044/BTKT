package com.apimobilestore.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.apimobilestore.dto.request.InventoryRequestDto;
import com.apimobilestore.dto.response.InventoryResponseDto;
import com.apimobilestore.entity.Inventory;
import com.apimobilestore.entity.Product;
import com.apimobilestore.entity.Role;
import com.apimobilestore.entity.User;
import com.apimobilestore.enums.Roles;
import com.apimobilestore.exception.AppException;
import com.apimobilestore.exception.ErrorCode;
import com.apimobilestore.mapper.InventoryMapper;
import com.apimobilestore.repository.InventoryRepository;
import com.apimobilestore.repository.ProductRepository;
import com.apimobilestore.repository.UserRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

public interface InventoryService {
	InventoryResponseDto updateInventory(Long productId, @Valid InventoryRequestDto inventoryRequestDto, String currentUsername);
    InventoryResponseDto getInventoryByProductId(Long productId, String currentUsername);
    List<InventoryResponseDto> getAllInventories(String currentUsername);
}
@Service
@RequiredArgsConstructor
@Validated
class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final InventoryMapper inventoryMapper;

    @Override
    @Transactional
    public InventoryResponseDto updateInventory(Long productId, @Valid InventoryRequestDto inventoryRequestDto, String currentUsername) {
        if (currentUsername == null) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        User user = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        if (!user.getRoles().stream().anyMatch(role -> role.getName().equals(Roles.ADMIN))) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseGet(() -> {
                    Inventory newInventory = new Inventory();
                    newInventory.setProduct(product);
                    return newInventory;
                });
        inventoryMapper.updateInventoryFromDto(inventoryRequestDto, inventory);
        inventory.setLastUpdated(LocalDateTime.now());
        inventory = inventoryRepository.save(inventory);
        return inventoryMapper.toInventoryResponseDto(inventory);
    }

    @Override
    public InventoryResponseDto getInventoryByProductId(Long productId, String currentUsername) {
        if (currentUsername == null) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        User user = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        if (!user.getRoles().stream().anyMatch(role -> role.getName().equals(Roles.ADMIN))) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }

        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new AppException(ErrorCode.INVENTORY_NOT_FOUND));
        return inventoryMapper.toInventoryResponseDto(inventory);
    }

    @Override
    public List<InventoryResponseDto> getAllInventories(String currentUsername) {
        if (currentUsername == null) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        User user = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        if (!user.getRoles().stream().anyMatch(role -> role.getName().equals(Roles.ADMIN))) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }

        return inventoryRepository.findAll().stream()
                .map(inventoryMapper::toInventoryResponseDto)
                .collect(Collectors.toList());
    }
}
