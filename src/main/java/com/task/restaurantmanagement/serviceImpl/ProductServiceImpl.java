package com.task.restaurantmanagement.serviceImpl;

import com.task.restaurantmanagement.entity.Category;
import com.task.restaurantmanagement.entity.Product;
import com.task.restaurantmanagement.entity.Restaurant;
import com.task.restaurantmanagement.entity.SubCategory;
import com.task.restaurantmanagement.entity.classes.ProductRequest;
import com.task.restaurantmanagement.repository.CategoryRepository;
import com.task.restaurantmanagement.repository.ProductRepository;
import com.task.restaurantmanagement.repository.RestaurantRepository;
import com.task.restaurantmanagement.repository.SubCategoryRepository;
import com.task.restaurantmanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    SubCategoryRepository subCategoryRepository;
    @Override
    public Product saveProduct(String fileName, ProductRequest request) {

        Category c = categoryRepository.getById(request.getCategoryId());
        SubCategory s = subCategoryRepository.getById(request.getSubcategoryId());
        Restaurant r= restaurantRepository.findByEmail(request.getRestEmail());
        Product p = new Product();
        p.setProductPrice(request.getProductPrice());
        p.setProductName(request.getProductName());
        p.setProductDescription(request.getDescription());
        p.setProductImage(fileName);
        p.setCategory(c);
        p.setSubCategory(s);
        p.setRestaurant(r);
        return productRepository.save(p);
    }

    @Override
    public List<Product> fetchAllProducts(String email) {
        Restaurant r = restaurantRepository.findByEmail(email);
        return productRepository.findAllByRestaurantOrderByProductId(r);
    }
}
