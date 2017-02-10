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
		IMEListComponent
	],
	exports: [
		FileUploadComponent,
		IMEListComponent
	]
})

export class DirectivesModule {
	
}