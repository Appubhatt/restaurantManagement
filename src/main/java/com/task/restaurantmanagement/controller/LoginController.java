package com.task.restaurantmanagement.controller;

import com.task.restaurantmanagement.entity.Admin;
import com.task.restaurantmanagement.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @Autowired
    AdminService adminService;

    @GetMapping("/login")
    public String loginPage() {
        System.out.println("LOGIN ");
        return "login";

    }
    @GetMapping("/registration")
    public String Registration(){
        return "restaurant-register";
    }

    @GetMapping("/admin/admin-index")
    public String adminIndexPage() {
        return "admin-index";
    }

    @GetMapping("/admin/manage-area")
    public String manageCity() {
        return "manage-area";
    }

    @GetMapping("/admin/category-details")
    public String categoryDetails(){
        return "category-details";
    }

    @GetMapping("/admin/manage-subcategory")
    public String subCategory(){
        return "manage-subcategory";
    }

    @PostMapping("/login-submit")
    public ResponseEntity<?> loginSubmit(@RequestBody Admin admin) {
        Admin result = adminService.findByEmailAndPassword(admin.getEmail(), admin.getPassword());

       try{
           return new ResponseEntity<>(result,HttpStatus.ACCEPTED);
       }catch (Exception e){
           System.out.println(e);
           return new ResponseEntity<>("Invalid Username password",HttpStatus.BAD_REQUEST);
       }
    }



}
