import { Component } from '@angular/core';
import { StudentService } from '../student';
import { Student } from '../../models/student';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';


@Component({
  selector: 'app-student-list',
  imports: [CommonModule, RouterModule],
  templateUrl: './student-list.html',
  styleUrl: './student-list.css',
})
export class StudentList {

  students: Student[] = []

  role = localStorage.getItem('role')

  constructor(private studentService: StudentService) { }

  ngOnInit() {
    this.students = this.studentService.getStudents()
  }

  deleteStudent(regNo: number) {
    const confirmDelete = confirm("Are you sure you want to delete this student?")

    if (confirmDelete) {
      this.studentService.deleteStudent(regNo)
      this.students = this.studentService.getStudents()
    }
  }
}