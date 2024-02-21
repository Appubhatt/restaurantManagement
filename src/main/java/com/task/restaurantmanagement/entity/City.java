package com.task.restaurantmanagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class City {

    @Id
    @Column(name = "city_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cityId;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "cityDescription")
    private String cityDescription;

    @OneToMany(mappedBy = "city",cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Area> areaList;

}
