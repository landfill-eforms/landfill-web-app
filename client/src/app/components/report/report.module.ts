import { ReportsComponent } from './reports/reports.component';
import { InstantaneousReportComponent } from './instantaneous-report/instantaneous-report.component';
import { CommonModule } from './../common/common.module';
import { NgModule } from '@angular/core';

@NgModule({
	imports: [
		CommonModule
	],
	providers: [],
	declarations: [
		ReportsComponent,
		InstantaneousReportComponent
	]
})

export class ReportModule {

}