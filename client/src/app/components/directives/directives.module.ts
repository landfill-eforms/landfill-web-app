import { InstantaneousDataViewComponent } from './data-view/instantaneous-data-view/instantaneous-data-view.component';
import { IMEListComponent } from './data-view/ime-list-view/ime-list-view.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '../common/common.module'
import { FileUploadComponent } from './file-upload/file-upload.component';

@NgModule({
	imports: [
		CommonModule
	],
	providers: [],
	declarations: [
		FileUploadComponent,
		InstantaneousDataViewComponent,
		IMEListComponent
	],
	exports: [
		FileUploadComponent,
		InstantaneousDataViewComponent,
		IMEListComponent
	]
})

export class DirectivesModule {
	
}