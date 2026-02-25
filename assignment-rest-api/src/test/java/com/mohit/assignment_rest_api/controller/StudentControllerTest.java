package com.mohit.assignment_rest_api.controller;

import com.mohit.assignment_rest_api.entity.Student;
import com.mohit.assignment_rest_api.repository.StudentRepository;
import com.mohit.assignment_rest_api.service.StudentService;
import com.mohit.assignment_rest_api.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService service;
    @MockBean
    private StudentRepository repo;

    @Test
    void testGetByRegNo_WhenStudentExists() throws Exception {

        Student student = new Student();
        student.setRegNo(1L);
        student.setName("Mohit");

        when(service.getStudent(1L)).thenReturn(Optional.of(student));

        mockMvc.perform(get("/students/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Mohit"));
    }

    @Test
    void testGetByRegNo_WhenStudentNotFound() throws Exception {

        when(service.getStudent(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/students/1"))
                .andExpect(status().isAccepted())
                .andExpect(content().string("Student not found for registration number: 1"));
    }
}