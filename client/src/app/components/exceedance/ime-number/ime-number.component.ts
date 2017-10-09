import { ImeGridsDialogComponent } from './../dialog/ime-grids-dialog/ime-grids-dialog.component';
import { AuthService } from './../../../services/auth/auth.service';
import { UserPermission } from './../../../model/server/persistence/enums/user/user-permission.enum';
import { ImeRecheckDialogComponent } from './../dialog/ime-recheck-dialog/ime-recheck-dialog.component';
import { ImeRepairDialogComponent } from './../dialog/ime-repair-dialog/ime-repair-dialog.component';
import { YesNoDialogComponent } from './../../directives/dialogs/yes-no-dialog/yes-no-dialog.component';
import { ExceedanceStatus } from './../../../model/server/persistence/enums/exceedance/exceedance-status.enum';
import { MonitoringPoint } from './../../../model/server/persistence/enums/location/monitoring-point.enum';
import { Site } from './../../../model/server/persistence/enums/location/site.enum';
import { EnumUtils } from './../../../utils/enum.utils';
import { ImeRepairData } from './../../../model/server/persistence/entity/surfaceemission/instantaneous/ime-repair-data.class';
import { TitleService } from './../../../services/app/title.service';
import { StringUtils } from './../../../utils/string.utils';
import { DateTimeUtils } from './../../../utils/date-time.utils';
import { User } from './../../../model/server/persistence/entity/user/user.class';
import { UserService } from './../../../services/user/user.service';
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

	isDataLoaded: boolean;
	imeNumber: string;
	imeNumberData: ImeNumber;

	isUsersLoaded: boolean;
	users: User[] = [];

	isCleared: boolean;
	clearable: boolean;

	/** Whether or not the current user has permissions to edit the IME. */
	private _canEdit: boolean;

	constructor(
		private _authService: AuthService,
		private _imeNumberService: ImeNumberService,
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
		this.imeNumber = this._activatedRoute.params['_value']['imeNumber'];
		this._titleService.setTitle(this.imeNumber);
		this._navigationService.getNavbarComponent().title = this.imeNumber;

		// Load IME number data.
		this._imeNumberService.getByImeNumber(this.imeNumber,
			(data) => {
				console.log(data);
				this.imeNumberData = this.processImeNumber(data);
				this.isCleared = this.imeNumberData.status == ExceedanceStatus.CLEARED;
				this.checkIfClearable();
				this.isDataLoaded = true;
			}
		);

		// Load list of inspectors.
		this._userService.getAllInspectors((data) => {
			this.users = data;
			this.isUsersLoaded = true;
		});

		// Check if user has permission to edit the IME.
		this._canEdit = this._authService.canAccess(UserPermission.EDIT_EXCEEDANCES);

	}

	private processImeNumber(imeNumber: ImeNumber): ImeNumber {

		// Sort data entries
		imeNumber.imeData.sort((a, b) => {
			return a.dateTime - b.dateTime;
		});
		for (let imeData of imeNumber.imeData) {
			imeData.imeRepairData.sort((a, b) => {
				return a.dateTime - b.dateTime;
			});
		}

		// Convert strings to enums
		imeNumber.site = EnumUtils.convertToEnum(Site, imeNumber.site);
		for (let i = 0; i < imeNumber.monitoringPoints.length; i++) {
			imeNumber.monitoringPoints[i] = EnumUtils.convertToEnum(MonitoringPoint, imeNumber.monitoringPoints[i]);
		}
		imeNumber.status = EnumUtils.convertToEnum(ExceedanceStatus, imeNumber.status);

		return imeNumber;
	}

	openRepairDialog(dataIdx?: number, repairIdx?: number) {

		let imeData: ImeData = dataIdx != null ? this.imeNumberData.imeData[dataIdx] : this.getLastImeData();
		let imeRepairData: ImeRepairData = imeData && repairIdx != null ? imeData.imeRepairData[repairIdx] : null;

		// Calculate min and max date/time allowed for the repair.
		let minDateTime: number = null;
		let maxDateTime: number = null;
		if (imeData) {
			minDateTime = imeData.dateTime;
		}
		if (dataIdx != null && dataIdx < this.imeNumberData.imeData.length - 1) {
			maxDateTime = this.imeNumberData.imeData[dataIdx + 1].dateTime;
		}

		let dialogConfig: MdDialogConfig = new MdDialogConfig();
		dialogConfig.width = '640px';
		dialogConfig.height = '360px';
		let dialogRef: MdDialogRef<ImeRepairDialogComponent> = this._dialog.open(ImeRepairDialogComponent, dialogConfig);
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
			this.checkIfClearable();
		});
	}

	openRecheckDialog(dataIdx?: number) {
		
		let imeData = this.imeNumberData.imeData[dataIdx];
		let prevImeData: ImeData = dataIdx != null && dataIdx > 0 ? this.imeNumberData.imeData[dataIdx - 1] : this.getLastImeData();
		
		// Calculate min and max date/time allowed for the recheck.
		let minDateTime: number = null;
		let maxDateTime: number = null;
		if (prevImeData) {
			minDateTime = prevImeData.dateTime;
			let prevImeRepairData: ImeRepairData = this.getLastImeRepairData(prevImeData);
			if (prevImeRepairData) {
				minDateTime = prevImeRepairData.dateTime;
			}
		}
		if (dataIdx != null) {
			let firstImeRepairData: ImeRepairData = imeData.imeRepairData[0];
			if (firstImeRepairData) {
				maxDateTime = firstImeRepairData.dateTime;
			}
			else {
				let nextImeData: ImeData = this.imeNumberData.imeData[dataIdx + 1];
				if (nextImeData) {
					maxDateTime = nextImeData.dateTime;
				}
			}
		}
		
		let dialogConfig: MdDialogConfig = new MdDialogConfig();
		dialogConfig.width = '640px';
		let dialogRef: MdDialogRef<ImeRecheckDialogComponent> = this._dialog.open(ImeRecheckDialogComponent, dialogConfig);
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
			this.checkIfClearable();
		});
	}

	deleteRepair(dataIdx: number, repairIdx: number) {
		this.imeNumberData.imeData[dataIdx].imeRepairData.splice(repairIdx, 1);
	}

	deleteRecheck(dataIdx: number) {
		this.imeNumberData.imeData.splice(dataIdx, 1);
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
				this._imeNumberService.update(this.imeNumberData, 
					(data) => {
						console.log(data);
						this._snackBar.open("IME number has been updated.", "OK", {duration: 3000});
						this.imeNumberData = this.processImeNumber(data);
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
		if (this.getLastImeData().methaneLevel >= 50000) {
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
				this._imeNumberService.clear(this.imeNumberData, 
					(data) => {
						console.log(data);
						this._snackBar.open("IME number has been updated.", "OK", {duration: 3000});
						this.imeNumberData = this.processImeNumber(data);
						this.isCleared = this.imeNumberData.status == ExceedanceStatus.CLEARED;
					},
					(err) => {
						this._snackBar.open(JSON.parse(err.text()).message, "OK", {duration: 5000});
					}
				);
			}
		});
	}

	openGridDialog() {
		let dialogConfig:MdDialogConfig = new MdDialogConfig();
		dialogConfig.width = '480px';
		let dialogRef:MdDialogRef<ImeGridsDialogComponent> = this._dialog.open(ImeGridsDialogComponent, dialogConfig);
		// TODO Pass data to dialog here...
		dialogRef.afterClosed().subscribe(result => {
			if (result) {
				// TODO Do something with data passed back by the dialog.
			}
		});
	}

	listGrids(imeNumber: ImeNumber): string {
		return this._imeNumberService.listGrids(imeNumber);
	}

	private getLastImeData(): ImeData {
		if (!this.imeNumberData || !this.imeNumberData.imeData) {
			return null;
		}
		return this.imeNumberData.imeData[this.imeNumberData.imeData.length - 1];
	}

	private getLastImeRepairData(imeData: ImeData): ImeRepairData {
		if (!imeData || !imeData.imeRepairData) {
			return null;
		}
		return imeData.imeRepairData[imeData.imeRepairData.length - 1];
	}

	private checkIfClearable() {
		if (!this.isCleared) {
			this.clearable = this.getLastImeData().methaneLevel < 50000;
		}
	}

}