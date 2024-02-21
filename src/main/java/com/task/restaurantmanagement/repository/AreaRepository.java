package com.task.restaurantmanagement.repository;

import com.task.restaurantmanagement.entity.Area;
import com.task.restaurantmanagement.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AreaRepository extends JpaRepository<Area,Integer> {

    List<Area> findByAreaNameAndAreaDescription(String name, String desc);

    List<Area> findAllByOrderByAreaIdAsc();

    @Query(value = "select * from area where city_id = :cityId",nativeQuery = true)
    List<Area> findByCity(@Param("cityId") int cityId);

   // Optional<Area> findByAreaNameAndAreaDescriptionAndCityId(String name, String desc, int id);
}
