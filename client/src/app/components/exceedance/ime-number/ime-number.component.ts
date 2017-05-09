import { ExceedanceStatus } from './../../../model/server/persistence/enums/exceedance/exceedance-status.enum';
import { MonitoringPoint } from './../../../model/server/persistence/enums/location/monitoring-point.enum';
import { Site } from './../../../model/server/persistence/enums/location/site.enum';
import { EnumUtils } from './../../../utils/enum.utils';
import { ImeRepairData } from './../../../model/server/persistence/entity/surfaceemission/instantaneous/ime-repair-data.class';
import { TitleService } from './../../../services/app/title.service';
import { StringUtils } from './../../../utils/string.utils';
import { DateTimeUtils } from './../../../utils/date-time.utils';
import { ImeRecheckDialogComponent } from './../../directives/dialogs/ime-recheck-dialog/ime-recheck-dialog.component';
import { User } from './../../../model/server/persistence/entity/user/user.class';
import { UserService } from './../../../services/user/user.service';
import { ImeRepairDialogComponent } from './../../directives/dialogs/ime-repair-dialog/ime-repair-dialog.component';
import { MdDialogRef, MdDialog, MdDialogConfig } from '@angular/material';
import { ImeData } from './../../../model/server/persistence/entity/surfaceemission/instantaneous/ime-data.class';
import { NavigationService } from './../../../services/app/navigation.service';
import { ImeNumber } from './../../../model/server/persistence/entity/surfaceemission/instantaneous/ime-number.class';
import { MdSnackBar } from '@angular/material';
import { ActivatedRoute } from '@angular/router';
import { ImeNumberService } from './../../../services/instantaneous/ime-number.service';
import { Component, OnInit } from '@angular/core';


@Component({
	selector: 'app-ime-number',
	templateUrl: './ime-number.component.html',
	styleUrls: ['./ime-number.component.scss']

})
export class ImeNumberComponent implements OnInit {

	DateTimeUtils = DateTimeUtils;
	StringUtils = StringUtils;

	isDataLoaded:boolean;
	imeNumber:string;
	imeNumberData:ImeNumber;

	isUsersLoaded:boolean;
	users:User[] = [];

	loadingMessage:string;

	constructor(
		private imeNumberService:ImeNumberService,
		private dialog:MdDialog,
		private userService:UserService,
		private activatedRoute:ActivatedRoute,
		private snackBar:MdSnackBar,
		private titleService:TitleService,
		private navigationService:NavigationService) {
			navigationService.getNavbarComponent().expanded = false;
			navigationService.getSideinfoComponent().disable();
	}

	ngOnInit() {

		// TODO Display error message if IME number in the URL is invalid.
		this.imeNumber = this.activatedRoute.params['_value']['imeNumber'];
		this.titleService.setTitle(this.imeNumber);
		this.navigationService.getNavbarComponent().title = this.imeNumber;

		// Load IME number data.
		this.loadImeNumber();

		// Load list of inspectors.
		this.userService.getAll((data) => {
			this.users = data;
			this.isUsersLoaded = true;
		});

	}

	private loadImeNumber() {
		this.imeNumberService.getByImeNumber(this.imeNumber,
			(data) => {
				console.log(data);
				this.imeNumberData = data;

				// Sort data entries upon loading.
				this.imeNumberData.imeData.sort((a, b) => {
					return a.dateTime - b.dateTime;
				});
				for (let imeData of this.imeNumberData.imeData) {
					imeData.imeRepairData.sort((a, b) => {
						return a.dateTime - b.dateTime;
					});
				}

				// Convert strings to enums.
				this.imeNumberData.site = EnumUtils.convertToEnum(Site, this.imeNumberData.site);
				for (let i = 0; i < this.imeNumberData.monitoringPoints.length; i++) {
					this.imeNumberData.monitoringPoints[i] = EnumUtils.convertToEnum(MonitoringPoint, this.imeNumberData.monitoringPoints[i]);
				}
				this.imeNumberData.status = EnumUtils.convertToEnum(ExceedanceStatus, this.imeNumberData.status);

				this.isDataLoaded = true;
			}
		);
	}

