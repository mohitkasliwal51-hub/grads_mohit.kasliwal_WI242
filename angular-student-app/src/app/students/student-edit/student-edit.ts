import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { StudentService } from '../student';
import { Student } from '../../models/student';
import { CommonModule } from '@angular/common';
import { ChangeDetectorRef } from '@angular/core';

@Component({
  selector: 'app-student-edit',
  imports: [FormsModule, CommonModule],
  templateUrl: './student-edit.html',
  styleUrl: './student-edit.css',
})
export class StudentEdit {
  student: Student = {
    regNo: 0,
    rollNo: 0,
    name: '',
    standard: 0,
    school: '',
    gender: '',
    percentage: 0
  }

  constructor(
    private route: ActivatedRoute,
    private studentService: StudentService,
    private router: Router,
    private cdr: ChangeDetectorRef
  ) { }

  ngOnInit() {

    const regNo = Number(this.route.snapshot.params['id'])

    this.studentService.getStudentByRegNo(regNo).subscribe(data => {
      this.student = data
      this.cdr.detectChanges()

    })
  }

  updateStudent() {

    this.studentService.updateStudent(this.student).subscribe(() => {
      this.router.navigate(['/dashboard/students'])

    })

  }

}
