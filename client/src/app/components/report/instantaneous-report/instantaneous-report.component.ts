import { ReportPeriod } from './../../../model/server/persistence/enums/report/report-period.enum';
import { FileDownloadService } from './../../../services/file/file-download.service';
import { ReportService } from './../../../services/report/report.service';
import { ReportType } from './../../../model/server/persistence/enums/report/report-type.enum';
import { MdSnackBar } from '@angular/material';
import { IndividualReportQuery } from './../../../model/server/persistence/entity/report/individual-report-query.class';
import { InstantaneousReport } from './../../../model/server/service/report/model/instantaneous-report.class';
import { Site } from './../../../model/server/persistence/enums/location/site.enum';
import { Component } from '@angular/core';

@Component({
	selector: 'app-instantaneous-report',
	templateUrl: './instantaneous-report.component.html'
})
export class InstantaneousReportComponent {

	selectedSite:Site = Site.BISHOPS;
	availableSites:Site[] = Site.values().filter(site => site.active);

	selectedReportPeriod:ReportPeriod = ReportPeriod.SINGLE;
	reportPeriodChoices:ReportPeriod[] = ReportPeriod.values();
	periodOffset:number = 0;

	report:InstantaneousReport;

	dateRange:any = {
		start: null,
		end: null
	}

	isDataLoaded:boolean = false;

	constructor(
		private snackBar:MdSnackBar,
		private reportService:ReportService,
		private fileDownloadService:FileDownloadService
		) {

	}

	previewReport() {
		console.log(this.selectedSite);
		if (!this.selectedSite) {
			this.snackBar.open("Please select a location.", "OK", {duration: 4000});
			return;
		}
		this.reportService.previewReport(this.generateQuery(), 
			(data) => {
				console.log(data);
				this.report = data;
				this.isDataLoaded = true;
			},
			(err) => {
				this.snackBar.open(JSON.parse(err.text()).message, "OK", {duration: 5000});
			}
		);
	}

	downloadReport() {
		this.fileDownloadService.downloadReportPdf(this.generateQuery());
	}

	dateChanged(key, event) {
		this.dateRange[key] = event;
	}

	private generateQuery():IndividualReportQuery {
		let reportQuery:IndividualReportQuery = new IndividualReportQuery;
		reportQuery.reportType = ReportType.INSTANTANEOUS;
		reportQuery.site = this.selectedSite;
		reportQuery.reportPeriod = this.selectedReportPeriod;
		reportQuery.periodOffset = this.periodOffset;
		reportQuery.periodToDate = true; // Always true;
		reportQuery.startDate = this.dateRange.start;
		reportQuery.endDate = this.dateRange.end;
		return reportQuery;
	}

}