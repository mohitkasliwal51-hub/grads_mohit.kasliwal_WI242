import { Component, output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-student-filter',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './student-filter.html'
})
export class StudentFilterComponent {

  schoolName = '';

  showBySchool = output<string>();
  showPassed = output<void>();
  reset = output<void>();

  onSchoolFilter() {
    this.showBySchool.emit(this.schoolName);
  }

  onShowPassed() {
    this.showPassed.emit();
  }

  onReset() {
    this.reset.emit();
  }

}