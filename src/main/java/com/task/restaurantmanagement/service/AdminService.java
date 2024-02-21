package com.task.restaurantmanagement.service;

import com.task.restaurantmanagement.entity.Admin;

public interface AdminService {
    Admin findByEmailAndPassword(String email,String password);

    void saveAdmin(Admin admin);
}
