package com.task.restaurantmanagement.serviceImpl;

import com.task.restaurantmanagement.entity.Area;
import com.task.restaurantmanagement.entity.City;
import com.task.restaurantmanagement.entity.Restaurant;
import com.task.restaurantmanagement.entity.classes.RestaurantRequest;
import com.task.restaurantmanagement.repository.AreaRepository;
import com.task.restaurantmanagement.repository.CityRepository;
import com.task.restaurantmanagement.repository.RestaurantRepository;
import com.task.restaurantmanagement.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    AreaRepository areaRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Override
    public Restaurant saveRestaurant(RestaurantRequest rest) {
        City c = cityRepository.findById(rest.getCityId()).get();
        Area a = areaRepository.findById(rest.getAreaId()).get();

        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantName(rest.getRestaurantName());
        restaurant.setEmail(rest.getEmail());
        restaurant.setAddress(rest.getAddress());
        restaurant.setPassword(rest.getPassword());
        restaurant.setContactNo(rest.getContactNo());
        restaurant.setCity(c);
        restaurant.setArea(a);
        return restaurantRepository.save(restaurant);
    }
}
