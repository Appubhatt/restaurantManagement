package com.task.restaurantmanagement.service;

import com.task.restaurantmanagement.entity.Restaurant;
import com.task.restaurantmanagement.entity.classes.RestaurantRequest;

public interface RestaurantService {


    Restaurant saveRestaurant (RestaurantRequest rest);
}
