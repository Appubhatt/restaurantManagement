package com.task.restaurantmanagement.entity.classes;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductRequest {

   private int productId;

   private String productName;

   private String description;

   private int productPrice;

   private int categoryId;

   private int subcategoryId;

   private String restEmail;
}
