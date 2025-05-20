package com.apimobilestore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.apimobilestore.dto.response.UserResponseDto;
import com.apimobilestore.entity.User;
import com.apimobilestore.mapper.ProductMapper;
import com.apimobilestore.service.AdminService;
import com.apimobilestore.service.ProductService;
import com.apimobilestore.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private AdminService adminService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductMapper productMapper;

    // User Management
    @GetMapping("/users")
    public String listUsers(Model model, Authentication authentication) {
        String currentUsername = authentication.getName();
        List<UserResponseDto> users = adminService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/user-list";
    }

    @GetMapping("/users/{username}")
    public String getUserDetail(@PathVariable String username, Model model, Authentication authentication) {
        String currentUsername = authentication.getName();
        UserResponseDto user = adminService.getUserByUsername(username);
        if (user == null) {
            return "redirect:/admin/users";
        }
        model.addAttribute("user", user);
        return "admin/user-detail";
    }

    @GetMapping("/users/{username}/edit")
    public String editUserForm(@PathVariable String username, Model model, Authentication authentication) {
        String currentUsername = authentication.getName();
        UserResponseDto user = adminService.getUserByUsername(username);
        if (user == null) {
            return "redirect:/admin/users";
        }
        model.addAttribute("user", user);
        return "admin/edit-user";
    }

    @PostMapping("/users/{username}/edit")
    public String updateUser(@PathVariable String username, @ModelAttribute User user, Authentication authentication) {
        String currentUsername = authentication.getName();
        userService.updateUser(username, user, currentUsername);
        return "redirect:/admin/users/" + username;
    }

    @PostMapping("/users/{username}/delete")
    public String deleteUser(@PathVariable String username, Authentication authentication) {
        String currentUsername = authentication.getName();
        userService.deleteUser(username, currentUsername);
        return "redirect:/admin/users";
    }

    // Product Management
    @GetMapping("/products")
    public String listProducts(Model model) {
        List<ProductResponseDto> dtos = productService.getAllProducts();
        List<Product> products = dtos.stream()
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
        model.addAttribute("products", products);
        return "admin/product-list";
    }

    @GetMapping("/products/{id}")
    public String getProductDetail(@PathVariable Long id, Model model) {
        ProductResponseDto dto = productService.getProductById(id);
        if (dto == null) {
            return "redirect:/admin/products";
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
        return "admin/product-detail";
    }

    @GetMapping("/products/{id}/edit")
    public String editProductForm(@PathVariable Long id, Model model) {
        ProductResponseDto dto = productService.getProductById(id);
        if (dto == null) {
            return "redirect:/admin/products";
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
        return "admin/edit-product";
    }

    @PostMapping("/products/{id}/edit")
    public String updateProduct(@PathVariable Long id, @ModelAttribute ProductRequestDto productRequestDto, Authentication authentication) {
        String currentUsername = authentication.getName();
        productService.updateProduct(id, productRequestDto, currentUsername);
        return "redirect:/admin/products/" + id;
    }

    @PostMapping("/products/{id}/delete")
    public String deleteProduct(@PathVariable Long id, Authentication authentication) {
        String currentUsername = authentication.getName();
        productService.deleteProduct(id, currentUsername);
        return "redirect:/admin/products";
    }
}
