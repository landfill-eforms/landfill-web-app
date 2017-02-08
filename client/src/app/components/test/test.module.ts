import { NgModule } from '@angular/core';
import { CommonModule } from '../common/common.module'
import { DirectivesModule } from './../directives/directives.module';
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
        InstantaneousTestComponent,
        InstantaneousUploadTestComponent,
        SleepTestComponent,
    ]
})

export class TestModule {
    constructor() {}
}

