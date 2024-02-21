package com.task.restaurantmanagement.service;

import com.task.restaurantmanagement.entity.Product;
import com.task.restaurantmanagement.entity.classes.ProductRequest;

import java.util.List;

public interface ProductService {

    Product saveProduct(String fileName, ProductRequest request);

    List<Product> fetchAllProducts(String email);
}
