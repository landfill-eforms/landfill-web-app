import { SortUtils } from './../../../utils/sort.utils';
import { UserPermission } from './../../../model/server/persistence/enums/user/user-permission.enum';
import { AuthService } from './../../../services/auth/auth.service';
import { IseRepairDialogComponent } from './../dialog/ise-repair-dialog/ise-repair-dialog.component';
import { IseRecheckDialogComponent } from './../dialog/ise-recheck-dialog/ise-recheck-dialog.component';
import { YesNoDialogComponent } from './../../directives/dialogs/yes-no-dialog/yes-no-dialog.component';
import { ExceedanceStatus } from './../../../model/server/persistence/enums/exceedance/exceedance-status.enum';
import { MonitoringPoint } from './../../../model/server/persistence/enums/location/monitoring-point.enum';
import { Site } from './../../../model/server/persistence/enums/location/site.enum';
import { EnumUtils } from './../../../utils/enum.utils';
import { IseRepairData } from './../../../model/server/persistence/entity/surfaceemission/integrated/ise-repair-data.class';
import { TitleService } from './../../../services/app/title.service';
import { StringUtils } from './../../../utils/string.utils';
import { DateTimeUtils } from './../../../utils/date-time.utils';
import { User } from './../../../model/server/persistence/entity/user/user.class';
import { UserService } from './../../../services/user/user.service';
import { MdDialogRef, MdDialog, MdDialogConfig } from '@angular/material';
import { IseData } from './../../../model/server/persistence/entity/surfaceemission/integrated/ise-data.class';
import { NavigationService } from './../../../services/app/navigation.service';
import { IseNumber } from './../../../model/server/persistence/entity/surfaceemission/integrated/ise-number.class';
import { MdSnackBar } from '@angular/material';
import { ActivatedRoute } from '@angular/router';
import { IseNumberService } from './../../../services/integrated/ise-number.service';
import { Component, OnInit } from '@angular/core';


@Component({
	selector: 'app-ise-number',
	templateUrl: './ise-number.component.html',
	styleUrls: ['./ise-number.component.scss']

})
export class IseNumberComponent implements OnInit {

	DateTimeUtils = DateTimeUtils;
	StringUtils = StringUtils;

	isDataLoaded: boolean;
	iseNumber: string;
	iseNumberData: IseNumber;

	isUsersLoaded: boolean;
	users: User[] = [];

	isCleared: boolean;
	clearable: boolean;

	/** Whether or not the current user has permissions to edit the ISE data. */
	private _canEdit: boolean;

	constructor(
		private _authService: AuthService,
		private _iseNumberService: IseNumberService,
		private _dialog: MdDialog,
		private _userService: UserService,
		private _activatedRoute: ActivatedRoute,
		private _snackBar: MdSnackBar,
		private _titleService: TitleService,
		private _navigationService: NavigationService) {
			_navigationService.getNavbarComponent().expanded = false;
			_navigationService.getSideinfoComponent().disable();
	}
	
	get canEdit(): boolean {
		return this._canEdit;
	}

	ngOnInit() {

		// TODO Display error message if IME number in the URL is invalid.
		this.iseNumber = this._activatedRoute.params['_value']['iseNumber'];
		this._titleService.setTitle(this.iseNumber);
		this._navigationService.getNavbarComponent().title = this.iseNumber;

		// Load IME number data.
		this._iseNumberService.getByIseNumber(this.iseNumber,
			(data) => {
				console.log(data);
				this.iseNumberData = this.processIseNumber(data);
				this.isCleared = this.iseNumberData.status == ExceedanceStatus.CLEARED;
				this.checkIfClearable();
				this.isDataLoaded = true;
			}
		);

		// Load list of inspectors.
		this._userService.getAllInspectors((data) => {
			this.users = data;
			SortUtils.sort(this.users, ["lastname", "firstname"], false);						
			this.isUsersLoaded = true;
		});

		// Check if user has permission to edit the ISE.
		this._canEdit = this._authService.canAccess(UserPermission.EDIT_EXCEEDANCES);

	}

	private processIseNumber(iseNumber: IseNumber): IseNumber {

		// Sort data entries
		iseNumber.iseData.sort((a, b) => {
			return a.dateTime - b.dateTime;
		});
		for (let iseData of iseNumber.iseData) {
			iseData.iseRepairData.sort((a, b) => {
				return a.dateTime - b.dateTime;
			});
		}

		// Convert strings to enums
		iseNumber.site = EnumUtils.convertToEnum(Site, iseNumber.site);
		iseNumber.monitoringPoint = EnumUtils.convertToEnum(MonitoringPoint, iseNumber.monitoringPoint);
		iseNumber.status = EnumUtils.convertToEnum(ExceedanceStatus, iseNumber.status);

		return iseNumber;
	}

	openRepairDialog(dataIdx?: number, repairIdx?: number) {

		let iseData: IseData = dataIdx != null ? this.iseNumberData.iseData[dataIdx] : this.getLastIseData();
		let iseRepairData: IseRepairData = iseData && repairIdx != null ? iseData.iseRepairData[repairIdx] : null;

		// Calculate min and max date/time allowed for the repair.
		let minDateTime: number = null;
		let maxDateTime: number = null;
		if (iseData) {
			minDateTime = iseData.dateTime;
		}
		if (dataIdx != null && dataIdx < this.iseNumberData.iseData.length - 1) {
			maxDateTime = this.iseNumberData.iseData[dataIdx + 1].dateTime;
		}

		let dialogConfig: MdDialogConfig = new MdDialogConfig();
		dialogConfig.width = '640px';
		dialogConfig.height = '360px';
		let dialogRef: MdDialogRef<IseRepairDialogComponent> = this._dialog.open(IseRepairDialogComponent, dialogConfig);
		dialogRef.componentInstance.minDateTime = minDateTime;
		dialogRef.componentInstance.maxDateTime = maxDateTime;
		dialogRef.componentInstance.originalData = iseRepairData;
		dialogRef.afterClosed().subscribe(result => {
			if (result) {
				// If a result was returned, then that means it's a new repair entry.
				result["deletable"] = true; // TEMPORARY
				iseData.iseRepairData.push(result);
				iseData.iseRepairData.sort((a, b) => {
					return a.dateTime - b.dateTime;
				});
			}
			this.checkIfClearable();
		});
	}

