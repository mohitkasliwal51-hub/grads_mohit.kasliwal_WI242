package com.mohit.assignment_rest_api.service;

import com.mohit.assignment_rest_api    .entity.*;
import com.mohit.assignment_rest_api.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {

    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    public Optional<Student> getStudent(Long regNo) {
        return repo.findById(regNo);
    }

    public Student saveStudent(Student student) {
        return repo.save(student);
    }

    public Student updateStudent(Long regNo, Student student) {
        student.setRegNo(regNo);
        return repo.save(student);
    }

    public Student patchStudent(Long regNo, Map<String, Object> updates) {
        Student student = repo.findById(regNo)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        if (updates.containsKey("name"))
            student.setName((String) updates.get("name"));

        if (updates.containsKey("percentage"))
            student.setPercentage(Double.valueOf(updates.get("percentage").toString()));

        return repo.save(student);
    }

    public void deleteStudent(Long regNo) {
        repo.deleteById(regNo);
    }
}