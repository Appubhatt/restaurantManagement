package com.task.restaurantmanagement.repository;

import com.task.restaurantmanagement.entity.Complaint;
import com.task.restaurantmanagement.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint,Integer> {

    List<Complaint> findAllByRestaurantOrderById(Restaurant r);

    List<Complaint> findAllByOrderById();

}
