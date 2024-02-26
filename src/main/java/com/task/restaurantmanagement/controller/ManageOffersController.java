package com.task.restaurantmanagement.controller;

import com.task.restaurantmanagement.entity.Offers;
import com.task.restaurantmanagement.entity.classes.OfferRequest;
import com.task.restaurantmanagement.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ManageOffersController {

    @Autowired
    OfferService offerService;
    @PostMapping("/restaurant/saveOffer")
    @ResponseBody
    public String saveOffer(@RequestBody OfferRequest request){
        try {
            offerService.saveOffer(request);
            return "Save";
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "Error";
        }
    }

    @PostMapping("/restaurant/fetchAllOffersEmail")
    public ResponseEntity<List<Offers>> fetchAllOffersByEmail(@RequestParam String restEmail){
        try{
            return new ResponseEntity<>(offerService.fetchAllOffers(restEmail), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/admin/fetch-all-offers")
    public ResponseEntity<List<Offers>> DisplayAllOffers(){
        try{
            return new ResponseEntity<>(offerService.fetchAll(), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
