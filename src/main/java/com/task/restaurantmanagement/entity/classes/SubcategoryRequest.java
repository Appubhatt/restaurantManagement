package com.task.restaurantmanagement.entity.classes;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
public class SubcategoryRequest {

    private int subcategoryId;

    private int categoryId;

    private String subcategoryName;

    private String subcategoryDescription;
}
