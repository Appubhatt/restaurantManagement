package com.task.restaurantmanagement.repository;

import com.task.restaurantmanagement.entity.Offers;
import com.task.restaurantmanagement.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offers,Integer> {

    List<Offers> findAllByRestaurantOrderById(Restaurant r);
}
