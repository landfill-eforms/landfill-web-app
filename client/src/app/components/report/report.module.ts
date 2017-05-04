import { DirectivesModule } from './../directives/directives.module';
import { ReportSelectorComponent } from './report-selector/report-selector.component';
import { CommonModule } from './../common/common.module';
import { NgModule } from '@angular/core';

@NgModule({
	imports: [
		CommonModule,
		DirectivesModule
	],
	providers: [],
	declarations: [
		ReportSelectorComponent,
	]
})

export class ReportModule {

}