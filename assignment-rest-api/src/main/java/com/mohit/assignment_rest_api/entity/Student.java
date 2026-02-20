package com.mohit.assignment_rest_api.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    private Long regNo;

    private Long rollNo;
    private String name;
    private Integer standard;
    private String school;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Double percentage;
}