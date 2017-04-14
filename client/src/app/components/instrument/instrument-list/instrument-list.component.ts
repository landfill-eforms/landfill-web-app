import { InstrumentListSideinfoComponent } from './../instrument-list-sideinfo/instrument-list-sideinfo.component';
import { NavigationService } from './../../../services/app/navigation.service';
import { Router, ActivatedRoute } from '@angular/router';
import { InputStatus, InputUtils } from './../../../utils/input.utils';
import { PaginationComponent, Paginfo } from './../../directives/pagination/pagination.component';
import { Subscription } from 'rxjs/Subscription';
import { NewInstrumentDialogComponent } from './../dialog/new-instrument-dialog/new-instrument-dialog.component';
import { InstrumentTypeService } from './../../../services/instrument/instrument-type.service';
import { InstrumentType } from './../../../model/server/persistence/entity/instrument/instrument-type.class';
import { MdDialog, MdSnackBar, MdDialogConfig, MdDialogRef } from '@angular/material';
import { InstrumentService } from './../../../services/instrument/instrument.service';
import { Instrument } from './../../../model/server/persistence/entity/instrument/instrument.class';
import { Sort, SortUtils } from './../../../utils/sort.utils';
import { Component, OnInit, ViewChild } from '@angular/core';

@Component({
	selector: 'app-instrument-list',
	templateUrl: './instrument-list.component.html',
	styleUrls: ['./instrument-list.component.scss']
})
export class InstrumentListComponent implements OnInit {

	@ViewChild('pagination') pagination:PaginationComponent;

	fabActionSubscriber:Subscription;

	isDataLoaded:boolean;
	loadingMessage:string;
	instruments:Instrument[];
	instrumentTypes:InstrumentType[];
	
	sort:Sort = {
		current: "id",
		reversed: false
	}

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

	showFilters:boolean = false;
	filteredRowsCount:number = 0;
	filteredInstruments:Instrument[] = [];
	filters:{text:string} = {
		text: ""
	};
	textFilterStatus:InputStatus = {
		valid: true,
		errorMessage: ""
	};

	paginfo:Paginfo = new Paginfo();
	paginatedInstruments:Instrument[] = [];

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
			navigationService.getNavbarComponent().expanded = true;
			navigationService.getSideinfoComponent().setDirective(InstrumentListSideinfoComponent, {instrumentType: null});
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
			this.instruments = data;
			this.applyFilters();
			this.navigationService.getSideinfoComponent().open();
			//this.isDataLoaded = true;
		});
	}

	loadInstrumentTypes() {
		this.instrumentTypeService.getAll((data) => {
			console.log(data);
			this.instrumentTypes = data;
			this.isDataLoaded = true;
		});
	}

	openNewInstrumentDialog() {
		let dialogConfig:MdDialogConfig = new MdDialogConfig();
		dialogConfig.width = '640px';
		//dialogConfig.height = '480px';
		let dialogRef:MdDialogRef<NewInstrumentDialogComponent> = this.dialog.open(NewInstrumentDialogComponent, dialogConfig);
		dialogRef.componentInstance.instrumentTypes = this.instrumentTypes;
		dialogRef.afterClosed().subscribe(result => {
			if (result) {
				this.snackBar.open("New user group has been created.", "OK", {duration: 2000});
				this.isDataLoaded = false;
				this.loadingMessage = "Reloading..."
				this.loadInstruments();
			}
		});
	}

	sortBy(sortBy:string) {
		SortUtils.sortAndUpdate(this.sort, sortBy, this.instruments, this.sortProperties[sortBy]);
		this.applyFilters();
	}

	toggleFilters() {
		if (this.showFilters) {
			this.showFilters = false;
			this.resetFilters();
		}
		else {
			this.showFilters = true;
		}
	}

	applyFilters() {

		// Validate the text search string.
		InputUtils.isAlphanumeric(this.filters.text, this.textFilterStatus, "Cannot have special characters in search.");

		// If the text search string is invalid, then return.
		if (!this.textFilterStatus.valid) {
			return;
		}

		// TODO Implement this.
		this.filteredInstruments = this.instruments.filter(o => true);

		this.paginfo.totalRows = this.filteredInstruments.length;
		if (this.pagination) {
			this.pagination.update();
		}
		this.applyPagination();
	}

	resetFilters() {
		this.filters.text = "";
		this.applyFilters();
	}

	applyPagination() {
		this.paginatedInstruments = this.filteredInstruments.filter((o, i) => {
			return i >= (this.paginfo.currentPage - 1) * this.paginfo.displayedRows && i < this.paginfo.currentPage * this.paginfo.displayedRows;
		});
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