package com.mohit.assignment_student_web_page.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mohit.assignment_student_web_page.entity.Student;

@Repository
public interface StudentPostgresRepository extends JpaRepository<Student, Integer> {

}
