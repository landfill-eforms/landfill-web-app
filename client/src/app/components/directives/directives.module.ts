import { UserListSideinfoComponent } from './sideinfo-directives/user-list-sideinfo/user-list-sideinfo.component';
import { PaginationComponent } from './pagination/pagination.component';
import { ImeRepairDialogComponent } from './dialogs/ime-repair-dialog/ime-repair-dialog.component';
import { ImeRecheckDialogComponent } from './dialogs/ime-recheck-dialog/ime-recheck-dialog.component';
import { ImeDataViewComponent } from './data-view/ime-data-view/ime-data-view.component';
import { InstantaneousDataViewComponent } from './data-view/instantaneous-data-view/instantaneous-data-view.component';
import { ImeListViewComponent } from './data-view/ime-list-view/ime-list-view.component';
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
		ImeListViewComponent,
		ImeDataViewComponent,
		ImeRecheckDialogComponent,
		ImeRepairDialogComponent,
		PaginationComponent,
		UserListSideinfoComponent
	],
	exports: [
		FileUploadComponent,
		InstantaneousDataViewComponent,
		ImeListViewComponent,
		ImeDataViewComponent,
		ImeRecheckDialogComponent,
		ImeRepairDialogComponent,
		PaginationComponent,
		UserListSideinfoComponent
	]
})

export class DirectivesModule {
	
}