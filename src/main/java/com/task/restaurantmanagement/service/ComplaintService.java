package com.task.restaurantmanagement.service;

import com.task.restaurantmanagement.entity.Complaint;
import com.task.restaurantmanagement.entity.classes.ComplaintRequest;

import java.util.List;

public interface ComplaintService {

    Complaint saveComplaint(String file, ComplaintRequest request);

    List<Complaint> findAllByOrder(String restEmail);

    List<Complaint> findAll();
}
