import { ScheduledReportListSideinfoComponent } from './scheduled-report-list-sideinfo/scheduled-report-list-sideinfo.component';
import { ScheduledReportDialogComponent } from './dialog/scheduled-report-dialog/scheduled-report-dialog.component';
import { ScheduledReportListComponent } from './scheduled-report-list/scheduled-report-list.component';
import { InstantaneousReportComponent } from './instantaneous-report/instantaneous-report.component';
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
		InstantaneousReportComponent,
		ScheduledReportListComponent,
		ScheduledReportDialogComponent,
		ScheduledReportListSideinfoComponent
	]
})

export class ReportModule {

}