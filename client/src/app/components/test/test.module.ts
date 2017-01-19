import { NgModule } from '@angular/core';
import { CommonModule } from '../common/common.module'
import { DirectivesModule } from './../directives/directives.module';
import { InstantaneousReportTestComponent } from './instantaneous-report-test/instantaneous-report-test.component';
import { InstantaneousTestComponent } from './instantaneous-test/instantaneous-test.component';
import { InstantaneousUploadTestComponent } from './instantaneous-upload-test/instantaneous-upload-test.component';
import { SleepTestComponent } from './sleep-test/sleep-test.component';

@NgModule({
    imports: [
        CommonModule,
        DirectivesModule
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

