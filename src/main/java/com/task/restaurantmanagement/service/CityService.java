package com.task.restaurantmanagement.service;

import com.task.restaurantmanagement.entity.City;
import com.task.restaurantmanagement.exception.DuplicateResourceException;

import java.util.List;

public interface CityService {
    void saveCity(City city) throws DuplicateResourceException;

    City findById(int id);

    void deleteCity(int id);

    List<City> findAll();

    void updateCity(City c) throws DuplicateResourceException;
}
