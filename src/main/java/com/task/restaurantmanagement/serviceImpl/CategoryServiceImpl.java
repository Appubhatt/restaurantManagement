package com.task.restaurantmanagement.serviceImpl;

import com.task.restaurantmanagement.entity.Category;
import com.task.restaurantmanagement.exception.DuplicateResourceException;
import com.task.restaurantmanagement.repository.CategoryRepository;
import com.task.restaurantmanagement.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public boolean saveCategory(Category category) {
        List<Category> categories = categoryRepository.findByCategoryNameAndCategoryDescription(category.getCategoryName(),category.getCategoryDescription());
        if(categories.isEmpty()){
            categoryRepository.save(category);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAllByOrderByCategoryId();
    }

    @Override
    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category findById(int id) {

        Optional<Category> category = categoryRepository.findById(id);
        return category.get();
    }

    @Override
    public void updateCategory(Category category) throws DuplicateResourceException {
        List<Category> catList = categoryRepository.findByCategoryNameAndCategoryDescription(category.getCategoryName(),category.getCategoryDescription());
        if(catList.isEmpty()){
            Category c = categoryRepository.getById(category.getCategoryId());
            c.setCategoryDescription(category.getCategoryDescription());
            c.setCategoryName(category.getCategoryName());
            categoryRepository.save(c);
        }else {
            throw new DuplicateResourceException("Resource already exists");
        }
    }
}
