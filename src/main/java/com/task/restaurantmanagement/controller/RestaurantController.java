package com.task.restaurantmanagement.controller;

import com.task.restaurantmanagement.entity.Admin;
import com.task.restaurantmanagement.entity.Restaurant;
import com.task.restaurantmanagement.entity.UserType;
import com.task.restaurantmanagement.entity.classes.RestaurantRequest;
import com.task.restaurantmanagement.repository.RestaurantRepository;
import com.task.restaurantmanagement.service.AdminService;
import com.task.restaurantmanagement.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class RestaurantController {

    @Autowired
    AdminService adminService;

    @Autowired
    RestaurantService restaurantService;
    @PostMapping("/saveRestaurant")
    public ResponseEntity<?> saveRestaurant(@RequestBody RestaurantRequest restaurantRequest){
        try{

           Restaurant rest =  restaurantService.saveRestaurant(restaurantRequest);
            Admin a = new Admin();
            a.setEmail(restaurantRequest.getEmail());
            a.setPassword(restaurantRequest.getPassword());
            a.setUserType(UserType.RESTAURANT);
            adminService.saveAdmin(a);

            return new ResponseEntity<Restaurant>(rest, HttpStatus.ACCEPTED);
        }catch (Exception e){
            System.out.println(e);
           return new ResponseEntity<>("Email already used",HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/restaurant/restaurant-dashboard")
    public String restaurantDashboardPage(){
        return "restaurant-dashboard";
    }

    @GetMapping("/restaurant/manage-product")
    public String manageProductsPage(){
        return "manage-products";
    }


    @GetMapping("/restaurant/manage-offers")
    public String manageOffers(){
        return "manage-offers";
    }

    @GetMapping("/restaurant/manage-complaint")
    public String manageComplaint(){
        return "manage-complaint";
    }

}
