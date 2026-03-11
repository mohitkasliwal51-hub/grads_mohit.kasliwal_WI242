import { Injectable } from '@angular/core';
import { Student } from '../models/student';

@Injectable({
  providedIn: 'root',
})
export class StudentService {

  students: Student[] = []
  constructor() {
    const storedStudents = localStorage.getItem('students')

    if (storedStudents) {
      this.students = JSON.parse(storedStudents)
    }
  }

  getStudents() {
    return this.students
  }

  addStudent(student: Student) {
    this.students.push(student)
    this.saveStudents()
  }

  deleteStudent(regNo: number) {
    this.students = this.students.filter(s => s.regNo !== regNo)

    this.saveStudents()
  }

  getStudentByRegNo(regNo: number) {
    return this.students.find(s => s.regNo == regNo)
  }

  updateStudent(updatedStudent: Student) {

    const index = this.students.findIndex(s => s.regNo == updatedStudent.regNo)

    if (index !== -1) {
      this.students[index] = updatedStudent
      this.saveStudents()
    }

  }
  saveStudents() {
    localStorage.setItem('students', JSON.stringify(this.students))
  }

}
