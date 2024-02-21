package com.task.restaurantmanagement.service;

import com.task.restaurantmanagement.entity.SubCategory;
import com.task.restaurantmanagement.entity.classes.SubcategoryRequest;
import com.task.restaurantmanagement.exception.DuplicateResourceException;

import java.util.List;

public interface SubCategoryService {

    void addSubcategory(SubCategory subCategory);

    List<SubCategory> findAll();

    void deleteById(int id);

    SubCategory findById(int id);

    void updateSubcategory(SubcategoryRequest request);

    List<SubCategory> findByCategoryId(int catId);
}
