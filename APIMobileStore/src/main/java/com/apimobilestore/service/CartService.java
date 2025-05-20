package com.apimobilestore.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.apimobilestore.dto.request.CartRequestDto;
import com.apimobilestore.dto.response.CartResponseDto;
import com.apimobilestore.entity.Cart;
import com.apimobilestore.entity.CartItem;
import com.apimobilestore.entity.Product;
import com.apimobilestore.entity.Role;
import com.apimobilestore.entity.User;
import com.apimobilestore.enums.Roles;
import com.apimobilestore.exception.AppException;
import com.apimobilestore.exception.ErrorCode;
import com.apimobilestore.mapper.CartMapper;
import com.apimobilestore.repository.*;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

public interface CartService {
    CartResponseDto getCartByUser(String username);
    CartResponseDto updateCart(@Valid CartRequestDto cartRequestDto, String currentUsername);
}

@Service
@RequiredArgsConstructor
@Validated
class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartMapper cartMapper;

    @Override
    @Transactional(readOnly = true)
    public CartResponseDto getCartByUser(String username) {
        if (username == null) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        if (!user.getRoles().stream().anyMatch(role -> role.getName().equals(Roles.CUSTOMER))) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }

        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });
        return cartMapper.toCartResponseDto(cart);
    }

    @Override
    @Transactional
    public CartResponseDto updateCart(@Valid CartRequestDto cartRequestDto, String currentUsername) {
        if (currentUsername == null) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        User user = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        if (!user.getRoles().stream().anyMatch(role -> role.getName().equals(Roles.CUSTOMER))) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }

        Cart cart = cartRepository.findByUserId(user.getId())
        	    .orElseGet(() -> {
        	        Cart newCart = new Cart();
        	        newCart.setUser(user);
        	        return cartRepository.save(newCart);
        	    });

        	List<CartItem> cartItems = cartRequestDto.getItems().stream().map(itemRequest -> {
        	    Product product = productRepository.findById(itemRequest.getProductId())
        	            .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        	    CartItem cartItem = cartMapper.toCartItem(itemRequest);
        	    cartItem.setProduct(product);
        	    cartItem.setPrice(product.getPrice());
        	    return cartItem;
        	}).collect(Collectors.toList());

        	Cart finalCart = cart; // ✅ tạo bản sao effectively final
        	cartItems.forEach(item -> item.setCart(finalCart));

        	cart.setItems(cartItems);
        	cart.setUpdatedAt(LocalDateTime.now());
        	cart = cartRepository.save(cart);
        	return cartMapper.toCartResponseDto(cart);
    }
}