	openRepairDialog(dataIdx?:number, repairIdx?:number) {

		let imeData:ImeData = dataIdx != null ? this.imeNumberData.imeData[dataIdx] : this.getLastImeData();
		let imeRepairData:ImeRepairData = imeData && repairIdx != null ? imeData.imeRepairData[repairIdx] : null;

		// Calculate min and max date/time allowed for the repair.
		let minDateTime:number = null;
		let maxDateTime:number = null;
		if (imeData) {
			minDateTime = imeData.dateTime;
		}
		if (dataIdx != null && dataIdx < this.imeNumberData.imeData.length - 1) {
			maxDateTime = this.imeNumberData.imeData[dataIdx + 1].dateTime;
		}

		let dialogConfig:MdDialogConfig = new MdDialogConfig();
		dialogConfig.width = '640px';
		dialogConfig.height = '360px';
		let dialogRef:MdDialogRef<ImeRepairDialogComponent> = this.dialog.open(ImeRepairDialogComponent, dialogConfig);
		dialogRef.componentInstance.minDateTime = minDateTime;
		dialogRef.componentInstance.maxDateTime = maxDateTime;
		dialogRef.componentInstance.originalData = imeRepairData;
		dialogRef.afterClosed().subscribe(result => {
			if (result) {
				// If a result was returned, then that means it's a new repair entry.
				result["deletable"] = true; // TEMPORARY
				imeData.imeRepairData.push(result);
				imeData.imeRepairData.sort((a, b) => {
					return a.dateTime - b.dateTime;
				});
			}
		});
	}

	openRecheckDialog(dataIdx?:number) {
		
		let imeData = this.imeNumberData.imeData[dataIdx];
		let prevImeData:ImeData = dataIdx != null && dataIdx > 0 ? this.imeNumberData.imeData[dataIdx - 1] : this.getLastImeData();
		
		// Calculate min and max date/time allowed for the recheck.
		let minDateTime:number = null;
		let maxDateTime:number = null;
		if (prevImeData) {
			minDateTime = prevImeData.dateTime;
			let prevImeRepairData:ImeRepairData = this.getLastImeRepairData(prevImeData);
			if (prevImeRepairData) {
				minDateTime = prevImeRepairData.dateTime;
			}
		}
		if (dataIdx != null) {
			let firstImeRepairData:ImeRepairData = imeData.imeRepairData[0];
			if (firstImeRepairData) {
				maxDateTime = firstImeRepairData.dateTime;
			}
			else {
				let nextImeData:ImeData = this.imeNumberData.imeData[dataIdx + 1];
				if (nextImeData) {
					maxDateTime = nextImeData.dateTime;
				}
			}
		}
		
		let dialogConfig:MdDialogConfig = new MdDialogConfig();
		dialogConfig.width = '640px';
		let dialogRef:MdDialogRef<ImeRecheckDialogComponent> = this.dialog.open(ImeRecheckDialogComponent, dialogConfig);
		dialogRef.componentInstance.users = this.users;
		dialogRef.componentInstance.minDateTime = minDateTime;
		dialogRef.componentInstance.maxDateTime = maxDateTime;
		dialogRef.componentInstance.originalData = imeData;
		dialogRef.afterClosed().subscribe(result => {
			if (result) {
				// If a result was returned, then that means it's a new recheck entry.
				result["deletable"] = true; // TEMPORARY
				this.imeNumberData.imeData.push(result);
			}
		});
	}

	deleteRepair(dataIdx:number, repairIdx:number) {
		this.imeNumberData.imeData[dataIdx].imeRepairData.splice(repairIdx, 1);
	}

	deleteRecheck(dataIdx:number) {
		this.imeNumberData.imeData.splice(dataIdx, 1);
	}

	save() {
		this.imeNumberService.update(this.imeNumberData, 
			(data) => {
				console.log(data);
				this.snackBar.open("IME number has been updated.", "OK", {duration: 2000});
				this.isDataLoaded = false;
				this.loadingMessage = "Reloading..."
				this.loadImeNumber();
			}
		);
	}

	listGrids(imeNumber:ImeNumber):string {
		return this.imeNumberService.listGrids(imeNumber);
	}

	private getLastImeData():ImeData {
		if (!this.imeNumberData || !this.imeNumberData.imeData || !this.imeNumberData.imeData.length) {
			return null;
		}
		return this.imeNumberData.imeData[this.imeNumberData.imeData.length - 1];
	}

	private getLastImeRepairData(imeData:ImeData):ImeRepairData {
		if (!imeData || !imeData.imeRepairData || !imeData.imeRepairData.length) {
			return null;
		}
		return imeData.imeRepairData[imeData.imeRepairData.length - 1];
	}

}