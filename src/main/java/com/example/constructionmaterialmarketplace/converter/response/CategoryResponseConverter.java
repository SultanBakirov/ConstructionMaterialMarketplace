package com.example.constructionmaterialmarketplace.converter.response;

import com.example.constructionmaterialmarketplace.dto.response.CategoryResponse;
import com.example.constructionmaterialmarketplace.entity.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryResponseConverter {

    public CategoryResponse viewCategory(Category category) {
        if (category == null) {
            return null;
        }
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(category.getId());
        categoryResponse.setCategoryName(category.getCategoryName());
        return categoryResponse;
    }

    public List<CategoryResponse> getAllCategories(List<Category> categories) {
        List<CategoryResponse> categoryResponses = new ArrayList<>();
        for (Category category : categories) {
            categoryResponses.add(viewCategory(category));
        }
        return categoryResponses;
    }
}
