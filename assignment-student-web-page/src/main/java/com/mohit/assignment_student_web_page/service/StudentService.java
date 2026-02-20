package com.mohit.assignment_student_web_page.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mohit.assignment_student_web_page.entity.Student;
import com.mohit.assignment_student_web_page.repository.h2.StudentH2Repository;
import com.mohit.assignment_student_web_page.repository.postgres.StudentPostgresRepository;

@Service
public class StudentService {

    private final StudentH2Repository h2Repository;
    private final StudentPostgresRepository postgresRepository;

    public StudentService(StudentH2Repository h2Repository,
            StudentPostgresRepository postgresRepository) {
        this.h2Repository = h2Repository;
        this.postgresRepository = postgresRepository;
    }

    @Transactional
    public void saveStudent(Student student) {

        // Save in H2
        h2Repository.save(student);

        // Save in PostgreSQL
        postgresRepository.save(student);
    }
}
