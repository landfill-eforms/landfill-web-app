import { IMENumberStatus } from './../../../model/server/persistence/entity/instantaneous/ime-number-status.enum';
import { IMENumberService } from './../../../services/ime-number.service';
import { IMENumber } from './../../../model/server/persistence/entity/instantaneous/ime-number.class';
import { UnverifiedInstantaneousData } from './../../../model/server/persistence/entity/unverified/unverified-instantaneous-data.class';
import { AssignIMENumberDialogComponent } from './../assign-ime-number-dialog/assign-ime-number-dialog.component';
import { MdDialogRef } from '@angular/material';
import { MdDialog, MdDialogConfig } from '@angular/material';
import { DateTimeUtils } from './../../../utils/date-time.utils';
import { StringUtils } from './../../../utils/string.utils';
import { EnumUtils } from './../../../utils/enum.utils';
import { Site } from './../../../model/server/model/site.enum';
import { MonitoringPoint } from './../../../model/server/model/monitoring-point.enum';
import { MdSnackBar } from '@angular/material';
import { UnverifiedDataService } from './../../../services/unverified-data-set.service';
import { ActivatedRoute } from '@angular/router';
import { UnverifiedDataSet } from './../../../model/server/persistence/entity/unverified/unverified-data-set.class';
import { Component, OnInit } from '@angular/core';

@Component({
	selector: 'app-unverified-data-set',
	templateUrl: './unverified-data-set.component.html',
	styleUrls: ['./unverified-data-set.component.scss']
})
export class UnverifiedDataSetComponent implements OnInit {

	StringUtils = StringUtils;
	DateTimeUtils = DateTimeUtils;

	isDataLoaded:boolean = false;
	dataSetId:string;
	dataSet:UnverifiedDataSet;
	existingIMENumbers:IMENumber[];
	createdIMENumbers:IMENumber[]; // IME numbers created during this session.
	sort:any = {
		current: "",
		reversed: false
	}

	constructor(
		private activatedRoute:ActivatedRoute,
		private unverifiedDataService:UnverifiedDataService,
		private imeNumberService:IMENumberService,
		private dialog:MdDialog,
		private snackBar:MdSnackBar,
	) {}

	ngOnInit() {
		this.dataSetId = this.activatedRoute.params['_value']['id'];
		
		this.unverifiedDataService.getById((data) => {
			console.log(data);
			data["site"] = EnumUtils.convertToEnum(Site, data["site"]);
			for (let j = 0; j < data.unverifiedInstantaneousData.length; j++) {
				data.unverifiedInstantaneousData[j]["monitoringPoint"] = EnumUtils.convertToEnum(MonitoringPoint, data.unverifiedInstantaneousData[j]["monitoringPoint"]);
			}
			this.dataSet = this.unverifiedDataService.checkForErrors(data);
			this.sortByGrid();
			this.imeNumberService.getBySite((data) => {
				// TODO Use current date.
				this.existingIMENumbers = data.filter(number => 
					number.discoveryDate >= this.dataSet.uploadedDate - 1000 * 60 * 60 * 24 * 30
				);
				console.log(this.existingIMENumbers);
				this.isDataLoaded = true;
			}, this.dataSet.site);
		}, this.dataSetId);
	}

	openAssignIMENumberDialog(data:UnverifiedInstantaneousData) {
		let dialogConfig:MdDialogConfig = new MdDialogConfig();
		dialogConfig.width = '640px';
		let dialogRef:MdDialogRef<AssignIMENumberDialogComponent> = this.dialog.open(AssignIMENumberDialogComponent, dialogConfig);
		dialogRef.componentInstance.data = data;
		dialogRef.componentInstance.createdIMENumbers = this.createdIMENumbers;
		dialogRef.componentInstance.existingIMENumbers = this.existingIMENumbers;
		dialogRef.afterClosed().subscribe(result => {
			if (result) {
				this.snackBar.open("IME number has been updated.", "OK", {duration: 2000});
			}
		});
	}

	sortByGrid() {
		if (this.sort.current === "grid") {
			this.sort.reversed = !this.sort.reversed;
		}
		else {
			this.sort.current = "grid";
			this.sort.reversed = false;
		}
		this.dataSet.unverifiedInstantaneousData.sort((a, b) => {
			let compareGrid = (a.monitoringPoint.ordinal - b.monitoringPoint.ordinal) * (this.sort.reversed ? -1 : 1)
			if (compareGrid != 0) {
				return compareGrid;
			}
			return (a.startTime - b.startTime) * (this.sort.reversed ? -1 : 1);
		});
	}

	sortByIme() {
		if (this.sort.current === "ime") {
			this.sort.reversed = !this.sort.reversed;
		}
		else {
			this.sort.current = "ime";
			this.sort.reversed = false;
		}
		this.dataSet.unverifiedInstantaneousData.sort((a, b) => {
			if (a.imeNumber && !b.imeNumber) {
				return 1 * (this.sort.reversed ? -1 : 1);
			}
			else if (b.imeNumber && !a.imeNumber) {
				return -1 * (this.sort.reversed ? -1 : 1);
			}
			else if (!a.imeNumber && !b.imeNumber) {
				var compareGrid = 0;
			}
			else {
				var compareGrid = this.stringSortFunction(a.imeNumber.imeNumber, b.imeNumber.imeNumber, this.sort.reversed);
			}
			if (compareGrid != 0) {
				return compareGrid;
			}
			return this.stringSortFunction(a.monitoringPoint.name, b.monitoringPoint.name, this.sort.reversed);
		});
	}

	sortByMethaneLevel() {
		if (this.sort.current === "methaneLevel") {
			this.sort.reversed = !this.sort.reversed;
		}
		else {
			this.sort.current = "methaneLevel";
			this.sort.reversed = false;
		}
		this.dataSet.unverifiedInstantaneousData.sort((a, b) => (a.methaneLevel - b.methaneLevel) * (this.sort.reversed ? -1 : 1));
	}

	// TODO Move this to a util class.
	private stringSortFunction(a:string, b:string, reversed:boolean):number {
		if (a > b) return reversed ? -1 : 1;
		if (a == b) return 0;
		if (a < b) return reversed ? 1 : -1;
	}

}