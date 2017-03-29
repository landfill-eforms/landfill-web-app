import { DirectivesModule } from './../directives/directives.module';
import { MobileUploadComponent } from './mobile-upload/mobile-upload.component';
import { NgModule } from '@angular/core';
import { CommonModule } from './../common/common.module';

@NgModule({
	imports: [
		CommonModule,
        DirectivesModule
    ],
	providers: [],
	declarations: [
        MobileUploadComponent
    ]
})

export class MobileModule {

}