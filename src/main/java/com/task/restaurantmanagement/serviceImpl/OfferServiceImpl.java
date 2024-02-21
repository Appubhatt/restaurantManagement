package com.task.restaurantmanagement.serviceImpl;

import com.task.restaurantmanagement.entity.Category;
import com.task.restaurantmanagement.entity.Offers;
import com.task.restaurantmanagement.entity.Restaurant;
import com.task.restaurantmanagement.entity.SubCategory;
import com.task.restaurantmanagement.entity.classes.OfferRequest;
import com.task.restaurantmanagement.repository.CategoryRepository;
import com.task.restaurantmanagement.repository.OfferRepository;
import com.task.restaurantmanagement.repository.RestaurantRepository;
import com.task.restaurantmanagement.repository.SubCategoryRepository;
import com.task.restaurantmanagement.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {

    @Autowired
    OfferRepository offerRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    SubCategoryRepository subCategoryRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Override
    public Offers saveOffer(OfferRequest request) {
        Category c = categoryRepository.getById(request.getCategoryId());
        SubCategory s = subCategoryRepository.getById(request.getSubcategoryId());
        Restaurant r = restaurantRepository.findByEmail(request.getRestaurantEmail());

        Offers of = new Offers();
        of.setCategory(c);
        of.setSubCategory(s);
        of.setRestaurant(r);
        of.setName(request.getName());
        of.setDescription(request.getDescription());
        of.setDiscount(request.getDiscount());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        // Parse the string to LocalDateTime using the formatter
        LocalDateTime startTime = LocalDateTime.parse(request.getStartTime(), formatter);
        LocalDateTime endTime = LocalDateTime.parse(request.getEndTime(), formatter);

        of.setStartTime(startTime);
        of.setEndTime(endTime);



        return offerRepository.save(of);
    }

    @Override
    public List<Offers> fetchAllOffers(String email) {
        Restaurant r = restaurantRepository.findByEmail(email);
        return offerRepository.findAllByRestaurantOrderById(r);
    }
}
