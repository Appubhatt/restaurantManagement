package com.task.restaurantmanagement.entity.classes;

import com.task.restaurantmanagement.entity.Status;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComplaintRequest {

    private String subject;

    private String description;

    private String restEmail;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column
    private String reply;
}
