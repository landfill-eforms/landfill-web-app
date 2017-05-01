import { PageLoadingIndicatorComponent } from './page-loading-indicator/page-loading-indicator.component';
import { DatePickerInputComponent } from './pickers/date-picker-input/date-picker-input.component';
import { DatePickerComponent } from './pickers/date-picker/date-picker.component';
import { OkDialogComponent } from './dialogs/ok-dialog/ok-dialog.component';
import { YesNoDialogComponent } from './dialogs/yes-no-dialog/yes-no-dialog.component';
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
		OkDialogComponent,
		YesNoDialogComponent,
		FileUploadComponent,
		InstantaneousDataViewComponent,
		ImeListViewComponent,
		ImeDataViewComponent,
		ImeRecheckDialogComponent,
		ImeRepairDialogComponent,
		PaginationComponent,
		PageLoadingIndicatorComponent,
		DatePickerComponent,
		DatePickerInputComponent
	],
	exports: [
		OkDialogComponent,
		YesNoDialogComponent,
		FileUploadComponent,
		InstantaneousDataViewComponent,
		ImeListViewComponent,
		ImeDataViewComponent,
		ImeRecheckDialogComponent,
		ImeRepairDialogComponent,
		PaginationComponent,
		PageLoadingIndicatorComponent,
		DatePickerComponent,
		DatePickerInputComponent
	]
})

export class DirectivesModule {
	
}