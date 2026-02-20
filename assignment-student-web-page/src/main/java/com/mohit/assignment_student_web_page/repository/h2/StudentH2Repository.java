package com.mohit.assignment_student_web_page.repository.h2;


import com.mohit.assignment_student_web_page.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentH2Repository extends JpaRepository<Student, Integer> {

}
