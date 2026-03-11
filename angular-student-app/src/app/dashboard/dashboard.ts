import { Component } from '@angular/core';
import { Router, RouterOutlet, RouterModule } from '@angular/router';
import { Auth } from '../auth/auth';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-dashboard',
  imports: [RouterModule, CommonModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard {
  role = ''

  constructor(private auth: Auth, private router: Router) { }

  ngOnInit() {
    this.role = this.auth.getRole() || ''
  }

  logout() {

    this.auth.logout()
    this.router.navigate(['/login'])

  }
}
