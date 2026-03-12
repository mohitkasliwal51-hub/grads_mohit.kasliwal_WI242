import { Injectable } from '@angular/core'
import { HttpClient, HttpParams } from '@angular/common/http'
import { Observable } from 'rxjs'
import { Student } from '../models/student'

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  private readonly API = 'http://localhost:8181/students'

  constructor(private http: HttpClient) { }

  // ---------------- CRUD ----------------

  getStudents(): Observable<Student[]> {
    return this.http.get<Student[]>(this.API)
  }

  getStudentByRegNo(regNo: number): Observable<Student> {
    return this.http.get<Student>(`${this.API}/${regNo}`)
  }

  addStudent(student: Student): Observable<Student> {
    return this.http.post<Student>(this.API, student)
  }

  updateStudent(student: Student): Observable<Student> {
    return this.http.put<Student>(`${this.API}/${student.regNo}`, student)
  }

  deleteStudent(regNo: number): Observable<void> {
    return this.http.delete<void>(`${this.API}/${regNo}`)
  }

  // ---------------- Filters ----------------

  getStudentsBySchool(name: string): Observable<Student[]> {

    const params = new HttpParams().set('name', name)

    return this.http.get<Student[]>(`${this.API}/school`, { params })
  }

  getPassedStudents(): Observable<Student[]> {

    const params = new HttpParams().set('pass', true)

    return this.http.get<Student[]>(`${this.API}/result`, { params })
  }

  // ---------------- Analytics ----------------

  countStudentsBySchool(name: string): Observable<number> {

    const params = new HttpParams().set('name', name)

    return this.http.get<number>(`${this.API}/school/count`, { params })
  }

  countStudentsByStandard(standard: number): Observable<number> {

    const params = new HttpParams().set('class', standard)

    return this.http.get<number>(`${this.API}/school/standard/count`, { params })
  }

  getStrengthByGenderAndStandard(
    gender: string,
    standard: number
  ): Observable<number> {

    const params = new HttpParams()
      .set('gender', gender)
      .set('standard', standard)

    return this.http.get<number>(`${this.API}/strength`, { params })
  }
}