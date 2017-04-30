import { ImeReportComponent } from './ime-report/ime-report.component';
import { DirectivesModule } from './../directives/directives.module';
import { ReportSelectorComponent } from './report-selector/report-selector.component';
import { InstantaneousReportComponent } from './instantaneous-report/instantaneous-report.component';
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
		InstantaneousReportComponent,
		ImeReportComponent
	]
})

export class ReportModule {

}