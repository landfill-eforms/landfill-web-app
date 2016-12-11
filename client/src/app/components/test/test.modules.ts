import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import {Routes, RouterModule} from '@angular/router';

import {InstantaneousReportTestComponent} from './instantaneous-report-test/instantaneous-report-test.component';
import {InstantaneousTestComponent} from './instantaneous-test/instantaneous-test.component';
import {InstantaneousUploadTestComponent} from './instantaneous-upload-test/instantaneous-upload-test.component';
import {SleepTestComponent} from './sleep-test/sleep-test.component';

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        RouterModule
    ],
    providers: [],
    declarations: [
        InstantaneousReportTestComponent,
        InstantaneousTestComponent,
        InstantaneousUploadTestComponent,
        SleepTestComponent,
    ]
})

export class TestModule {
    constructor() {}
}

