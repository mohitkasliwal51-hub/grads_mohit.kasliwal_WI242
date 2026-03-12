import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentAnalytics } from './student-analytics';

describe('StudentAnalytics', () => {
  let component: StudentAnalytics;
  let fixture: ComponentFixture<StudentAnalytics>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StudentAnalytics],
    }).compileComponents();

    fixture = TestBed.createComponent(StudentAnalytics);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
