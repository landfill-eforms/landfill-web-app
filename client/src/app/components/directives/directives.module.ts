import { IMERepairDialogComponent } from './dialog/ime-repair-dialog/ime-repair-dialog.component';
import { IMERecheckDialogComponent } from './dialog/ime-recheck-dialog/ime-recheck-dialog.component';
import { IMEDataViewComponent } from './data-view/ime-data-view/ime-data-view.component';
import { InstantaneousDataViewComponent } from './data-view/instantaneous-data-view/instantaneous-data-view.component';
import { IMEListViewComponent } from './data-view/ime-list-view/ime-list-view.component';
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
		IMEListViewComponent,
		IMEDataViewComponent,
		IMERecheckDialogComponent,
		IMERepairDialogComponent
	],
	exports: [
		FileUploadComponent,
		InstantaneousDataViewComponent,
		IMEListViewComponent,
		IMEDataViewComponent,
		IMERecheckDialogComponent,
		IMERepairDialogComponent
	]
})

export class DirectivesModule {
	
}