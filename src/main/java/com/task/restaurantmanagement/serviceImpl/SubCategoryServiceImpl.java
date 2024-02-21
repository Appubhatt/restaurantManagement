package com.task.restaurantmanagement.serviceImpl;

import com.task.restaurantmanagement.entity.Category;
import com.task.restaurantmanagement.entity.SubCategory;
import com.task.restaurantmanagement.entity.classes.SubcategoryRequest;
import com.task.restaurantmanagement.exception.DuplicateResourceException;
import com.task.restaurantmanagement.repository.CategoryRepository;
import com.task.restaurantmanagement.repository.SubCategoryRepository;
import com.task.restaurantmanagement.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    @Autowired
    SubCategoryRepository subCategoryRepository;

    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public void addSubcategory(SubCategory subCategory) {
        subCategoryRepository.save(subCategory);
    }

    @Override
    public List<SubCategory> findAll() {
        return subCategoryRepository.findAllByOrderBySubcategoryId();
    }

    @Override
    public void deleteById(int id) {
        subCategoryRepository.deleteById(id);
    }

    @Override
    public SubCategory findById(int id) {
        return subCategoryRepository.findById(id).get();
    }

    @Override
    public void updateSubcategory(SubcategoryRequest request) {
        Optional<SubCategory> optional = subCategoryRepository.findById(request.getSubcategoryId());

        optional.get().setSubcategoryName(request.getSubcategoryName());
        optional.get().setSubcategoryDescription(request.getSubcategoryDescription());
        optional.get().setCategory(categoryRepository.getById(request.getCategoryId()));
        subCategoryRepository.save(optional.get());
    }

    @Override
    public List<SubCategory> findByCategoryId(int catId) {

        return subCategoryRepository.findAllByCategory(catId);
    }
}
