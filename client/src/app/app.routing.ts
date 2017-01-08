import {Routes, RouterModule} from '@angular/router';
import {ModuleWithProviders} from "@angular/core";
import {AuthGuard} from "./services/auth/authguard";
import {LoginComponent} from './components/public/login/login.component';
import {NavigationBaseComponent} from './components/navigation/navigation-base/navigation-base.component';
import {InstantaneousTestComponent} from './components/test/instantaneous-test/instantaneous-test.component';
import {InstantaneousReportTestComponent} from './components/test/instantaneous-report-test/instantaneous-report-test.component';
import {InstantaneousUploadTestComponent} from './components/test/instantaneous-upload-test/instantaneous-upload-test.component';


const PublicRoutes:Routes = [
    {
        path: '', 
        redirectTo: 'login',
        pathMatch: 'full',
    },
    {
        path: 'login',
        component: LoginComponent,   
    }
];

const RestrictedRoutes:Routes = [
    {
        path: 'something',
        component: NavigationBaseComponent,
        children: [
            {
                path: 'instantaneous_report',
                component: InstantaneousReportTestComponent,
            },
            {
                path: 'instantaneous_test',
                component: InstantaneousTestComponent,
            },
            {
                path: 'instantaneous_upload',
                component: InstantaneousUploadTestComponent,
            }
        ]
    }
];

const TestRoutes:Routes = [
    {
        path: 'instantaneous_report',
        component: InstantaneousReportTestComponent,
        // canActivate: [AuthGuard],
        // data: {roles: [
        //     "SUPER_ADMIN",
        //     "ROLE_USER"
        // ]}
    },
    {
        path: 'instantaneous_test',
        component: InstantaneousTestComponent,
        canActivate: [AuthGuard],
        data: {roles: [
            "ROLE_ADMIN",
        ]}
    },
    {
        path: 'instantaneous_upload',
        component: InstantaneousUploadTestComponent,
        canActivate: [AuthGuard],
        data: {roles: [
            "ROLE_ADMIN",
        ]}
    },

];

export const AppRoutes:any[] = [
    ...PublicRoutes,
    ...RestrictedRoutes,
    ...TestRoutes,
];

export const AppRouterProviders: any[] = [

]

export const Routing:ModuleWithProviders = RouterModule.forRoot(AppRoutes);