import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { StudentService } from '../student';
import { Student } from '../../models/student';

@Component({
  selector: 'app-student-edit',
  imports: [FormsModule],
  templateUrl: './student-edit.html',
  styleUrl: './student-edit.css',
})
export class StudentEdit {
  student: Student = {
    regNo: 0,
    rollNo: 0,
    name: '',
    standard: '',
    school: ''
  }

  constructor(
    private route: ActivatedRoute,
    private studentService: StudentService,
    private router: Router
  ) { }

  ngOnInit() {

    const regNo = Number(this.route.snapshot.params['id'])

    const existingStudent = this.studentService.getStudentByRegNo(regNo)

    if (existingStudent) {
      this.student = { ...existingStudent }
    }

  }

  updateStudent() {

    this.studentService.updateStudent(this.student)

    this.router.navigate(['/dashboard/students'])

  }

}
