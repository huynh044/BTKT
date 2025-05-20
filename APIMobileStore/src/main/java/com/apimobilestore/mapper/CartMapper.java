package com.apimobilestore.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.apimobilestore.dto.request.CartItemRequestDto;
import com.apimobilestore.dto.request.CartRequestDto;
import com.apimobilestore.dto.response.CartItemResponseDto;
import com.apimobilestore.dto.response.CartResponseDto;
import com.apimobilestore.entity.Cart;
import com.apimobilestore.entity.CartItem;

@Mapper(componentModel = "spring")
public interface CartMapper {

    // Map CartRequestDto to Cart (for updating cart)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Cart toCart(CartRequestDto cartRequestDto);

    // Map CartItemRequestDto to CartItem
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cart", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "price", ignore = true)
    CartItem toCartItem(CartItemRequestDto cartItemRequestDto);

    // Map Cart to CartResponseDto
    @Mapping(source = "user.id", target = "userId")
    CartResponseDto toCartResponseDto(Cart cart);

    // Map CartItem to CartItemResponseDto
    @Mapping(source = "product.id", target = "productId")
    CartItemResponseDto toCartItemResponseDto(CartItem cartItem);
}

