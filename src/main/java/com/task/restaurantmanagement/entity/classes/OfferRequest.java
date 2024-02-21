package com.task.restaurantmanagement.entity.classes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfferRequest {

    private String name;

    private String description;

    private String startTime;

    private String endTime;

    private String discount;

    private int categoryId;

    private int subcategoryId;

    private String restaurantEmail;
}
