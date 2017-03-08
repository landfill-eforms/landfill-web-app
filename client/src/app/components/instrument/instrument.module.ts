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
		InstrumentListComponent,
		InstrumentComponent,
		InstrumentTypeListComponent,
		InstrumentTypeComponent
	]
})

export class InstrumentModule {

}