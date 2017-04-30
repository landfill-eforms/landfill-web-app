import { MobileSyncSelectorComponent } from './mobile-sync-selector/mobile-sync-selector.component';
import { MobileUploadComponent } from './mobile-upload/mobile-upload.component';
import { DirectivesModule } from './../directives/directives.module';
import { NgModule } from '@angular/core';
import { CommonModule } from './../common/common.module';

@NgModule({
	imports: [
		CommonModule,
        DirectivesModule
    ],
	providers: [],
	declarations: [
		MobileSyncSelectorComponent,
        MobileUploadComponent
    ]
})

export class MobileModule {

}