import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { StudentService } from '../student';
import { Student } from '../../models/student';

@Component({
  selector: 'app-student-add',
  imports: [FormsModule],
  templateUrl: './student-add.html',
  styleUrl: './student-add.css',
})
export class StudentAdd {
  student: Student = {
    regNo: 0,
    rollNo: 0,
    name: '',
    standard: '',
    school: ''
  }

  constructor(private studentService: StudentService,
    private router: Router) { }

  addStudent() {

    this.studentService.addStudent(this.student)

    this.router.navigate(['/dashboard/students'])

  }
}
