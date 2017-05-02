import { InstrumentSelectorComponent } from './instrument-selector/instrument-selector.component';
import { InstrumentTypeDialogComponent } from './dialog/instrument-type-dialog/instrument-type-dialog.component';
import { InstrumentDialogComponent } from './dialog/instrument-dialog/instrument-dialog.component';
import { InstrumentListSideinfoComponent } from './instrument-list-sideinfo/instrument-list-sideinfo.component';
import { InstrumentTypeListSideinfoComponent } from './instrument-type-list-sideinfo/instrument-type-list-sideinfo.component';
import { InstrumentListComponent } from './instrument-list/instrument-list.component';
import { InstrumentTypeListComponent } from './instrument-type-list/instrument-type-list.component';
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
		InstrumentSelectorComponent,
		InstrumentDialogComponent,
		InstrumentTypeDialogComponent,
		InstrumentListComponent,
		InstrumentTypeListComponent,
		InstrumentListSideinfoComponent,
		InstrumentTypeListSideinfoComponent
	]
})

export class InstrumentModule {

}