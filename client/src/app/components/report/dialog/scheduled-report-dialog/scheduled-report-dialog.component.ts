import { ReportPeriod } from './../../../../model/server/persistence/enums/report/report-period.enum';
import { Site } from './../../../../model/server/persistence/enums/location/site.enum';
import { ExceedanceType } from './../../../../model/server/persistence/enums/exceedance/exceedance-type.enum';
import { ReportType } from './../../../../model/server/persistence/enums/report/report-type.enum';
import { ScheduledReportQuery } from './../../../../model/server/persistence/entity/report/scheduled-report-query.class';
import { EnumUtils } from './../../../../utils/enum.utils';
import { EmailRecipientType } from './../../../../model/server/persistence/enums/email/email-recipient-type.enum';
import { UserGroup } from './../../../../model/server/persistence/entity/user/user-group.class';
import { EmailRecipient } from './../../../../model/server/persistence/entity/email/email-recipient.class';
import { ScheduledEmailService } from './../../../../services/scheduled/scheduled-email.service';
import { MdSnackBar } from '@angular/material';
import { MdDialogRef } from '@angular/material';
import { SchedulePeriodBoundary } from './../../../../model/server/persistence/enums/scheduled/schedule-period-boundary.enum';
import { ScheduleRecurrence } from './../../../../model/server/persistence/enums/scheduled/schedule-recurrence.enum';
import { Schedule } from './../../../../model/server/persistence/entity/scheduled/schedule.class';
import { ScheduledReport } from './../../../../model/server/persistence/entity/scheduled/scheduled-report.class';
import { Component, OnInit } from '@angular/core';

@Component({
	selector: 'app-scheduled-report-dialog',
	templateUrl: './scheduled-report-dialog.component.html'
})
export class ScheduledReportDialogComponent implements OnInit {

	readonly scheduleRecurrenceChoices:ScheduleRecurrence[] = ScheduleRecurrence.values();
	// readonly periodBoundaryChoices:SchedulePeriodBoundary[] = SchedulePeriodBoundary.values();
	readonly reportTypeChoices:ReportType[] = [
		ReportType.INSTANTANEOUS,
		ReportType.INTEGRATED,
		ReportType.EXCEEDANCE,
		ReportType.WARMSPOT
	];
	readonly reportPeriodChoices:ReportPeriod[] = ReportPeriod.values();
	readonly availableSites:Site[] = Site.values().filter(site => site.active);

	isNew:boolean;
	scheduledReport:ScheduledReport;
	userGroups:UserGroup[] = [];
	userGroupsWrapped:{userGroup:UserGroup, selected:boolean}[] = [];

	newRecipient:EmailRecipient = new EmailRecipient();
	

	constructor(
		public dialogRef:MdDialogRef<ScheduledReportDialogComponent>,
		private snackBar:MdSnackBar,
		private scheduledEmailService:ScheduledEmailService
	) {}

	ngOnInit() {
		if (!this.scheduledReport) {
			this.scheduledReport = new ScheduledReport();
			this.scheduledReport.recipients = [];
			this.scheduledReport.userGroups = [];
			this.scheduledReport.reportQueries = [new ScheduledReportQuery()];
			this.scheduledReport.reportQueries[0].reportType = ReportType.INSTANTANEOUS;
			this.scheduledReport.reportQueries[0].reportPeriod = ReportPeriod.SINGLE;
			this.scheduledReport.reportQueries[0].site = Site.BISHOPS;
			this.scheduledReport.schedule = new Schedule();
			this.scheduledReport.schedule.active = true;
			this.scheduledReport.schedule.offset = new Date().getTime();
			this.scheduledReport.schedule.frequency = 1;
			this.scheduledReport.schedule.recurrence = ScheduleRecurrence.MONTHLY;
			
		}
		else {
			// Clone
			let clone:ScheduledReport = new ScheduledReport();
			for (let key of Object.keys(this.scheduledReport)) {
				if (key === "schedule") {
					clone.schedule = new Schedule();
					for (let k of Object.keys(this.scheduledReport.schedule)) {
						clone.schedule[k] = this.scheduledReport.schedule[k];
					}
				}
				else if (key === "recipients") {
					clone.recipients = [];
					for (let recipient of this.scheduledReport.recipients) {
						clone.recipients.push(recipient);
					}
				}
				else if (key === "userGroups") {
					clone.userGroups = [];
					for (let userGroup of this.scheduledReport.userGroups) {
						clone.userGroups.push(userGroup);
					}
				}
				else if (key === "reportQueries") {
					clone.reportQueries = [];
					for (let reportQuery of this.scheduledReport.reportQueries) {
						clone.reportQueries.push(reportQuery);
					}
				}
				else {
					clone[key] = this.scheduledReport[key];
				}
			}
			this.scheduledReport = clone;
		}
		for (let userGroup of this.userGroups) {
			this.userGroupsWrapped.push({
				userGroup: userGroup,
				selected: this.scheduledReport.userGroups.map(g => g.id).indexOf(userGroup.id) > -1
			});
		}
	}

	confirm() {
		this.scheduledReport.userGroups = [];
		for (let userGroupWrapped of this.userGroupsWrapped) {
			if (userGroupWrapped.selected) {
				this.scheduledReport.userGroups.push(userGroupWrapped.userGroup);
			}
		}
		this.scheduledReport.reportQueries[0].exceedanceTypes = [ExceedanceType.INSTANTANEOUS, ExceedanceType.INTEGRATED]; // TODO Implement this properly.
		this.scheduledReport.schedule.periodBoundary = SchedulePeriodBoundary.START;
		// TODO Perform data verification before saving.
		if (this.isNew) {
			this.scheduledEmailService.createScheduledReport(this.scheduledReport,
				(data) => {
					console.log(data);
					this.dialogRef.close(data);
				},
				(err) => {
					this.snackBar.open(JSON.parse(err.text()).message, "OK", {duration: 5000});
				}
			);
		}
		else {
			this.scheduledEmailService.updateScheduledReport(this.scheduledReport,
				(data) => {
					console.log(data);
					this.dialogRef.close(data);
				},
				(err) => {
					this.snackBar.open(JSON.parse(err.text()).message, "OK", {duration: 5000});
				}
			);
		}
	}

	addRecipient() {
		this.newRecipient.recipientType = EmailRecipientType.TO;
		this.scheduledReport.recipients.push(this.newRecipient);
		this.newRecipient = new EmailRecipient();
	}

	removeRecipient(recipient:EmailRecipient) {
		this.scheduledReport.recipients.splice(this.scheduledReport.recipients.indexOf(recipient), 1);
	}

	updateOffset(date:number) {
		this.scheduledReport.schedule.offset = date;
	}

	updateStartDate(date:number) {
		this.scheduledReport.reportQueries[0].startDate = date;
	}

	updateEndDate(date:number) {
		this.scheduledReport.reportQueries[0].endDate = date;
	}

	cancel() {
		this.dialogRef.close();
	}

}
