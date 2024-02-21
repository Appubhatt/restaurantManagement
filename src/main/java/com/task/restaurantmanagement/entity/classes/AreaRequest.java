package com.task.restaurantmanagement.entity.classes;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class AreaRequest {

    private int cityId;

    private int areaId;

    private String areaName;

    private String areaDescription;
}
