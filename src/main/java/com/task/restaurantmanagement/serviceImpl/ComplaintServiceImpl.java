package com.task.restaurantmanagement.serviceImpl;

import com.task.restaurantmanagement.entity.Complaint;
import com.task.restaurantmanagement.entity.Restaurant;
import com.task.restaurantmanagement.entity.Status;
import com.task.restaurantmanagement.entity.classes.ComplaintRequest;
import com.task.restaurantmanagement.repository.ComplaintRepository;
import com.task.restaurantmanagement.repository.RestaurantRepository;
import com.task.restaurantmanagement.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
 public class ComplaintServiceImpl implements ComplaintService {

    @Autowired
    ComplaintRepository complaintRepository;

    @Autowired
    RestaurantRepository restaurantRepository;


    @Override
    public Complaint saveComplaint(String file, ComplaintRequest request) {
        Restaurant r = restaurantRepository.findByEmail(request.getRestEmail());
        Complaint c = new Complaint();
        c.setRestaurant(r);
        c.setStatus(Status.PENDING);
        c.setSubject(request.getSubject());
        c.setDescription(request.getDescription());
        c.setAttachment(file);
        c.setComplaintDate(LocalDateTime.now());
        return complaintRepository.save(c);
    }

    @Override
    public List<Complaint> findAllByOrder(String restEmail) {
        Restaurant r = restaurantRepository.findByEmail(restEmail);
        return complaintRepository.findAllByRestaurantOrderById(r);
    }
}
