package com.example.constructionmaterialmarketplace.service;

import com.example.constructionmaterialmarketplace.dto.request.ProductRequest;
import com.example.constructionmaterialmarketplace.dto.response.ProductResponse;
import com.example.constructionmaterialmarketplace.dto.response.SimpleResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    ProductResponse saveProduct(Long productId, ProductRequest productRequest);

    ProductResponse getProductById(Long productId);

    List<ProductResponse> getAllProducts();

    ProductResponse updateProduct(Long productId, ProductRequest productRequest);

    SimpleResponse deleteProduct(Long productId);
}
