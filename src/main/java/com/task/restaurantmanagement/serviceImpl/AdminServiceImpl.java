package com.task.restaurantmanagement.serviceImpl;

import com.task.restaurantmanagement.entity.Admin;
import com.task.restaurantmanagement.repository.AdminRepository;
import com.task.restaurantmanagement.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminRepository adminRepository;


    @Override
    public Admin findByEmailAndPassword(String email, String password) {

        return adminRepository.findByEmailAndPassword(email,password);
    }

    @Override
    public void saveAdmin(Admin admin) {
        adminRepository.save(admin);
    }
}
