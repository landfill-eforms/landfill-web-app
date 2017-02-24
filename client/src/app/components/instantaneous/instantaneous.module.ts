import { IMENumberComponent } from './ime-number/ime-number.component';
import { IMENumberListComponent } from './ime-number-list/ime-number-list.component';
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
		IMENumberListComponent,
		IMENumberComponent
	]
})

export class InstantaneousModule {

}