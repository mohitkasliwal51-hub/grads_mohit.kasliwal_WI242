import { CanActivateFn } from '@angular/router';
import { inject } from '@angular/core';
import { Auth } from './auth';

export const roleGuard: CanActivateFn = (route, state) => {
  const auth = inject(Auth);
  // const expectedRole = route.data?.['role'];
  const userRole = auth.getRole();

  // if (userRole === expectedRole) {
  //   return true;
  // }
  const allowedRoles = route.data?.['roles'];
  if (allowedRoles && allowedRoles.includes(userRole)) {
    return true;
  }
  return false;
};
