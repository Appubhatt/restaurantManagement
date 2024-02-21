package com.task.restaurantmanagement.service;

import com.task.restaurantmanagement.entity.Category;
import com.task.restaurantmanagement.exception.DuplicateResourceException;

import java.util.List;

public interface CategoryService {

    boolean saveCategory(Category category);

    List<Category> findAll();

    void deleteCategory(int id);

    Category findById(int id);

    void updateCategory(Category category) throws DuplicateResourceException;



}
