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
		ImeNumberComponent
	]
})

export class ExceedanceModule {

}