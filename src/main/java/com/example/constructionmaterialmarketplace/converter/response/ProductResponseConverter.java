package com.example.constructionmaterialmarketplace.converter.response;

import com.example.constructionmaterialmarketplace.dto.response.ProductResponse;
import com.example.constructionmaterialmarketplace.entity.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductResponseConverter {

    public ProductResponse viewProduct(Product product) {
        if (product == null) {
            return null;
        }
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setProductName(product.getProductName());
        productResponse.setImage(product.getImage());
        productResponse.setDescription(product.getDescription());
        productResponse.setPrice(product.getPrice());
        productResponse.setStockQuantity(product.getStockQuantity());
        return productResponse;
    }

    public List<ProductResponse> getAllProducts(List<Product> products) {
        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product : products) {
            productResponses.add(viewProduct(product));
        }
        return productResponses;
    }
}
