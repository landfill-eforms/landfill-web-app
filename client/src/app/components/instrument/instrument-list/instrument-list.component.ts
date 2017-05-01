import { InstrumentDialogComponent } from './../dialog/instrument-dialog/instrument-dialog.component';
import { PaginationComponent } from './../../directives/pagination/pagination.component';
import { InstrumentListSideinfoComponent } from './../instrument-list-sideinfo/instrument-list-sideinfo.component';
import { InstrumentService } from './../../../services/instrument/instrument.service';
import { InstrumentType } from './../../../model/server/persistence/entity/instrument/instrument-type.class';
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
import { Instrument } from './../../../model/server/persistence/entity/instrument/instrument.class';
import { Component, OnInit, ViewChild, OnDestroy } from '@angular/core';

@Component({
	selector: 'app-instrument-list',
	templateUrl: './instrument-list.component.html'
})
export class InstrumentListComponent extends AbstractDataTableComponent<Instrument> implements OnInit, OnDestroy {

	@ViewChild('pagination') pagination:PaginationComponent;

	fabActionSubscriber:Subscription;

	isInstrumentTypesLoaded:boolean
	loadingMessage:string;
	instrumentTypes:InstrumentType[];

	sortProperties:any = {
		serialNumber: [
			"serialNumber"
		],
		instrumentType: [
			"instrumentType.type",
			"serialNumber"
		],
		manufacturer: [
			"instrumentType.manufacturer",
			"instrumentType.type",
			"serialNumber"
		],
		status: [
			"instrumentStatus",
			"serialNumber"
		],
		serviceDueDate: [
			"serviceDueDate",
			"serialNumber"
		],
		lastServiceDate: [
			"lastServiceDate",
			"serialNumber"
		],
		purchaseDate: [
			"purchaseDate",
			"serialNumber"
		],
		site: [
			"site",
			"serialNumber"
		],
		inventoryNumber: [
			"inventoryNumber",
			"serialNumber"
		],
	}

	filters:{text:string} = {
		text: ""
	};

	showSideInfo:boolean = false;
	selectedInstrument:Instrument;

	constructor(
		private router:Router,
		private activatedRoute:ActivatedRoute,
		private instrumentService:InstrumentService,
		private instrumentTypeService:InstrumentTypeService,
		private dialog:MdDialog,
		private snackBar:MdSnackBar,
		private navigationService:NavigationService) {
			super();
			navigationService.getNavbarComponent().expanded = true;
			navigationService.getSideinfoComponent().setDirective(InstrumentListSideinfoComponent, {instrumentType: null});
			navigationService.getSideinfoComponent().enable();
	}

	ngOnInit() {
		this.navigationService.getNavbarComponent().setFabInfo({
			icon: "add",
			tooltip: "New Equipment"
		});
		this.fabActionSubscriber = this.navigationService
			.getNavbarComponent()
			.getFabActionSource()
			.asObservable()
			.subscribe((event) => {
				if (event instanceof MouseEvent) {
					this.openNewInstrumentDialog();
				}
			});
		this.loadingMessage = "Loading Equipment List...";
		this.loadInstruments();
		this.loadInstrumentTypes();
	}

	ngOnDestroy() {
		this.navigationService.getSideinfoComponent().disable();
		this.navigationService.getNavbarComponent().resetFabInfo();
		this.navigationService.getNavbarComponent().resetFabActionSource();
		this.fabActionSubscriber.unsubscribe();
	}
	
	loadInstruments() {
		this.instrumentService.getAll((data) => {
			console.log(data);
			this.data = data;
			this.applyFilters();
			this.navigationService.getSideinfoComponent().open();
			this.isDataLoaded = true;
		});
	}

	loadInstrumentTypes() {
		this.instrumentTypeService.getAll((data) => {
			console.log(data);
			this.instrumentTypes = data;
			this.isInstrumentTypesLoaded = true;
		});
	}

	openNewInstrumentDialog() {
		let dialogConfig:MdDialogConfig = new MdDialogConfig();
		dialogConfig.width = '640px';
		//dialogConfig.height = '480px';
		let dialogRef:MdDialogRef<InstrumentDialogComponent> = this.dialog.open(InstrumentDialogComponent, dialogConfig);
		dialogRef.componentInstance.instrumentTypes = this.instrumentTypes;
		dialogRef.afterClosed().subscribe(result => {
			if (result) {
				this.snackBar.open("New equipment added.", "OK", {duration: 2000});
				this.isDataLoaded = false;
				this.loadingMessage = "Reloading..."
				this.loadInstruments();
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

	selectInstrument(instrument:Instrument) {
		if (!this.selectedInstrument) {
			this.navigationService.getSideinfoComponent().open();
			this.showSideInfo = true;
		}
		this.selectedInstrument = instrument;
		this.navigationService.getSideinfoComponent().subtitle = this.selectedInstrument.serialNumber; 
		this.navigationService.getSideinfoComponent().getDirective().setData(this.selectedInstrument);
	}

	deselectInstrument() {
		this.selectedInstrument = null;
	}

	navigateToInstrument(instrument:Instrument) {
		this.router.navigate([instrument.id], {relativeTo: this.activatedRoute});
	}
	
}