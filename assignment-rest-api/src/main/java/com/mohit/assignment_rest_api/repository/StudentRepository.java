package com.mohit.assignment_rest_api.repository;


import com.mohit.assignment_rest_api.entity.Student;
import com.mohit.assignment_rest_api.entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findBySchool(String school);

    long countBySchool(String school);

    long countByStandard(Integer standard);

    List<Student> findByPercentageGreaterThanEqualOrderByPercentageDesc(Double percentage);

    long countByGenderAndStandard(Gender gender, Integer standard);
}
