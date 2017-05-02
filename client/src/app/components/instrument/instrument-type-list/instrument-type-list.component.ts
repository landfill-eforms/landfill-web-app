import { InstrumentTypeDialogComponent } from './../dialog/instrument-type-dialog/instrument-type-dialog.component';
import { PaginationComponent } from './../../directives/pagination/pagination.component';
import { InputUtils } from './../../../utils/input.utils';
import { MdDialogConfig } from '@angular/material';
import { MdDialogRef } from '@angular/material';
import { InstrumentTypeListSideinfoComponent } from './../instrument-type-list-sideinfo/instrument-type-list-sideinfo.component';
import { NavigationService } from './../../../services/app/navigation.service';
import { MdSnackBar } from '@angular/material';
import { MdDialog } from '@angular/material';
import { InstrumentTypeService } from './../../../services/instrument/instrument-type.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { AbstractDataTableComponent } from './../../../model/client/abstract-components/abstract-data-table.component';
import { InstrumentType } from './../../../model/server/persistence/entity/instrument/instrument-type.class';
import { Component, OnInit, OnDestroy, ViewChild } from '@angular/core';

@Component({
	selector: 'app-instrument-type-list',
	templateUrl: './instrument-type-list.component.html'
})
export class InstrumentTypeListComponent extends AbstractDataTableComponent<InstrumentType> implements OnInit, OnDestroy {

	@ViewChild('pagination') pagination:PaginationComponent;

	fabActionSubscriber:Subscription;

	loadingMessage:string;

	sortProperties:any = {
		type: [
			"type"
		],
		manufacturer: [
			"manufacturer",
			"type"
		],
		instantaneous: [
			"instantaneous",
			"type"
		],
		probe: [
			"probe",
			"type"
		],
		methanePercent: [
			"methanePercent",
			"type"
		],
		methanePpm: [
			"methanePpm",
			"type"
		],
		hydrogenSulfidePpm: [
			"hydrogenSulfidePpm",
			"type"
		],
		oxygenPercent: [
			"oxygenPercent",
			"type"
		],
		carbonDioxidePercent: [
			"carbonDioxidePercent",
			"type"
		],
		nitrogenPercent: [
			"nitrogenPercent",
			"type"
		],
		pressure: [
			"pressure",
			"type"
		]
	}

	filters:{text:string} = {
		text: ""
	};

	showSideInfo:boolean = false;
	selectedInstrumentType:InstrumentType;

	constructor(
		private router:Router,
		private activatedRoute:ActivatedRoute,
		private instrumentTypeService:InstrumentTypeService,
		private dialog:MdDialog,
		private snackBar:MdSnackBar,
		private navigationService:NavigationService) {
			super();
			navigationService.getNavbarComponent().expanded = true;
			navigationService.getSideinfoComponent().setDirective(InstrumentTypeListSideinfoComponent, {instrumentType: null});
			navigationService.getSideinfoComponent().enable();
	}

	ngOnInit() {
		this.navigationService.getNavbarComponent().setFabInfo({
			icon: "add",
			tooltip: "New Type"
		});
		this.fabActionSubscriber = this.navigationService
			.getNavbarComponent()
			.getFabActionSource()
			.asObservable()
			.subscribe((event) => {
				if (event instanceof MouseEvent) {
					this.openNewInstrumentTypeDialog();
				}
			});
		this.loadingMessage = "Loading...";
		this.loadInstrumentTypes();
	}

	ngOnDestroy() {
		this.navigationService.getSideinfoComponent().disable();
		this.navigationService.getNavbarComponent().resetFabInfo();
		this.navigationService.getNavbarComponent().resetFabActionSource();
		this.fabActionSubscriber.unsubscribe();
	}

	private loadInstrumentTypes() {
		this.instrumentTypeService.getAll((data) => {
			console.log(data);
			this.data = data;
			this.applyFilters();
			this.navigationService.getSideinfoComponent().open();
			this.showSideInfo = true;
			this.isDataLoaded = true;
		});
	}

	openNewInstrumentTypeDialog() {
		let dialogConfig:MdDialogConfig = new MdDialogConfig();
		dialogConfig.width = '640px';
			//dialogConfig.height = '480px';
		let dialogRef:MdDialogRef<InstrumentTypeDialogComponent> = this.dialog.open(InstrumentTypeDialogComponent, dialogConfig);
		dialogRef.afterClosed().subscribe(result => {
			if (result) {
				this.snackBar.open("New equipment type added.", "OK", {duration: 2000});
				this.isDataLoaded = false;
				this.loadingMessage = "Reloading..."
				this.loadInstrumentTypes();
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

		// TODO Implement this.
		this.filteredData = this.data.filter(o => true);

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

	selectInstrumentType(instrumentType:InstrumentType) {
		if (!this.selectedInstrumentType) {
			this.navigationService.getSideinfoComponent().open();
			this.showSideInfo = true;
		}
		this.selectedInstrumentType = instrumentType;
		this.navigationService.getSideinfoComponent().subtitle = this.selectedInstrumentType.type; 
		this.navigationService.getSideinfoComponent().getDirective().setData(this.selectedInstrumentType);
	}

	deselectInstrumentType() {
		this.selectedInstrumentType = null;
	}

	navigateToInstrumentType(instrumentType:InstrumentType) {
		this.router.navigate([instrumentType.id], {relativeTo: this.activatedRoute});
	}

}