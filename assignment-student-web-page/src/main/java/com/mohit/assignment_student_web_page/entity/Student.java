package com.mohit.assignment_student_web_page.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="students")
public class Student {

    @Id
    private Integer roll_no;
    private String name;
    private String standard;
    private String school;
    public Student() {
    }
    public Integer getRollNo() {
        return roll_no;
    }
    public void setRollNo(Integer rollNo) {
        this.roll_no = rollNo;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getStandard() {
        return standard;
    }
    public void setStandard(String standard) {
        this.standard = standard;
    }
    public String getSchool() {
        return school;
    }
    public void setSchool(String school) {
        this.school = school;
    }

}
