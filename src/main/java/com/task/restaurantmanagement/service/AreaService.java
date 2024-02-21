package com.task.restaurantmanagement.service;

import com.task.restaurantmanagement.entity.Area;
import com.task.restaurantmanagement.entity.City;
import com.task.restaurantmanagement.entity.classes.AreaRequest;
import com.task.restaurantmanagement.exception.DuplicateResourceException;

import java.util.List;

public interface AreaService {

    boolean addArea(Area area);

    List<Area> findAllArea();

    Area getArea(int id);

    void deleteArea(int id);

    void updateArea(AreaRequest request) ;

    List<Area> findAllByCity(int cId);
}
