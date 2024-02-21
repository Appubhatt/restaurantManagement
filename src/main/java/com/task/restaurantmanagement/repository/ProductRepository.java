package com.task.restaurantmanagement.repository;

import com.task.restaurantmanagement.entity.Product;
import com.task.restaurantmanagement.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    List<Product> findAllByRestaurantOrderByProductId(Restaurant restaurant);

}
