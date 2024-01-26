package com.example.constructionmaterialmarketplace.converter.request;

import com.example.constructionmaterialmarketplace.dto.request.CategoryRequest;
import com.example.constructionmaterialmarketplace.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryRequestConverter {

    public Category create(CategoryRequest categoryRequest) {
        if (categoryRequest == null) {
            return null;
        }
        Category category = new Category();
        category.setCategoryName(categoryRequest.getCategoryName());
        return category;
    }

    public void update(Category category, CategoryRequest categoryRequest) {
        category.setCategoryName(categoryRequest.getCategoryName());
    }
}
