import { Component, output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-student-analytics',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './student-analytics.html'
})
export class StudentAnalyticsComponent {

  schoolName = '';
  standard: number | null = null;
  gender = '';

  schoolCount = output<string>();
  standardCount = output<number>();
  strength = output<{ gender: string, standard: number }>();

  onSchoolCount() {
    this.schoolCount.emit(this.schoolName);
  }

  onStandardCount() {
    if (this.standard !== null) {
      this.standardCount.emit(this.standard);
    }
  }

  onStrength() {
    if (this.standard !== null) {
      this.strength.emit({
        gender: this.gender,
        standard: this.standard
      });
    }
  }

}