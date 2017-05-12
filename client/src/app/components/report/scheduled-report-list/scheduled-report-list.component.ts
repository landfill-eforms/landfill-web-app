import { UserGroup } from './../../../model/server/persistence/entity/user/user-group.class';
import { UserGroupService } from './../../../services/user/user-group.service';
import { ReportType } from './../../../model/server/persistence/enums/report/report-type.enum';
import { ReportPeriod } from './../../../model/server/persistence/enums/report/report-period.enum';
import { ScheduleRecurrence } from './../../../model/server/persistence/enums/scheduled/schedule-recurrence.enum';
import { SchedulePeriodBoundary } from './../../../model/server/persistence/enums/scheduled/schedule-period-boundary.enum';
import { EnumUtils } from './../../../utils/enum.utils';
import { ScheduledReportListSideinfoComponent } from './../scheduled-report-list-sideinfo/scheduled-report-list-sideinfo.component';
import { ScheduledReportDialogComponent } from './../dialog/scheduled-report-dialog/scheduled-report-dialog.component';
import { StringUtils } from './../../../utils/string.utils';
import { DateTimeUtils } from './../../../utils/date-time.utils';
import { UserPermission } from './../../../model/server/persistence/enums/user/user-permission.enum';
import { ScheduledEmailService } from './../../../services/scheduled/scheduled-email.service';
import { AuthService } from './../../../services/auth/auth.service';
import { Site } from './../../../model/server/persistence/enums/location/site.enum';
import { ScheduledReport } from './../../../model/server/persistence/entity/scheduled/scheduled-report.class';
import { PaginationComponent } from './../../directives/pagination/pagination.component';
import { InputUtils } from './../../../utils/input.utils';
import { MdDialogConfig } from '@angular/material';
import { MdDialogRef } from '@angular/material';
import { NavigationService } from './../../../services/app/navigation.service';
import { MdSnackBar } from '@angular/material';
import { MdDialog } from '@angular/material';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { AbstractDataTableComponent } from './../../../model/client/abstract-components/abstract-data-table.component';
import { Component, OnInit, ViewChild, OnDestroy } from '@angular/core';

@Component({
	selector: 'app-scheduled-report-list',
	templateUrl: './scheduled-report-list.component.html'
})
export class ScheduledReportListComponent extends AbstractDataTableComponent<ScheduledReport> implements OnInit, OnDestroy {

	@ViewChild('pagination') pagination:PaginationComponent;

	fabActionSubscriber:Subscription;

	isUserGroupsLoaded:boolean;
	loadingMessage:string;
	userGroups:UserGroup[];

	sortProperties:any = {};

	filters:{text:string, status:number} = {
		text: "",
		status: -1
	};

	statusFilterChoices:any[] = [
		{
			ordinal: -1,
			name: "Any"
		},
		{
			ordinal: 0,
			name: "Active"
		},
		{
			ordinal: 1,
			name: "Inactive"
		},
	];

	showSideInfo:boolean = false;
	selectedScheduledReport:ScheduledReport;

	constructor(
		private authService:AuthService,
		private router:Router,
		private activatedRoute:ActivatedRoute,
		private scheduledEmailService:ScheduledEmailService,
		private userGroupService:UserGroupService,
		private dialog:MdDialog,
		private snackBar:MdSnackBar,
		private navigationService:NavigationService) {
			super();
			navigationService.getNavbarComponent().expanded = true;
			navigationService.getSideinfoComponent().setDirective(ScheduledReportListSideinfoComponent, {instrumentType: null});
			navigationService.getSideinfoComponent().enable();
	}

	ngOnInit() {
		this.canEdit = this.authService.canAccess([UserPermission.SCHEDULE_EMAIL_REPORTS]);
		if(this.canEdit) {
			this.navigationService.getNavbarComponent().setFabInfo({
				icon: "add",
				tooltip: "Schedule New Report"
			});
			this.fabActionSubscriber = this.navigationService
				.getNavbarComponent()
				.getFabActionSource()
				.asObservable()
				.subscribe((event) => {
					if (event instanceof MouseEvent) {
						this.openScheduledReportDialog(null);
					}
				});
		}
		this.loadingMessage = "Loading Scheduled Reports...";
		this.loadScheduledReports();
		this.loadUserGroups();
	}

	ngOnDestroy() {
		this.navigationService.getSideinfoComponent().disable();
		if (this.fabActionSubscriber) {
			this.navigationService.getNavbarComponent().resetFabInfo();
			this.navigationService.getNavbarComponent().resetFabActionSource();
			this.fabActionSubscriber.unsubscribe();
		}
	}
	
