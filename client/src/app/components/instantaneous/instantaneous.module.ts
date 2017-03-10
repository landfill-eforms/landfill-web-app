import { ImeNumberComponent } from './ime-number/ime-number.component';
import { ImeNumberListComponent } from './ime-number-list/ime-number-list.component';
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
		ImeNumberListComponent,
		ImeNumberComponent
	]
})

export class InstantaneousModule {

}