package com.task.restaurantmanagement.service;

import com.task.restaurantmanagement.entity.Restaurant;
import com.task.restaurantmanagement.entity.classes.RestaurantRequest;

import java.util.List;

public interface RestaurantService {

    List<Restaurant> fetchAll ();
    Restaurant saveRestaurant (RestaurantRequest rest);
}
