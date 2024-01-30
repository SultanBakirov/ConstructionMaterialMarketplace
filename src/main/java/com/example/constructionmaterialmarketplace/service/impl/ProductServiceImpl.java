package com.example.constructionmaterialmarketplace.service.impl;

import com.example.constructionmaterialmarketplace.converter.request.ProductRequestConverter;
import com.example.constructionmaterialmarketplace.converter.response.ProductResponseConverter;
import com.example.constructionmaterialmarketplace.dto.request.ProductRequest;
import com.example.constructionmaterialmarketplace.dto.response.ProductResponse;
import com.example.constructionmaterialmarketplace.dto.response.SimpleResponse;
import com.example.constructionmaterialmarketplace.entity.Category;
import com.example.constructionmaterialmarketplace.entity.Product;
import com.example.constructionmaterialmarketplace.exception.NotFoundException;
import com.example.constructionmaterialmarketplace.repository.CategoryRepository;
import com.example.constructionmaterialmarketplace.repository.ProductRepository;
import com.example.constructionmaterialmarketplace.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    private final ProductRequestConverter productRequestConverter;

    private final ProductResponseConverter productResponseConverter;

    @Override
    public ProductResponse saveProduct(Long categoryId, ProductRequest productRequest) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException(String.format("Category not found with id: " + categoryId)));
        Product product = productRequestConverter.create(productRequest);
        product.setCategoryId(category);
        productRepository.save(product);
        return productResponseConverter.viewProduct(product);
    }

    @Override
    public ProductResponse getProductById(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException(String.format("Product not found with id: " + productId)));
        return productResponseConverter.viewProduct(product);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return productResponseConverter.getAllProducts(products);
    }

    @Override
    public ProductResponse updateProduct(Long productId, ProductRequest productRequest) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException(String.format("Product not found with id: " + productId)));
        productRequestConverter.update(product, productRequest);
        return productResponseConverter.viewProduct(productRepository.save(product));
    }

    @Override
    public SimpleResponse deleteProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException(String.format("Product not found with id: " + productId)));
        productRepository.delete(product);
        return new SimpleResponse("The Product removed!");
    }
}
