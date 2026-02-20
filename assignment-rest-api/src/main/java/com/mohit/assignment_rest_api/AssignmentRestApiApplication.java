package com.mohit.assignment_rest_api;

// Student
// ------------
// RegNo
// RollNo
// Name
// Standard
// School
// Gender
// Percentage


// GET    /students                  - Get all the students
// GET   /students/regNo              - Specific student details of the given Registration number
// POST  /students                  - Insert a student record
// PUT    /students/regNo              - Update specific student record
// PATCH   /students/regNo              - Update with given attributes
// DELETE  /students/regNo              - Remove the student record for the given Registration number
// GET    /students/school?name=KV          - List all students belonging to that school
// GET    /students/school/count?name=DPS        - Total strength in that school
// GET    /students/school/standard/count?class=5      - Total number of students in 5th standard
// GET   /students/result?pass=true/false        - List the students in descending order of their percentage (40% and above is pass)
// GET    /students/strength?gender=MALE&standard=5  - How many Male students in standard 5

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AssignmentRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssignmentRestApiApplication.class, args);
	}

}
