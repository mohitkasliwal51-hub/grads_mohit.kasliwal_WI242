import { ChangeDetectorRef, Component } from '@angular/core';
import { StudentService } from '../student';
import { Student } from '../../models/student';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';

import { StudentAnalyticsComponent } from '../student-analytics/student-analytics';
import { StudentFilterComponent } from '../student-filter/student-filter';

@Component({
  selector: 'app-student-list',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    StudentAnalyticsComponent,
    StudentFilterComponent
  ],
  templateUrl: './student-list.html',
  styleUrl: './student-list.css',
})
export class StudentList {

  students: Student[] = []
  schoolCount = 0
  standardCount = 0
  strengthCount = 0
  passedCount = 0
  role = localStorage.getItem('role')


  constructor(
    private studentService: StudentService,
    private cdr: ChangeDetectorRef
  ) { }

  ngOnInit() {
    this.loadStudents()
  }

  loadStudents() {

    this.studentService.getStudents()
      .subscribe(data => {

        // this.students = [...data]
        this.students = data
        this.cdr.detectChanges()
      })

  }

  deleteStudent(regNo: number) {

    if (confirm("Are you sure you want to delete this student?")) {

      this.studentService.deleteStudent(regNo)
        .subscribe(() => {

          this.loadStudents()

        })

    }

  }

  showStudentsBySchool(school: string) {

    this.studentService
      .getStudentsBySchool(school)
      .subscribe(data => {

        this.students = data
        this.cdr.detectChanges()
      })

  }

  showPassedStudents() {

    this.studentService
      .getPassedStudents()
      .subscribe(data => {

        this.students = data
        this.passedCount = data.length
        this.cdr.detectChanges()

      })

  }

  getSchoolCount(school: string) {

    this.studentService
      .countStudentsBySchool(school)
      .subscribe(count => {

        this.schoolCount = count
        this.cdr.detectChanges()

      })

  }

  getStandardCount(standard: number) {

    this.studentService
      .countStudentsByStandard(standard)
      .subscribe(count => {

        this.standardCount = count
        this.cdr.detectChanges()

      })

  }

  getStrength(data: { gender: string, standard: number }) {

    this.studentService
      .getStrengthByGenderAndStandard(data.gender, data.standard)
      .subscribe(count => {

        this.strengthCount = count
        this.cdr.detectChanges()

      })

  }

  resetTable() {
    this.loadStudents()
  }

  trackByRegNo(index: number, student: Student) {
    return student.regNo
  }

}