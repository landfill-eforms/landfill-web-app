import { InstrumentTypeDialogComponent } from './dialog/instrument-type-dialog/instrument-type-dialog.component';
import { InstrumentDialogComponent } from './dialog/instrument-dialog/instrument-dialog.component';
import { InstrumentListSideinfoComponent } from './instrument-list-sideinfo/instrument-list-sideinfo.component';
import { InstrumentTypeListSideinfoComponent } from './instrument-type-list-sideinfo/instrument-type-list-sideinfo.component';
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
		InstrumentDialogComponent,
		InstrumentTypeDialogComponent,
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