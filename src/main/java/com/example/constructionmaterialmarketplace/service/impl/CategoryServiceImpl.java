package com.example.constructionmaterialmarketplace.service.impl;

import com.example.constructionmaterialmarketplace.converter.request.CategoryRequestConverter;
import com.example.constructionmaterialmarketplace.converter.response.CategoryResponseConverter;
import com.example.constructionmaterialmarketplace.dto.request.CategoryRequest;
import com.example.constructionmaterialmarketplace.dto.response.CategoryResponse;
import com.example.constructionmaterialmarketplace.dto.response.SimpleResponse;
import com.example.constructionmaterialmarketplace.entity.Category;
import com.example.constructionmaterialmarketplace.exception.NotFoundException;
import com.example.constructionmaterialmarketplace.repository.CategoryRepository;
import com.example.constructionmaterialmarketplace.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryRequestConverter categoryRequestConverter;

    private final CategoryResponseConverter categoryResponseConverter;

    @Override
    public CategoryResponse saveCategory(CategoryRequest categoryRequest) {
        Category category = categoryRequestConverter.create(categoryRequest);
        categoryRepository.save(category);
        return categoryResponseConverter.viewCategory(category);
    }

    @Override
    public CategoryResponse getCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException(String.format("Category with id = %d not found", categoryId)));
        return categoryResponseConverter.viewCategory(category);
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categoryResponseConverter.getAllCategories(categories);
    }

    @Override
    public CategoryResponse updateCategory(Long categoryId, CategoryRequest categoryRequest) {
        Category category = categoryRepository.findById(categoryId).get();
        categoryRequestConverter.update(category, categoryRequest);
        return categoryResponseConverter.viewCategory(categoryRepository.save(category));
    }

    @Override
    public SimpleResponse deleteCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException(String.format("Category not found with id: " + categoryId)));
        categoryRepository.delete(category);
        return new SimpleResponse("The category removed!");
    }
}
