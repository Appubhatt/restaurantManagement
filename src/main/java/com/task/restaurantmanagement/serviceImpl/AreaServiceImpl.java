package com.task.restaurantmanagement.serviceImpl;

import com.task.restaurantmanagement.entity.Area;
import com.task.restaurantmanagement.entity.City;
import com.task.restaurantmanagement.entity.classes.AreaRequest;
import com.task.restaurantmanagement.exception.DuplicateResourceException;
import com.task.restaurantmanagement.repository.AreaRepository;
import com.task.restaurantmanagement.repository.CityRepository;
import com.task.restaurantmanagement.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    AreaRepository areaRepository;

    @Autowired
    CityRepository cityRepository;

    @Override
    public boolean addArea(Area area) {
        List<Area> areaList = areaRepository.findByAreaNameAndAreaDescription(area.getAreaName(), area.getAreaDescription());
        if (!areaList.isEmpty()) {
            return false;
        } else {
            areaRepository.save(area);
            return true;
        }
    }

    @Override
    public List<Area> findAllArea() {
        return areaRepository.findAllByOrderByAreaIdAsc();
    }

    @Override
    public Area getArea(int id) {
        Optional<Area> area = areaRepository.findById(id);
        return area.get();
    }

    @Override
    public void deleteArea(int id) {
        areaRepository.deleteById(id);
    }

    @Override
    public void updateArea(AreaRequest request)  {
        System.out.println(request.toString());
       Area area = areaRepository.getById(request.getAreaId());
       area.setAreaDescription(request.getAreaDescription());
       area.setAreaName(request.getAreaName());
       area.setCity(cityRepository.getById(request.getCityId()));
       areaRepository.save(area);
    }

    @Override
    public List<Area> findAllByCity(int cid) {
        City c = cityRepository.getById(cid);

        return areaRepository.findByCity(cid);
    }

}