	openRecheckDialog(dataIdx?: number) {
		
		let iseData = this.iseNumberData.iseData[dataIdx];
		let prevIseData: IseData = dataIdx != null && dataIdx > 0 ? this.iseNumberData.iseData[dataIdx - 1] : this.getLastIseData();
		
		// Calculate min and max date/time allowed for the recheck.
		let minDateTime: number = null;
		let maxDateTime: number = null;
		if (prevIseData) {
			minDateTime = prevIseData.dateTime;
			let prevIseRepairData: IseRepairData = this.getLastIseRepairData(prevIseData);
			if (prevIseRepairData) {
				minDateTime = prevIseRepairData.dateTime;
			}
		}
		if (dataIdx != null) {
			let firstIseRepairData: IseRepairData = iseData.iseRepairData[0];
			if (firstIseRepairData) {
				maxDateTime = firstIseRepairData.dateTime;
			}
			else {
				let nextIseData: IseData = this.iseNumberData.iseData[dataIdx + 1];
				if (nextIseData) {
					maxDateTime = nextIseData.dateTime;
				}
			}
		}
		
		let dialogConfig: MdDialogConfig = new MdDialogConfig();
		dialogConfig.width = '640px';
		let dialogRef: MdDialogRef<IseRecheckDialogComponent> = this._dialog.open(IseRecheckDialogComponent, dialogConfig);
		dialogRef.componentInstance.users = this.users;
		dialogRef.componentInstance.minDateTime = minDateTime;
		dialogRef.componentInstance.maxDateTime = maxDateTime;
		dialogRef.componentInstance.originalData = iseData;
		dialogRef.afterClosed().subscribe(result => {
			if (result) {
				// If a result was returned, then that means it's a new recheck entry.
				result["deletable"] = true; // TEMPORARY
				this.iseNumberData.iseData.push(result);
			}
			this.checkIfClearable();
		});
	}

	deleteRepair(dataIdx: number, repairIdx: number) {
		this.iseNumberData.iseData[dataIdx].iseRepairData.splice(repairIdx, 1);
	}

	deleteRecheck(dataIdx: number) {
		this.iseNumberData.iseData.splice(dataIdx, 1);
	}

	save() {
		let dialogConfig: MdDialogConfig = new MdDialogConfig();
		dialogConfig.width = '480px';
		let dialogRef: MdDialogRef<YesNoDialogComponent> = this._dialog.open(YesNoDialogComponent, dialogConfig);
		dialogRef.componentInstance.title = "Confirm";
		dialogRef.componentInstance.prompt = ["Recheck/repair entries that are saved to the database cannot be deleted. Would you like to continue saving?"];
		dialogRef.componentInstance.confirmLabel = "SAVE";
		dialogRef.componentInstance.cancelLabel = "CANCEL";
		dialogRef.afterClosed().subscribe((res) => {
			if (res) {
				this._iseNumberService.update(this.iseNumberData, 
					(data) => {
						console.log(data);
						this._snackBar.open("IME number has been updated.", "OK", {duration: 3000});
						this.iseNumberData = this.processIseNumber(data);
						this.checkIfClearable();
					},
					(err) => {
						this._snackBar.open(JSON.parse(err.text()).message, "OK", {duration: 5000});
					}
				);
			}
		});
	}

	clear() {
		if (this.getLastIseData().methaneLevel >= 2500) {
			return;
		}
		let dialogConfig: MdDialogConfig = new MdDialogConfig();
		dialogConfig.width = '480px';
		let dialogRef: MdDialogRef<YesNoDialogComponent> = this._dialog.open(YesNoDialogComponent, dialogConfig);
		dialogRef.componentInstance.title = "Confirm";
		dialogRef.componentInstance.prompt = ["Cleared IME numbers cannot be edited. Please make sure all the entries and grids are correct before clearing. Would you like to clear this IME?"];
		dialogRef.componentInstance.confirmLabel = "YES";
		dialogRef.componentInstance.cancelLabel = "NO";
		dialogRef.afterClosed().subscribe((res) => {
			if (res) {
				this._iseNumberService.clear(this.iseNumberData, 
					(data) => {
						console.log(data);
						this._snackBar.open("IME number has been updated.", "OK", {duration: 3000});
						this.iseNumberData = this.processIseNumber(data);
						this.isCleared = this.iseNumberData.status == ExceedanceStatus.CLEARED;
					},
					(err) => {
						this._snackBar.open(JSON.parse(err.text()).message, "OK", {duration: 5000});
					}
				);
			}
		});
	}

	private getLastIseData(): IseData {
		if (!this.iseNumberData || !this.iseNumberData.iseData) {
			return null;
		}
		return this.iseNumberData.iseData[this.iseNumberData.iseData.length - 1];
	}

	private getLastIseRepairData(iseData: IseData): IseRepairData {
		if (!iseData || !iseData.iseRepairData) {
			return null;
		}
		return iseData.iseRepairData[iseData.iseRepairData.length - 1];
	}

	private checkIfClearable() {
		if (!this.isCleared) {
			this.clearable = this.getLastIseData().methaneLevel < 2500;
		}
	}

}