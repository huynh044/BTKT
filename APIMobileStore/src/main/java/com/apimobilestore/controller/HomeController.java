package com.apimobilestore.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.apimobilestore.dto.request.ProductRequestDto;
import com.apimobilestore.dto.response.ProductResponseDto;
import com.apimobilestore.entity.Product;
import com.apimobilestore.mapper.ProductMapper;
import com.apimobilestore.service.ProductService;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductMapper productMapper;

    @GetMapping("/")
    public String home(Model model) {
        List<ProductResponseDto> dtos = productService.getFeaturedProducts();
        List<Product> featuredProducts = dtos.stream()
                .map(dto -> {
                    Product product = new Product();
                    productMapper.updateProductFromDto(ProductRequestDto.builder()
                            .name(dto.getName())
                            .brand(dto.getBrand())
                            .model(dto.getModel())
                            .cpu(dto.getCpu())
                            .gpu(dto.getGpu())
                            .ram(dto.getRam())
                            .storage(dto.getStorage())
                            .display(dto.getDisplay())
                            .os(dto.getOs())
                            .battery(dto.getBattery())
                            .weight(dto.getWeight())
                            .price(dto.getPrice())
                            .imageUrl(dto.getImageUrl())
                            .build(), product);
                    product.setId(dto.getId());
                    product.setCreatedAt(dto.getCreatedAt());
                    product.setUpdatedAt(dto.getUpdatedAt());
                    return product;
                })
                .collect(Collectors.toList());
        model.addAttribute("featuredProducts", featuredProducts);
        return "home";
    }

    @GetMapping("/products/{id}")
    public String productDetail(@PathVariable Long id, Model model) {
        ProductResponseDto dto = productService.getProductById(id);
        if (dto == null) {
            return "redirect:/";
        }
        Product product = new Product();
        productMapper.updateProductFromDto(ProductRequestDto.builder()
                .name(dto.getName())
                .brand(dto.getBrand())
                .model(dto.getModel())
                .cpu(dto.getCpu())
                .gpu(dto.getGpu())
                .ram(dto.getRam())
                .storage(dto.getStorage())
                .display(dto.getDisplay())
                .os(dto.getOs())
                .battery(dto.getBattery())
                .weight(dto.getWeight())
                .price(dto.getPrice())
                .imageUrl(dto.getImageUrl())
                .build(), product);
        product.setId(dto.getId());
        product.setCreatedAt(dto.getCreatedAt());
        product.setUpdatedAt(dto.getUpdatedAt());
        model.addAttribute("product", product);
        return "redirect:/";
    }
}
