package com.task.restaurantmanagement.entity.classes;

import com.task.restaurantmanagement.entity.Area;
import com.task.restaurantmanagement.entity.City;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RestaurantRequest {

    private int restaurantId;

    private String restaurantName;

    private String password;

    private String email;

    private String contactNo;

    private String address;

    private int cityId;

    private int areaId;
}
