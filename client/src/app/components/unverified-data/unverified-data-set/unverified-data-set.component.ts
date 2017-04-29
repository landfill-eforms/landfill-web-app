import { OkDialogComponent } from './../../directives/dialogs/ok-dialog/ok-dialog.component';
import { UnverifiedProbeData } from './../../../model/server/persistence/entity/unverified/unverified-probe-data.class';
import { UnverifiedIntegratedData } from './../../../model/server/persistence/entity/unverified/unverified-integrated-data.class';
import { Instrument } from './../../../model/server/persistence/entity/instrument/instrument.class';
import { InstrumentService } from './../../../services/instrument/instrument.service';
import { ImeNumberService } from './../../../services/instantaneous/ime-number.service';
import { ImeNumber } from './../../../model/server/persistence/entity/surfaceemission/instantaneous/ime-number.class';
import { UnverifiedInstantaneousData } from './../../../model/server/persistence/entity/unverified/unverified-instantaneous-data.class';
import { AssignImeNumberDialogComponent } from './../assign-ime-number-dialog/assign-ime-number-dialog.component';
import { MdDialogRef } from '@angular/material';
import { MdDialog, MdDialogConfig } from '@angular/material';
import { DateTimeUtils } from './../../../utils/date-time.utils';
import { StringUtils } from './../../../utils/string.utils';
import { EnumUtils } from './../../../utils/enum.utils';
import { Site } from './../../../model/server/persistence/enums/location/site.enum';
import { MonitoringPoint } from './../../../model/server/persistence/enums/location/monitoring-point.enum';
import { MdSnackBar } from '@angular/material';
import { UnverifiedDataService } from './../../../services/unverified/unverified-data-set.service';
import { ActivatedRoute, Router } from '@angular/router';
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
	instruments:Instrument[] = [];
	dataSetId:string;
	dataSet:UnverifiedDataSet;
	existingImeNumbers:ImeNumber[];
	createdImeNumbers:ImeNumber[]; // IME numbers created during this session.
	barometricPressure:number;
	sort:any = {
		current: "",
		reversed: false
	}

	constructor(
		private activatedRoute:ActivatedRoute,
		private router:Router,
		private unverifiedDataService:UnverifiedDataService,
		private imeNumberService:ImeNumberService,
		private instrumentService:InstrumentService,
		private dialog:MdDialog,
		private snackBar:MdSnackBar,
	) {}

	ngOnInit() {
		this.dataSetId = this.activatedRoute.params['_value']['id'];
		
		this.unverifiedDataService.getById(this.dataSetId,
			(data) => {
				this.dataSet = this.processData(data);
				for (let i = 0; i < this.dataSet.unverifiedInstantaneousData.length; i++) {
					if (!this.dataSet.unverifiedInstantaneousData[i].instrument) {
						this.dataSet.unverifiedInstantaneousData[i].instrument = <any>{}; // LOLOLOL
					}
				}
				for (let i = 0; i < this.dataSet.unverifiedIntegratedData.length; i++) {
					if (!this.dataSet.unverifiedIntegratedData[i].instrument) {
						this.dataSet.unverifiedIntegratedData[i].instrument = <any>{}; // LOLOLOL
					}
				}
				this.sortByGrid();
				this.imeNumberService.getBySite(this.dataSet.site,
					(data) => {
						// TODO Use current date.
						this.existingImeNumbers = data;
						// .filter(number => 
						// 	number.dateCode >= this.dataSet.uploadedDate - 1000 * 60 * 60 * 24 * 30
						// );
						console.log(this.existingImeNumbers);
						this.instrumentService.getAll((data) => {
							this.instruments = data;
							// this.instruments = data.filter(o => o.instrumentType.probe || o.instrumentType.instantaneous); // TODO Do this properly.
							this.isDataLoaded = true;
						});
					}
				);
			}
		);
	}

	// TODO Clean this up.
	processData(data:any):UnverifiedDataSet {
		data["site"] = EnumUtils.convertToEnum(Site, data["site"]);
		if (data.barometricPressure) {
			data.barometricPressure = data.barometricPressure / 100;
		}
		for (let i = 0; i < data.unverifiedInstantaneousData.length; i++) {
			let unverifiedInstantaneousData:any = data.unverifiedInstantaneousData[i];
			unverifiedInstantaneousData["monitoringPoint"] = EnumUtils.convertToEnum(MonitoringPoint, unverifiedInstantaneousData["monitoringPoint"]);

			// TEMPORARY
			if (unverifiedInstantaneousData["barometricPressure"]) {
				this.barometricPressure = unverifiedInstantaneousData["barometricPressure"] / 100;
			}

		}
		for (let i = 0; i < data.unverifiedIntegratedData.length; i++) {
			let unverifiedIntegratedData:any = data.unverifiedIntegratedData[i];
			unverifiedIntegratedData["monitoringPoint"] = EnumUtils.convertToEnum(MonitoringPoint, unverifiedIntegratedData["monitoringPoint"]);
		}
		for (let i = 0; i < data.unverifiedProbeData.length; i++) {
			let unverifiedProbeData:any = data.unverifiedProbeData[i];
			unverifiedProbeData["monitoringPoint"] = EnumUtils.convertToEnum(MonitoringPoint, unverifiedProbeData["monitoringPoint"]);
		}
		return this.unverifiedDataService.checkForErrors(data);
	}

	openAssignImeNumberDialog(data:UnverifiedInstantaneousData) {
		let dialogConfig:MdDialogConfig = new MdDialogConfig();
		dialogConfig.width = '640px';
		let dialogRef:MdDialogRef<AssignImeNumberDialogComponent> = this.dialog.open(AssignImeNumberDialogComponent, dialogConfig);
		dialogRef.componentInstance.site = this.dataSet.site;
		dialogRef.componentInstance.data = data;
		dialogRef.componentInstance.createdImeNumbers = this.createdImeNumbers;
		dialogRef.componentInstance.existingImeNumbers = this.existingImeNumbers;
		dialogRef.afterClosed().subscribe(result => {
			if (result) {
				this.snackBar.open("IME number has been updated.", "OK", {duration: 2000});
				this.unverifiedDataService.checkForErrors(this.dataSet);
			}
		});
	}

	removeImeNumbers(data:UnverifiedInstantaneousData) {
		this.snackBar.open("IME number has been removed.", "OK", {duration: 2000});
		data.imeNumbers = [];
		this.unverifiedDataService.checkForErrors(this.dataSet);
	}

	updateBarometricPressure() {
		for (let i = 0; i < this.dataSet.unverifiedInstantaneousData.length; i++) {
			if (this.barometricPressure) {
				this.dataSet.unverifiedInstantaneousData[i].barometricPressure = this.barometricPressure * 100;
			}
		}
		for (let i = 0; i < this.dataSet.unverifiedIntegratedData.length; i++) {
			if (this.barometricPressure) {
				this.dataSet.unverifiedIntegratedData[i].barometricPressure = this.barometricPressure * 100;
			}
		}
		for (let i = 0; i < this.dataSet.unverifiedProbeData.length; i++) {
			if (this.barometricPressure) {
				this.dataSet.unverifiedProbeData[i].barometricPressure = this.barometricPressure * 100;
			}
		}
		this.unverifiedDataService.checkForErrors(this.dataSet);
	}

	save() {
		// this.dataSet.site = EnumUtils.convertToString(this.dataSet.site);
		// for (let i = 0; i < this.dataSet.unverifiedInstantaneousData.length; i++) {
		// 	let unverifiedInstantaneousData:UnverifiedInstantaneousData =  this.dataSet.unverifiedInstantaneousData[i];
		// 	unverifiedInstantaneousData.monitoringPoint = EnumUtils.convertToString(unverifiedInstantaneousData.monitoringPoint);
		// }
		// for (let i = 0; i < this.dataSet.unverifiedIntegratedData.length; i++) {
		// 	let unverifiedIntegratedData:UnverifiedIntegratedData =  this.dataSet.unverifiedIntegratedData[i];
		// 	unverifiedIntegratedData.monitoringPoint = EnumUtils.convertToString(unverifiedIntegratedData.monitoringPoint);
		// }
		// for (let i = 0; i < this.dataSet.unverifiedProbeData.length; i++) {
		// 	let unverifiedProbeData:UnverifiedProbeData =  this.dataSet.unverifiedProbeData[i];
		// 	unverifiedProbeData.monitoringPoint = EnumUtils.convertToString(unverifiedProbeData.monitoringPoint);
		// }
		console.log(this.dataSet);
		this.unverifiedDataService.update(this.dataSet, 
			(data) => {
				if (data) {
					this.processData(this.dataSet);
					this.snackBar.open("Data saved.", "OK", {duration: 2000});
				}
			}
		);
	}

	commitAll() {
		if (this.dataSet.errors && (this.dataSet.errors.dataSet.length != 0 || this.dataSet.errors.instantaneous.length != 0)) {
			this.snackBar.open("Cannot commit data because it contains errors.", "OK", {duration: 2000});
			return;
		}
		// this.dataSet.site = EnumUtils.convertToString(this.dataSet.site);
		// for (let i = 0; i < this.dataSet.unverifiedInstantaneousData.length; i++) {
		// 	let unverifiedInstantaneousData:UnverifiedInstantaneousData =  this.dataSet.unverifiedInstantaneousData[i];
		// 	unverifiedInstantaneousData.monitoringPoint = EnumUtils.convertToString(unverifiedInstantaneousData.monitoringPoint);
		// }
		// for (let i = 0; i < this.dataSet.unverifiedIntegratedData.length; i++) {
		// 	let unverifiedIntegratedData:UnverifiedIntegratedData =  this.dataSet.unverifiedIntegratedData[i];
		// 	unverifiedIntegratedData.monitoringPoint = EnumUtils.convertToString(unverifiedIntegratedData.monitoringPoint);
		// }
		// for (let i = 0; i < this.dataSet.unverifiedProbeData.length; i++) {
		// 	let unverifiedProbeData:UnverifiedProbeData =  this.dataSet.unverifiedProbeData[i];
		// 	unverifiedProbeData.monitoringPoint = EnumUtils.convertToString(unverifiedProbeData.monitoringPoint);
		// }
		this.unverifiedDataService.commit(this.dataSet,
			(data) => {
				if (data) {
					this.processData(this.dataSet);
					this.snackBar.open("Data set successfully verified.", "OK", {duration: 3000});
					this.router.navigate(['/app/unverified-data-set-list']);
				}
			},
			(err) => {
				let message:string[] = JSON.parse(err.text()).message.split("\n");
				this.openErrorCallbackDialog(message);
			}
		);
	}

	// TODO Update save methods.

	openErrorCallbackDialog(message:string[]) {
		let dialogConfig:MdDialogConfig = new MdDialogConfig();
		dialogConfig.width = '640px';
		let dialogRef:MdDialogRef<OkDialogComponent> = this.dialog.open(OkDialogComponent, dialogConfig);
		dialogRef.componentInstance.title = "Error Committing Data";
		dialogRef.componentInstance.prompt = message;
		dialogRef.componentInstance.confirmLabel = "OK";
		dialogRef.afterClosed().subscribe((res) => {
			if (res) {
				console.log(this.dataSet);
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
			if (a.imeNumbers[0] && !b.imeNumbers[0]) {
				return 1 * (this.sort.reversed ? -1 : 1);
			}
			else if (b.imeNumbers[0] && !a.imeNumbers[0]) {
				return -1 * (this.sort.reversed ? -1 : 1);
			}
			else if (!a.imeNumbers[0] && !b.imeNumbers[0]) {
				var compareGrid = 0;
			}
			else {
				var compareGrid = this.stringSortFunction(a.imeNumbers[0].imeNumber, b.imeNumbers[0].imeNumber, this.sort.reversed);
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

	consoleLog() {
		console.log(this.dataSet);
	}

}