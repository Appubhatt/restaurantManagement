package com.task.restaurantmanagement.service;

import com.task.restaurantmanagement.entity.Offers;
import com.task.restaurantmanagement.entity.classes.OfferRequest;

import java.util.List;

public interface OfferService {

    Offers saveOffer(OfferRequest request);

    List<Offers> fetchAllOffers(String restEmail);

    List<Offers> fetchAll();
}
