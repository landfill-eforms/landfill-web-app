import { NgModule } from '@angular/core';
import { CommonModule } from '../common/common.module'
import { DirectivesModule } from './../directives/directives.module';
import { InstantaneousUploadTestComponent } from './instantaneous-upload-test/instantaneous-upload-test.component';
import { SleepTestComponent } from './sleep-test/sleep-test.component';

@NgModule({
    imports: [
        CommonModule,
        DirectivesModule
    ],
    providers: [],
    declarations: [
        InstantaneousUploadTestComponent,
        SleepTestComponent,
    ]
})

export class TestModule {
    constructor() {}
}

