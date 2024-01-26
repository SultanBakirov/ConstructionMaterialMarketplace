package com.example.constructionmaterialmarketplace.service;

import com.example.constructionmaterialmarketplace.dto.request.CategoryRequest;
import com.example.constructionmaterialmarketplace.dto.response.CategoryResponse;
import com.example.constructionmaterialmarketplace.dto.response.SimpleResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    CategoryResponse saveCategory(CategoryRequest categoryRequest);

    CategoryResponse getCategoryById(Long categoryId);

    List<CategoryResponse> getAllCategories();

    CategoryResponse updateCategory(Long addressId, CategoryRequest categoryRequest);

    SimpleResponse deleteCategoryById(Long categoryId);
}
