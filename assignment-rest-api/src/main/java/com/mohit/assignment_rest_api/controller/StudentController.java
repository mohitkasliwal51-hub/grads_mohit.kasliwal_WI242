package com.mohit.assignment_rest_api.controller;

import com.mohit.assignment_rest_api.entity.*;
import com.mohit.assignment_rest_api.service.StudentService;
import com.mohit.assignment_rest_api.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;
    private final StudentRepository repo;

    public StudentController(StudentService service, StudentRepository repo) {
        this.service = service;
        this.repo = repo;
    }

    // GET /students
    @Operation(summary = "Get all students",
            description = "Retrieve the complete list of students available in the system")
    @ApiResponse(responseCode = "200", description = "Students fetched successfully")
    @GetMapping
    public List<Student> getAll() {
        return service.getAllStudents();
    }

    // GET /students/{regNo}
    @Operation(summary = "Get student by registration number",
            description = "Retrieve details of a specific student using registration number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student found"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @GetMapping("/{regNo}")
    public Optional<Student> getByRegNo(
            @Parameter(description = "Registration number of the student", example = "1")
            @PathVariable Long regNo) {
        return service.getStudent(regNo);
    }

    // POST /students
    @Operation(summary = "Create a new student",
            description = "Insert a new student record into the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Student created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid student data provided")
    })
    @PostMapping
    public Student create(@RequestBody Student student) {
        return service.saveStudent(student);
    }

    // PUT /students/{regNo}
    @Operation(summary = "Update entire student record",
            description = "Replace all fields of an existing student using registration number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student updated successfully"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @PutMapping("/{regNo}")
    public Student update(
            @Parameter(description = "Registration number of the student", example = "1")
            @PathVariable Long regNo,
            @RequestBody Student student) {
        return service.updateStudent(regNo, student);
    }

    // PATCH /students/{regNo}
    @Operation(summary = "Partially update student record",
            description = "Update specific fields of an existing student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student partially updated"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @PatchMapping("/{regNo}")
    public Student patch(
            @Parameter(description = "Registration number of the student", example = "1")
            @PathVariable Long regNo,
            @RequestBody Map<String, Object> updates) {
        return service.patchStudent(regNo, updates);
    }

    // DELETE /students/{regNo}
    @Operation(summary = "Delete student by registration number",
            description = "Remove a student record from the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Student deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @DeleteMapping("/{regNo}")
    public void delete(
            @Parameter(description = "Registration number of the student", example = "1")
            @PathVariable Long regNo) {
        service.deleteStudent(regNo);
    }

    // GET /students/school?name=KV
    @Operation(summary = "Get students by school",
            description = "Retrieve all students belonging to a specific school")
    @ApiResponse(responseCode = "200", description = "Students retrieved successfully")
    @GetMapping("/school")
    public List<Student> getBySchool(
            @Parameter(description = "Name of the school", example = "KV")
            @RequestParam String name) {
        return repo.findBySchool(name);
    }

    // GET /students/school/count?name=DPS
    @Operation(summary = "Count students in a school",
            description = "Get total number of students in a particular school")
    @ApiResponse(responseCode = "200", description = "Count retrieved successfully")
    @GetMapping("/school/count")
    public long countBySchool(
            @Parameter(description = "Name of the school", example = "DPS")
            @RequestParam String name) {
        return repo.countBySchool(name);
    }

    // GET /students/school/standard/count?class=5
    @Operation(summary = "Count students by standard",
            description = "Get total number of students in a specific standard")
    @ApiResponse(responseCode = "200", description = "Count retrieved successfully")
    @GetMapping("/school/standard/count")
    public long countByStandard(
            @Parameter(description = "Standard/Class number", example = "5")
            @RequestParam("class") Integer standard) {
        return repo.countByStandard(standard);
    }

    // GET /students/result?pass=true
    @Operation(summary = "Get students by result",
            description = "Retrieve students sorted by percentage in descending order. Pass = 40% and above.")
    @ApiResponse(responseCode = "200", description = "Students retrieved successfully")
    @GetMapping("/result")
    public List<Student> getResults(
            @Parameter(description = "true for passed students (>=40%), false for all students", example = "true")
            @RequestParam boolean pass) {
        if (pass)
            return repo.findByPercentageGreaterThanEqualOrderByPercentageDesc(40.0);
        else
            return repo.findAll();
    }

    // GET /students/strength?gender=MALE&standard=5
    @Operation(summary = "Get strength by gender and standard",
            description = "Count number of students based on gender and standard")
    @ApiResponse(responseCode = "200", description = "Count retrieved successfully")
    @GetMapping("/strength")
    public long getStrength(
            @Parameter(description = "Gender of student (MALE/FEMALE)", example = "MALE")
            @RequestParam Gender gender,
            @Parameter(description = "Standard/Class number", example = "5")
            @RequestParam Integer standard) {
        return repo.countByGenderAndStandard(gender, standard);
    }
}