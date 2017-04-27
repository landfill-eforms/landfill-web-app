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
		ExceedanceSelectorComponent
	]
})

export class ExceedanceModule {

}