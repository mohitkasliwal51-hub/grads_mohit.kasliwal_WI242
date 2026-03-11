import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class Auth {
  login(username: string, password: string): boolean {
    if (username === 'admin' && password === 'admin') {
      localStorage.setItem('role', 'admin');
      return true;
    }
    if (username === 'staff' && password === 'staff') {
      localStorage.setItem('role', 'staff');
      return true;
    }
    return false;
  }

  getRole() {
    return localStorage.getItem('role');
  }
  isLoggedIn(): boolean {
    // return this.getRole() !== null;
    return localStorage.getItem('role') !== null;
  }

  logout() {
    localStorage.removeItem('role');
  }
}