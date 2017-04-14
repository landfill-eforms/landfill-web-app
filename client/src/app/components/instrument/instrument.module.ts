import { InstrumentListSideinfoComponent } from './instrument-list-sideinfo/instrument-list-sideinfo.component';
import { InstrumentTypeListSideinfoComponent } from './instrument-type-list-sideinfo/instrument-type-list-sideinfo.component';
import { NewInstrumentTypeDialogComponent } from './dialog/new-instrument-type-dialog/new-instrument-type-dialog.component';
import { NewInstrumentDialogComponent } from './dialog/new-instrument-dialog/new-instrument-dialog.component';
import { InstrumentComponent } from './instrument/instrument.component';
import { InstrumentListComponent } from './instrument-list/instrument-list.component';
import { InstrumentTypeComponent } from './instrument-type/instrument-type.component';
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
		NewInstrumentDialogComponent,
		NewInstrumentTypeDialogComponent,
		InstrumentListComponent,
		InstrumentComponent,
		InstrumentTypeListComponent,
		InstrumentTypeComponent,
		InstrumentListSideinfoComponent,
		InstrumentTypeListSideinfoComponent
	]
})

export class InstrumentModule {

}