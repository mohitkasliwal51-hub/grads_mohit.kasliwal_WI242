package com.mohit.assignment_student_web_page.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mohit.assignment_student_web_page.entity.Student;
import com.mohit.assignment_student_web_page.service.StudentService;

@Controller
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Load form page
    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("student", new Student());
        return "studentForm";
    }

    // Handle INSERT button
    @PostMapping("/save")
    public String saveStudent(@ModelAttribute Student student) {

        studentService.saveStudent(student);

        return "redirect:/";
    }
}
