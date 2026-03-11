import { Routes } from '@angular/router';
import { Login } from './auth/login/login';
import { Dashboard } from './dashboard/dashboard';
import { StudentList } from './students/student-list/student-list';
import { StudentAdd } from './students/student-add/student-add';
import { StudentEdit } from './students/student-edit/student-edit';
import { authGuard } from './auth/auth-guard';
import { roleGuard } from './auth/role-guard';

export const routes: Routes = [
    {
        path: 'login',
        title: 'login page',
        component: Login
    },
    {
        path: 'dashboard',
        title: 'dashboard page',
        component: Dashboard,
        canActivate: [authGuard],
        children: [
            {
                path: 'students',
                title: 'students page',
                component: StudentList,
                canActivate: [roleGuard],
                data: { roles: ['admin', 'staff'] }
            },
            {
                path: 'add',
                title: 'add student page',
                component: StudentAdd,
                canActivate: [roleGuard],
                // data: { role: 'admin' }
                data: { roles: ['admin'] }
            },
            {
                path: 'edit/:id',
                title: 'edit student page',
                component: StudentEdit,
                canActivate: [roleGuard],
                // data: { role: 'admin' }
                data: { roles: ['admin'] }
            }
        ]
    },
    {
        path: '',
        redirectTo: 'login',
        pathMatch: 'full'
    }
];
