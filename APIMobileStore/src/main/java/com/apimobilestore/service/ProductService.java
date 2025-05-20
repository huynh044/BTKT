package com.apimobilestore.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.apimobilestore.dto.request.ProductRequestDto;

import com.apimobilestore.dto.response.ProductResponseDto;
import com.apimobilestore.entity.Product;
import com.apimobilestore.entity.Role;
import com.apimobilestore.entity.User;
import com.apimobilestore.enums.Roles;
import com.apimobilestore.exception.AppException;
import com.apimobilestore.exception.ErrorCode;
import com.apimobilestore.mapper.ProductMapper;
import com.apimobilestore.repository.ProductRepository;
import com.apimobilestore.repository.UserRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

public interface ProductService {
	ProductResponseDto createProduct(@Valid ProductRequestDto productRequestDto, String currentUsername);
    ProductResponseDto updateProduct(Long id, @Valid ProductRequestDto productRequestDto, String currentUsername);
    void deleteProduct(Long id, String currentUsername);
    ProductResponseDto getProductById(Long id);
    List<ProductResponseDto> getAllProducts();
    List<ProductResponseDto> getFeaturedProducts();

}
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ProductMapper productMapper;

    @Override
    @Transactional
    public ProductResponseDto createProduct(@Valid ProductRequestDto productRequestDto, String currentUsername) {
        if (currentUsername == null) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        User user = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        if (!user.getRoles().stream().anyMatch(role -> role.getName().equals(Roles.ADMIN))) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }

        Product product = productMapper.toProduct(productRequestDto);
        product = productRepository.save(product);
        return productMapper.toProductResponseDto(product);
    }

    @Override
    @Transactional
    public ProductResponseDto updateProduct(Long id, @Valid ProductRequestDto productRequestDto, String currentUsername) {
        if (currentUsername == null) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        User user = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        if (!user.getRoles().stream().anyMatch(role -> role.getName().equals(Roles.ADMIN))) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        productMapper.updateProductFromDto(productRequestDto, product);
        product = productRepository.save(product);
        return productMapper.toProductResponseDto(product);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id, String currentUsername) {
        if (currentUsername == null) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        User user = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        if (!user.getRoles().stream().anyMatch(role -> role.getName().equals(Roles.ADMIN))) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        productRepository.delete(product);
    }

    @Override
    public ProductResponseDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        return productMapper.toProductResponseDto(product);
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toProductResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponseDto> getFeaturedProducts() {
        // Giả định có logic để lấy sản phẩm nổi bật
        return productRepository.findAll().stream()
                .map(productMapper::toProductResponseDto)
                .collect(Collectors.toList());
    }
}
