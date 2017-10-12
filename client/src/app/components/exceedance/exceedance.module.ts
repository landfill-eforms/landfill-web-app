import { ImeGridsDialogComponent } from './dialog/ime-grids-dialog/ime-grids-dialog.component';
import { IseNumberComponent } from './ise-number/ise-number.component';
import { ImeRecheckDialogComponent } from './dialog/ime-recheck-dialog/ime-recheck-dialog.component';
import { IseRepairDialogComponent } from './dialog/ise-repair-dialog/ise-repair-dialog.component';
import { IseRecheckDialogComponent } from './dialog/ise-recheck-dialog/ise-recheck-dialog.component';
import { ImeRepairDialogComponent } from './dialog/ime-repair-dialog/ime-repair-dialog.component';
import { IseNumberListSideinfoComponent } from './ise-number-list-sideinfo/ise-number-list-sideinfo.component';
import { IseNumberListComponent } from './ise-number-list/ise-number-list.component';
import { ImeNumberListSideinfoComponent } from './ime-number-list-sideinfo/ime-number-list-sideinfo.component';
import { ImeNumberComponent } from './ime-number/ime-number.component';
import { ImeNumberListComponent } from './ime-number-list/ime-number-list.component';
import { ExceedanceSelectorComponent } from './exceedance-selector/exceedance-selector.component';
import { DirectivesModule } from './../directives/directives.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '../common/common.module';

@NgModule({
	imports: [
		CommonModule,
		DirectivesModule
	],
	providers: [],
	declarations: [
		ExceedanceSelectorComponent,
		ImeNumberListComponent,
		ImeNumberListSideinfoComponent,
		ImeNumberComponent,
		ImeGridsDialogComponent,
		ImeRecheckDialogComponent,
		ImeRepairDialogComponent,
		IseNumberListComponent,
		IseNumberListSideinfoComponent,
		IseNumberComponent,
		IseRecheckDialogComponent,
		IseRepairDialogComponent
	]
})

export class ExceedanceModule {

}