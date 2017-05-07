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
		IseNumberListComponent,
		IseNumberListSideinfoComponent,
	]
})

export class ExceedanceModule {

}