package com.task.restaurantmanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.restaurantmanagement.entity.Product;
import com.task.restaurantmanagement.entity.classes.ProductRequest;
import com.task.restaurantmanagement.fileupload.FileUploadUtil;
import com.task.restaurantmanagement.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ObjectMapper objectMapper;
    @PostMapping(value = "/restaurant/saveProduct",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public String saveProduct(@RequestParam("productImage")MultipartFile file, @RequestParam("data")String request){

        try{

            ProductRequest req = objectMapper.readValue(request,ProductRequest.class);
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String fileCode = FileUploadUtil.saveFile(fileName,file);

            productService.saveProduct(fileCode,req);

            return "Valid";


        }catch (Exception e){

            System.out.println(e.getMessage());
            return "Invalid Data";
        }
    }


    @PostMapping("/restaurant/fetchAllProducts")
    public ResponseEntity<List<Product>> fetchAllProducts(@RequestParam String restEmail){
        System.out.println(restEmail);
        try{
            return new ResponseEntity<>(productService.fetchAllProducts(restEmail), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
