import {Routes, RouterModule} from '@angular/router';
import {ModuleWithProviders} from "@angular/core";
import {LoginComponent} from './components/public/login/login.component';
import {InstantaneousTestComponent} from './components/test/instantaneous-test/instantaneous-test.component';
import {InstantaneousReportTestComponent} from './components/test/instantaneous-report-test/instantaneous-report-test.component';
import {InstantaneousUploadTestComponent} from './components/test/instantaneous-upload-test/instantaneous-upload-test.component';


const PublicRoutes: Routes = [
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

const TestRoutes:Routes = [

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
    },

];

export const AppRoutes:any[] = [
    ...PublicRoutes,
    ...TestRoutes,
];

export const AppRouterProviders: any[] = [

]

export const Routing:ModuleWithProviders = RouterModule.forRoot(AppRoutes);