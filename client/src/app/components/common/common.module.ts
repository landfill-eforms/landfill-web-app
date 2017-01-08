import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import {Routes, RouterModule} from '@angular/router';
import {MaterialModule} from '@angular/material'
import {FlexLayoutModule} from "@angular/flex-layout";

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        RouterModule,
        MaterialModule.forRoot(),
        FlexLayoutModule.forRoot()
    ],
    providers: [],
    declarations: [],
    exports: [
        BrowserModule,
        FormsModule,
        RouterModule,
        MaterialModule,
        FlexLayoutModule
    ]
})
export class CommonModule {
    
}