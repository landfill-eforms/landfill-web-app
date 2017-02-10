import { IMENumbersComponent } from './ime-numbers/ime-numbers.component';
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
		IMENumbersComponent,
		
	]
})

export class InstantaneousModule {

}