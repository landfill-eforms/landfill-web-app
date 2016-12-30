import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import {Routes, RouterModule} from '@angular/router';
import {MaterialModule} from '@angular/material'

import {LoginComponent} from './login/login.component';

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        RouterModule,
        MaterialModule
    ],
    providers: [],
    declarations: [
        LoginComponent,
    ]
})

export class PublicModule {
    
}