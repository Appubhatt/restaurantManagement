package com.task.restaurantmanagement.repository;

import com.task.restaurantmanagement.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City,Integer> {


    Optional<City> findByCityName(String cityName);

    Optional<City> findByCityNameAndCityDescription(String cityName, String desc);

    List<City> findAllByOrderByCityIdAsc();
}
