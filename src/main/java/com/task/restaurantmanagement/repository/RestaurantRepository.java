package com.task.restaurantmanagement.repository;

import com.task.restaurantmanagement.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant,Integer> {

    Restaurant findByEmail(String email);
}
