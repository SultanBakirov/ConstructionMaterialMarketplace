package com.example.constructionmaterialmarketplace.controller;

import com.example.constructionmaterialmarketplace.dto.request.ProductRequest;
import com.example.constructionmaterialmarketplace.dto.response.ProductResponse;
import com.example.constructionmaterialmarketplace.dto.response.SimpleResponse;
import com.example.constructionmaterialmarketplace.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/{productId}/save")
    public ProductResponse saveProduct(@PathVariable Long productId, @RequestBody ProductRequest productRequest) {
        return productService.saveProduct(productId, productRequest);
    }

    @GetMapping("/{productId}")
    public ProductResponse getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }

    @GetMapping("/product")
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    @PutMapping("/{productId}")
    public ProductResponse updateProductData(@PathVariable Long productId, @RequestBody ProductRequest productRequest) {
        return productService.updateProduct(productId, productRequest);
    }

    @DeleteMapping("/{productId}")
    public SimpleResponse deleteProductById(@PathVariable Long productId) {
        return productService.deleteProduct(productId);
    }
}