	private loadScheduledReports() {
		this.scheduledEmailService.getAllScheduledReports((data) => {
			console.log(data);
			this.data = data;
			for (let scheduledReport of this.data) {
				scheduledReport.schedule.periodBoundary = EnumUtils.convertToEnum(SchedulePeriodBoundary, scheduledReport.schedule.periodBoundary);
				scheduledReport.schedule.recurrence = EnumUtils.convertToEnum(ScheduleRecurrence, scheduledReport.schedule.recurrence);
				for (let reportQuery of scheduledReport.reportQueries) {
					reportQuery.reportPeriod = EnumUtils.convertToEnum(ReportPeriod, reportQuery.reportPeriod);
					reportQuery.reportType = EnumUtils.convertToEnum(ReportType, reportQuery.reportType);
					reportQuery.site = EnumUtils.convertToEnum(Site, reportQuery.site);
				}
			}
			this.applyFilters();
			this.navigationService.getSideinfoComponent().open();
			this.showSideInfo = true;
			this.isDataLoaded = true;
		});
	}

	private loadUserGroups() {
		this.userGroupService.getAll((data) => {
			console.log(data);
			this.userGroups = data;
			this.isUserGroupsLoaded = true;
		});
	}

	openScheduledReportDialog(scheduledReport:ScheduledReport) {
		if (!this.isDataLoaded) {
			return;
		}
		let isNew:boolean = !scheduledReport;
		let dialogConfig:MdDialogConfig = new MdDialogConfig();
		dialogConfig.width = '960px';
		dialogConfig.height = '640px';
		let dialogRef:MdDialogRef<ScheduledReportDialogComponent> = this.dialog.open(ScheduledReportDialogComponent, dialogConfig);
		dialogRef.componentInstance.userGroups = this.userGroups;
		if (isNew) {
			dialogRef.componentInstance.isNew = true;
		}
		else {
			dialogRef.componentInstance.scheduledReport = scheduledReport;
		}
		dialogRef.afterClosed().subscribe(result => {
			if (result) {
				if (isNew) {
					this.snackBar.open("New equipment added.", "OK", {duration: 3000});
				}
				else {
					this.snackBar.open("Equipment updated.", "OK", {duration: 3000});
				}
				this.isDataLoaded = false;
				this.loadingMessage = "Reloading..."
				this.deselectScheduledReport();
				this.loadScheduledReports();
			}
		});
	}

	applyFilters() {

		// Validate the text search string.
		InputUtils.isAlphanumeric(this.filters.text, this.textFilterStatus, "Cannot have special characters in search.");

		// If the text search string is invalid, then return.
		if (!this.textFilterStatus.valid) {
			return;
		}

		this.filteredData = this.data.filter(o => {
			let textMatch:boolean = true;
			if (this.filters.text) {
				let search:RegExp = new RegExp(this.filters.text, 'i');
				textMatch = search.test(o.subject) || search.test(o.body);
			}
			let statusMatch:boolean = true;
			if (this.filters.status >= 0) {
				statusMatch = o.schedule.active && this.filters.status == 0 || !o.schedule.active && this.filters.status == 1;
			}
			return textMatch && statusMatch;
		});

		this.paginfo.totalRows = this.filteredData.length;
		if (this.pagination) {
			this.pagination.update();
		}
		this.applyPagination();
	}

	resetFilters() {
		this.filters.text = "";
		this.applyFilters();
	}

	toggleSideInfo() {
		if (this.showSideInfo) {
			this.navigationService.getSideinfoComponent().close();
			this.showSideInfo = false;
		}
		else {
			this.navigationService.getSideinfoComponent().open();
			this.showSideInfo = true;
		}
	}

	selectScheduledReport(scheduledReport:ScheduledReport) {
		if (!this.selectedScheduledReport) {
			this.navigationService.getSideinfoComponent().open();
			this.showSideInfo = true;
		}
		this.selectedScheduledReport = scheduledReport;
		this.navigationService.getSideinfoComponent().getDirective().setData(this.selectedScheduledReport);
	}

	deselectScheduledReport() {
		this.selectedScheduledReport = null;
		this.navigationService.getSideinfoComponent().getDirective().setData(null);
	}

	deleteScheduledReport(scheduledReport:ScheduledReport) {
		this.scheduledEmailService.deleteScheduledReport(scheduledReport, 
			(data) => {
				console.log("Deleted", data);
				this.isDataLoaded = false;
				this.loadingMessage = "Reloading..."
				this.deselectScheduledReport();
				this.loadScheduledReports();
			}, 
			(err) => {
				this.snackBar.open(JSON.parse(err.text()).message, "OK", {duration: 5000});
			}
		);
	}

	isNavDrawerOpen():boolean {
		return this.navigationService.isNavDrawerOpened();
	}
	
}