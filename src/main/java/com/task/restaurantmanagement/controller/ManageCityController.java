package com.task.restaurantmanagement.controller;

import com.task.restaurantmanagement.entity.Area;
import com.task.restaurantmanagement.entity.City;
import com.task.restaurantmanagement.entity.SubCategory;
import com.task.restaurantmanagement.entity.classes.AreaRequest;

import com.task.restaurantmanagement.exception.DuplicateResourceException;
import com.task.restaurantmanagement.service.AreaService;
import com.task.restaurantmanagement.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ManageCityController {

    @Autowired
    CityService cityService;

    @Autowired
    AreaService areaService;
    @PostMapping("/admin/add-city")
    @ResponseBody
    public ResponseEntity<String> addCity(@RequestBody City c){
        try{
            cityService.saveCity(c);
            return new ResponseEntity<>("Accepted",HttpStatus.ACCEPTED);
        }catch (DuplicateResourceException e ){
            return new ResponseEntity<>("Bad request",HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/admin/update-city")
    public ResponseEntity<String> updateCity(@RequestBody City city){
        try {
            System.out.println(city.toString());
            cityService.updateCity(city);
            return new ResponseEntity<>("valid", HttpStatus.ACCEPTED);
        } catch (DuplicateResourceException e) {
            return new ResponseEntity<>("invalid",HttpStatus.BAD_REQUEST);
        }

    }


    @GetMapping("/admin/getCity")
    @ResponseBody
    public City getCity(@RequestParam int cityId){
        City c = cityService.findById(cityId);
       return c;
    }

    @GetMapping("/admin/deleteCity")
    public String deleteCity(@RequestParam int cityId){

        cityService.deleteCity(cityId);
        return "manage-city";
    }


    @GetMapping("/admin/manage-city")
    public String manageCity(Model model) {
        model.addAttribute("cities",cityService.findAll());
        return "manage-city";
    }

    @GetMapping("/admin/fetchCity")
    public ResponseEntity<List<City>> findAll(){
        return new ResponseEntity<>(cityService.findAll(),HttpStatus.OK);
    }

                            /* Area Controller*/

    @PostMapping("/admin/add-area")
    @ResponseBody
    public String addArea(@RequestBody AreaRequest area){
        City c= cityService.findById(area.getCityId());
        Area areaData = new Area();
        areaData.setAreaDescription(area.getAreaDescription());
        areaData.setAreaName(area.getAreaName());
        areaData.setCity(c);

        c.getAreaList().add(areaData);

       boolean b= areaService.addArea(areaData);

       if(b) return "true";
       else return "false";
    }
    @GetMapping("/admin/fetchArea")
    public ResponseEntity<List<Area>> findAllArea(){
        return new ResponseEntity<>(areaService.findAllArea(),HttpStatus.OK);
    }

    @GetMapping("/admin/getArea")
    public ResponseEntity<Area> getArea(@RequestParam int areaId){
        return new ResponseEntity<>(areaService.getArea(areaId),HttpStatus.ACCEPTED);
    }

    @GetMapping("/admin/deleteArea")
    public String deleteArea(@RequestParam int areaId){
        //areaService.deleteArea(areaId);
        Area areaData = areaService.getArea(areaId);
        areaData.setCity(null);
        areaService.addArea(areaData);
        areaService.deleteArea(areaId);
        return "manage-area";
    }

    @PostMapping("/admin/update-area")
    public ResponseEntity<String> updateArea(@RequestBody AreaRequest request){
        areaService.updateArea(request);
        return new ResponseEntity<>("Updated",HttpStatus.ACCEPTED);
    }

    @GetMapping("/admin/fetchBycityId")
    public ResponseEntity<List<Area>> findByCityId(@RequestParam int cityId){
            System.out.println(cityService.findById(cityId).toString());
        try{
            return new ResponseEntity<>(areaService.findAllByCity(cityId),HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
