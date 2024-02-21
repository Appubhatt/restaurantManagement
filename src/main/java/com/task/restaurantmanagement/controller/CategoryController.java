package com.task.restaurantmanagement.controller;

import com.task.restaurantmanagement.entity.Category;
import com.task.restaurantmanagement.entity.SubCategory;
import com.task.restaurantmanagement.entity.classes.SubcategoryRequest;
import com.task.restaurantmanagement.exception.DuplicateResourceException;
import com.task.restaurantmanagement.service.CategoryService;
import com.task.restaurantmanagement.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    SubCategoryService subCategoryService;

    @PostMapping("/admin/add-category")
    public ResponseEntity<String> addCate(@RequestBody Category category){
        if(categoryService.saveCategory(category)){
            return new ResponseEntity<>("Valid", HttpStatus.ACCEPTED);
        }else{
            return  new ResponseEntity<>("Invalid",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/admin/fetch-category")

    public ResponseEntity<List<Category>> fetchCategory(){
        return new ResponseEntity<List<Category>>(categoryService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/admin/delete-category")
    public String deleteCategory(@RequestParam int categoryId){
        categoryService.deleteCategory(categoryId);
        return "category-details";
    }

    @GetMapping("/admin/get-category")
    public ResponseEntity<Category> getCategory(@RequestParam int categoryId){

        return new ResponseEntity<>(categoryService.findById(categoryId),HttpStatus.OK);
    }

    @PostMapping("/admin/update-category")
    public ResponseEntity<String> updateCategory(@RequestBody Category category){
        try{
            System.out.println(category.toString());
            categoryService.updateCategory(category);
            return new ResponseEntity<>("Category Updated",HttpStatus.ACCEPTED);
        } catch (DuplicateResourceException e) {
            return new ResponseEntity<>("Resource duplication",HttpStatus.BAD_REQUEST);
        }

    }
                                /* Subcategory */

    @PostMapping("/admin/add-subcategory")
    public ResponseEntity<String> addSubcategory(@RequestBody SubcategoryRequest subcategoryRequest){

        System.out.println(subcategoryRequest.toString());
        SubCategory subCategory= new SubCategory();
        Category c = categoryService.findById(subcategoryRequest.getCategoryId());
        subCategory.setSubcategoryName(subcategoryRequest.getSubcategoryName());
        subCategory.setSubcategoryDescription(subcategoryRequest.getSubcategoryDescription());
        subCategory.setCategory(c);

        subCategoryService.addSubcategory(subCategory);
        return new ResponseEntity<>("OK",HttpStatus.ACCEPTED);
    }

    @GetMapping("/admin/fetchSubcategory")
    public ResponseEntity<List<SubCategory>> findAllSubCategory(){
        return new ResponseEntity<>(subCategoryService.findAll(),HttpStatus.ACCEPTED);
    }

    @GetMapping("/admin/deleteSubcategory")
    public String deleteById(@RequestParam int subcategoryId){
        SubCategory subCategory = subCategoryService.findById(subcategoryId);
        subCategory.setCategory(null);
        subCategoryService.deleteById(subcategoryId);
        return "manage-subcategory";
    }

    @GetMapping("/admin/get-subcategory")
    public ResponseEntity<SubCategory> getSubcategory(@RequestParam int subcategoryId){
        return new ResponseEntity<>(subCategoryService.findById(subcategoryId),HttpStatus.OK);
    }

    @PostMapping("/admin/update-subcategory")
    public ResponseEntity<String> updateSubcategory(@RequestBody SubcategoryRequest subcategoryRequest){
        subCategoryService.updateSubcategory(subcategoryRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/admin/fetchBycategoryId")
    public ResponseEntity<List<SubCategory>> findByCategoryId(@RequestParam int categoryId){
        return new ResponseEntity<>(subCategoryService.findByCategoryId(categoryId),HttpStatus.OK);
    }
}
