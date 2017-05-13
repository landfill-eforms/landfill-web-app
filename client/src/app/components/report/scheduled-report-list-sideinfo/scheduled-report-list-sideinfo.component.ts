import { ScheduledReport } from './../../../model/server/persistence/entity/scheduled/scheduled-report.class';
import { StringUtils } from './../../../utils/string.utils';
import { DateTimeUtils } from './../../../utils/date-time.utils';
import { NavigationService } from './../../../services/app/navigation.service';
import { AbstractSideinfoComponent } from './../../../model/client/abstract-components/abstract-sideinfo.component';
import { Component } from '@angular/core';

@Component({
	selector: 'app-scheduled-report-list-sideinfo',
	templateUrl: './scheduled-report-list-sideinfo.component.html'
})
export class ScheduledReportListSideinfoComponent extends AbstractSideinfoComponent<ScheduledReport> {

	scheduledReport:ScheduledReport;
	tests:string[] = []

	constructor() {
		super("Scheduled Reports");
	}

	getData():ScheduledReport {
		return this.scheduledReport;
	}

	setData(data:ScheduledReport) {
		this.scheduledReport = data;
	}
	
}