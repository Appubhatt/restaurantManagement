package com.task.restaurantmanagement.serviceImpl;

import com.task.restaurantmanagement.entity.City;
import com.task.restaurantmanagement.exception.DuplicateResourceException;
import com.task.restaurantmanagement.repository.CityRepository;
import com.task.restaurantmanagement.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    CityRepository cityRepository;


    @Override
    public void saveCity(City city) throws DuplicateResourceException {
        Optional<City> optional= cityRepository.findByCityName(city.getCityName());
        if(optional.isPresent()){
                throw new DuplicateResourceException("Resource already exists");
        }
        cityRepository.save(city);


    }

    @Override
    public City findById(int id) {
       Optional<City> c = cityRepository.findById(id);
       return c.get();
    }

    @Override
    public void deleteCity(int id) {
        cityRepository.deleteById(id);
    }

    @Override
    public List<City> findAll() {
        return cityRepository.findAllByOrderByCityIdAsc();
    }

    @Override
    public void updateCity(City city) throws DuplicateResourceException {
        Optional<City> optional= cityRepository.findByCityNameAndCityDescription(city.getCityName(),city.getCityDescription());
        if(optional.isEmpty()){
            City c = cityRepository.getById(city.getCityId());
            c.setCityName(city.getCityName());
            c.setCityDescription(city.getCityDescription());
            cityRepository.save(c);

        }else {
          throw new DuplicateResourceException("Resource already exists");
        }

    }

}
