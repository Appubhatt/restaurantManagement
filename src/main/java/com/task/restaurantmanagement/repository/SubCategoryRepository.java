package com.task.restaurantmanagement.repository;

import com.task.restaurantmanagement.entity.Category;
import com.task.restaurantmanagement.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory,Integer> {

    List<SubCategory> findAllByOrderBySubcategoryId();

    @Query(value = "select * from sub_category where category_id = :catId",
            nativeQuery = true)
    List<SubCategory> findAllByCategory(@Param("catId") int catId);
}
