import { NavigationService } from './../../../services/app/navigation.service';
import { RestrictedRoute } from './../../../routes/restricted.route';
import { AppConstant } from './../../../app.constant';
import { EditUnverifiedWarmspotDialogComponent } from './../dialog/edit-unverified-warmspot-dialog/edit-unverified-warmspot-dialog.component';
import { EditUnverifiedIntegratedDialogComponent } from './../dialog/edit-unverified-integrated-dialog/edit-unverified-integrated-dialog.component';
import { EditUnverifiedInstantaneousDialogComponent } from './../dialog/edit-unverified-instantaneous-dialog/edit-unverified-instantaneous-dialog.component';
import { IseNumber } from './../../../model/server/persistence/entity/surfaceemission/integrated/ise-number.class';
import { UnverifiedWarmspotData } from './../../../model/server/persistence/entity/unverified/unverified-warmspot-data.class';
import { ExceedanceStatus } from './../../../model/server/persistence/enums/exceedance/exceedance-status.enum';
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
	unverifiedDataSetId:string;
	unverifiedDataSet:UnverifiedDataSet;
	existingImeNumbers:ImeNumber[];
	createdImeNumbers:ImeNumber[]; // IME numbers created during this session.
	barometricPressure:number;

	activeItem:any = {};

	selectedInstantaneous:UnverifiedInstantaneousData[] = [];

	infoCardOpen:boolean;

	constructor(
		private activatedRoute:ActivatedRoute,
		private router:Router,
		private unverifiedDataService:UnverifiedDataService,
		private imeNumberService:ImeNumberService,
		private instrumentService:InstrumentService,
		private dialog:MdDialog,
		private snackBar:MdSnackBar,
		private navigationService:NavigationService) {
			navigationService.getNavbarComponent().expanded = false;
			navigationService.getSideinfoComponent().disable();
	}

	ngOnInit() {
		this.unverifiedDataSetId = this.activatedRoute.params['_value']['id'];
		
		this.unverifiedDataService.getById(this.unverifiedDataSetId,
			(data) => {
				this.unverifiedDataSet = this.processData(data);
				for (let i = 0; i < this.unverifiedDataSet.unverifiedInstantaneousData.length; i++) {
					if (!this.unverifiedDataSet.unverifiedInstantaneousData[i].instrument) {
						this.unverifiedDataSet.unverifiedInstantaneousData[i].instrument = <any>{}; // LOLOLOL
					}
				}
				for (let i = 0; i < this.unverifiedDataSet.unverifiedIntegratedData.length; i++) {
					if (!this.unverifiedDataSet.unverifiedIntegratedData[i].instrument) {
						this.unverifiedDataSet.unverifiedIntegratedData[i].instrument = <any>{}; // LOLOLOL
					}
				}
				this.imeNumberService.getBySite(this.unverifiedDataSet.site,
					(data) => {
						// TODO Use current date.
						this.existingImeNumbers = data;
						// .filter(number => 
						// 	number.dateCode >= this.unverifiedDataSet.uploadedDate - 1000 * 60 * 60 * 24 * 30
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
	processData(data:UnverifiedDataSet):UnverifiedDataSet {

		data.site = EnumUtils.convertToEnum(Site, data.site);

		// Instantaneous
		for (let unverifiedInstantaneousData of data.unverifiedInstantaneousData) {
			unverifiedInstantaneousData.monitoringPoint = EnumUtils.convertToEnum(MonitoringPoint, unverifiedInstantaneousData.monitoringPoint);
		}
		data.unverifiedInstantaneousData.sort((a, b) => a.monitoringPoint.ordinal - b.monitoringPoint.ordinal);

		// Warmspot
		for (let unverifiedWarmspotData of data.unverifiedWarmspotData) {
			unverifiedWarmspotData.monitoringPoint = EnumUtils.convertToEnum(MonitoringPoint, unverifiedWarmspotData.monitoringPoint);
		}
		data.unverifiedWarmspotData.sort((a, b) => a.monitoringPoint.ordinal - b.monitoringPoint.ordinal);

		// IME Numbers
		for (let imeNumber of data.imeNumbers) {
			imeNumber.site = EnumUtils.convertToEnum(Site, imeNumber.site);
			for (let i = 0; i < imeNumber.monitoringPoints.length; i++) {
				imeNumber.monitoringPoints[i] = EnumUtils.convertToEnum(MonitoringPoint, imeNumber.monitoringPoints[i]);
			}
			imeNumber.status = EnumUtils.convertToEnum(ExceedanceStatus, imeNumber.status);
		}
		data.imeNumbers.sort((a, b) => {
			if (a.imeNumber == b.imeNumber) {
				return 0;
			}
			return a.imeNumber > b.imeNumber ? 1 : -1;
		});

		// Integrated
		for (let unverifiedIntegratedData of data.unverifiedIntegratedData) {
			unverifiedIntegratedData.monitoringPoint = EnumUtils.convertToEnum(MonitoringPoint, unverifiedIntegratedData.monitoringPoint);
		}
		data.unverifiedIntegratedData.sort((a, b) => a.monitoringPoint.ordinal - b.monitoringPoint.ordinal);

		// ISE Numbers
		for (let iseNumbers of data.iseNumbers) {
			iseNumbers.site = EnumUtils.convertToEnum(Site, iseNumbers.site);
			iseNumbers.monitoringPoint = EnumUtils.convertToEnum(MonitoringPoint, iseNumbers.monitoringPoint);
			iseNumbers.status = EnumUtils.convertToEnum(ExceedanceStatus, iseNumbers.status);
		}
		data.iseNumbers.sort((a, b) => {
			if (a.iseNumber == b.iseNumber) {
				return 0;
			}
			return a.iseNumber > b.iseNumber ? 1 : -1;
		});

		// TODO Add probe.
		return this.unverifiedDataService.checkForErrors(data);
	}

	editInstantaneous(data:UnverifiedInstantaneousData) {
		this.activeItem = data;
		let dialogConfig:MdDialogConfig = new MdDialogConfig();
		dialogConfig.width = '480px';
		let dialogRef:MdDialogRef<EditUnverifiedInstantaneousDialogComponent> = this.dialog.open(EditUnverifiedInstantaneousDialogComponent, dialogConfig);
		dialogRef.componentInstance.availableInstruments = this.instruments;
		dialogRef.componentInstance.data = data;
		dialogRef.afterClosed().subscribe(result => {
			if (result) {
				data.instrument = this.findInstrumentById(result["instrumentId"]);
				data.barometricPressure = result["barometricPressure"] * 100;
				data.methaneLevel = result["methaneLevel"] * 100;
			}
			this.activeItem = null;
			this.unverifiedDataService.checkForErrors(this.unverifiedDataSet);
		});
	}

	editWarmspot(data:UnverifiedWarmspotData) {
		this.activeItem = data;
		let dialogConfig:MdDialogConfig = new MdDialogConfig();
		dialogConfig.width = '480px';
		let dialogRef:MdDialogRef<EditUnverifiedWarmspotDialogComponent> = this.dialog.open(EditUnverifiedWarmspotDialogComponent, dialogConfig);
		dialogRef.componentInstance.availableInstruments = this.instruments;
		dialogRef.componentInstance.data = data;
		dialogRef.afterClosed().subscribe(result => {
			if (result) {
				data.instrument = this.findInstrumentById(result["instrumentId"]);
				data.methaneLevel = result["methaneLevel"] * 100;
				data.size = result["size"];
				data.description = result["description"];
			}
			this.activeItem = null;
			this.unverifiedDataService.checkForErrors(this.unverifiedDataSet);
		});
	}

	editImeNumber(data:ImeNumber) {
		
	}

	editIntegrated(data:UnverifiedIntegratedData) {
		this.activeItem = data;
		let dialogConfig:MdDialogConfig = new MdDialogConfig();
		dialogConfig.width = '480px';
		let dialogRef:MdDialogRef<EditUnverifiedIntegratedDialogComponent> = this.dialog.open(EditUnverifiedIntegratedDialogComponent, dialogConfig);
		dialogRef.componentInstance.availableInstruments = this.instruments;
		dialogRef.componentInstance.data = data;
		dialogRef.afterClosed().subscribe(result => {
			if (result) {
				data.instrument = this.findInstrumentById(result["instrumentId"]);
				data.barometricPressure = result["barometricPressure"] * 100;
				data.methaneLevel = result["methaneLevel"] * 100;
				data.bagNumber = result["bagNumber"];
				data.volume = result["volume"];
			}
			this.activeItem = null;
			this.unverifiedDataService.checkForErrors(this.unverifiedDataSet);
		});
	}

	editIseNumber(data:IseNumber) {
		
	}

	editProbeData(data:UnverifiedProbeData) {
		
	}

	toggleInstantaneous(data:UnverifiedInstantaneousData) {
		console.log(data);
		this.selectedInstantaneous = this.unverifiedDataSet.unverifiedInstantaneousData.filter(u => u.selected);
	}

	toggleWarmspot(data:UnverifiedWarmspotData) {
		
	}

	toggleImeNumber(data:ImeNumber) {
		
	}

	toggleIntegrated(data:UnverifiedIntegratedData) {

	}

	toggleIseNumber(data:IseNumber) {
		
	}

	toggleProbeData(data:UnverifiedProbeData) {
		
	}

	openAssignImeNumberDialog(data:UnverifiedInstantaneousData) {
		let dialogConfig:MdDialogConfig = new MdDialogConfig();
		dialogConfig.width = '640px';
		let dialogRef:MdDialogRef<AssignImeNumberDialogComponent> = this.dialog.open(AssignImeNumberDialogComponent, dialogConfig);
		dialogRef.componentInstance.site = this.unverifiedDataSet.site;
		dialogRef.componentInstance.data = data;
		dialogRef.componentInstance.createdImeNumbers = this.createdImeNumbers;
		dialogRef.componentInstance.existingImeNumbers = this.existingImeNumbers;
		dialogRef.afterClosed().subscribe(result => {
			if (result) {
				this.snackBar.open("IME number has been updated.", "OK", {duration: 2000});
				this.unverifiedDataService.checkForErrors(this.unverifiedDataSet);
			}
		});
	}

	removeImeNumbers(data:UnverifiedInstantaneousData) {
		this.snackBar.open("IME number has been removed.", "OK", {duration: 2000});
		data.imeNumbers = [];
		this.unverifiedDataService.checkForErrors(this.unverifiedDataSet);
	}

	updateBarometricPressure() {
		for (let i = 0; i < this.unverifiedDataSet.unverifiedInstantaneousData.length; i++) {
			if (this.barometricPressure) {
				this.unverifiedDataSet.unverifiedInstantaneousData[i].barometricPressure = this.barometricPressure * 100;
			}
		}
		for (let i = 0; i < this.unverifiedDataSet.unverifiedIntegratedData.length; i++) {
			if (this.barometricPressure) {
				this.unverifiedDataSet.unverifiedIntegratedData[i].barometricPressure = this.barometricPressure * 100;
			}
		}
		for (let i = 0; i < this.unverifiedDataSet.unverifiedProbeData.length; i++) {
			if (this.barometricPressure) {
				this.unverifiedDataSet.unverifiedProbeData[i].barometricPressure = this.barometricPressure * 100;
			}
		}
		this.unverifiedDataService.checkForErrors(this.unverifiedDataSet);
	}

	save() {
		console.log(this.unverifiedDataSet);
		this.unverifiedDataService.update(this.unverifiedDataSet, 
			(data) => {
				if (data) {
					this.processData(this.unverifiedDataSet);
					this.snackBar.open("Data saved.", "OK", {duration: 2000});
				}
			}
		);
	}

	commitAll() {
		if (this.unverifiedDataSet.errors && (this.unverifiedDataSet.errors.unverifiedDataSet.length != 0 || this.unverifiedDataSet.errors.instantaneous.length != 0)) {
			this.snackBar.open("Cannot commit data because it contains errors.", "OK", {duration: 2000});
			return;
		}
		this.unverifiedDataService.commit(this.unverifiedDataSet,
			(data) => {
				if (data) {
					this.processData(this.unverifiedDataSet);
					this.snackBar.open("Data set successfully verified.", "OK", {duration: 3000});
					this.router.navigate([AppConstant.RESTRICTED_ROUTE_BASE + '/' + RestrictedRoute.UNVERIFIED_DATA_SET_LIST.path]);
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
				console.log(this.unverifiedDataSet);
			}
		});
	}

	private findInstrumentById(id:number):Instrument {
		for (let instrument of this.instruments) {
			if (instrument.id === id) {
				return instrument;
			}
		}
		return null;
	}

	consoleLog() {
		console.log(this.unverifiedDataSet);
	}

	toggleInfoCard() {
		this.infoCardOpen = !this.infoCardOpen;
	}

}