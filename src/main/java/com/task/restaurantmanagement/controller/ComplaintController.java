package com.task.restaurantmanagement.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.restaurantmanagement.entity.Complaint;
import com.task.restaurantmanagement.entity.classes.ComplaintRequest;
import com.task.restaurantmanagement.fileupload.FileUploadUtil;
import com.task.restaurantmanagement.service.ComplaintService;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class ComplaintController {

    @Autowired
    ComplaintService complaintService;

    @Autowired
    ObjectMapper objectMapper;
    @PostMapping(value = "/restaurant/saveComplaint",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public String saveComplaint(@RequestParam("attachment")MultipartFile file,@RequestParam("data") String data) {

        try{
            ComplaintRequest request = objectMapper.readValue(data,ComplaintRequest.class);
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String fileCode = FileUploadUtil.saveFile(fileName,file);
            complaintService.saveComplaint(fileCode,request);
            return "Valid";
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "Invalid";
        }
    }

    @PostMapping("/restaurant/fetchAll")
    public ResponseEntity<List<Complaint>> fetchAll(@RequestParam String restEmail){
        try{
            return new ResponseEntity<>(complaintService.findAllByOrder(restEmail), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
