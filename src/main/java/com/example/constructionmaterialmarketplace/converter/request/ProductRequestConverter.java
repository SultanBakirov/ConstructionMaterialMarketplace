package com.example.constructionmaterialmarketplace.converter.request;

import com.example.constructionmaterialmarketplace.dto.request.ProductRequest;
import com.example.constructionmaterialmarketplace.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductRequestConverter {

    public Product create(ProductRequest productRequest) {
        if (productRequest == null) {
            return null;
        }
        Product product = new Product();
        product.setProductName(productRequest.getProductName());
        product.setImage(productRequest.getImage());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setStockQuantity(productRequest.getStockQuantity());
        return product;
    }

    public void update(Product product, ProductRequest productRequest) {
        product.setProductName(productRequest.getProductName());
        product.setImage(productRequest.getImage());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setStockQuantity(productRequest.getStockQuantity());
    }
}
