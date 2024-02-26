package com.task.restaurantmanagement.repository;

import com.task.restaurantmanagement.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant,Integer> {

    List<Restaurant> findAllByOrderByRestaurantId();
    Restaurant findByEmail(String email);
}
