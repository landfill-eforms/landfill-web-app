import { InstrumentTypeListSideinfoComponent } from './../instrument-type-list-sideinfo/instrument-type-list-sideinfo.component';
import { NavigationService } from './../../../services/app/navigation.service';
import { Router, ActivatedRoute } from '@angular/router';
import { InputStatus, InputUtils } from './../../../utils/input.utils';
import { Subscription } from 'rxjs/Subscription';
import { PaginationComponent, Paginfo } from './../../directives/pagination/pagination.component';
import { NewInstrumentTypeDialogComponent } from './../dialog/new-instrument-type-dialog/new-instrument-type-dialog.component';
import { MdDialogRef } from '@angular/material';
import { MdDialog, MdSnackBar, MdDialogConfig } from '@angular/material';
import { InstrumentTypeService } from './../../../services/instrument/instrument-type.service';
import { Sort, SortUtils } from './../../../utils/sort.utils';
import { InstrumentType } from './../../../model/server/persistence/entity/instrument/instrument-type.class';
import { Component, OnInit, ViewChild } from '@angular/core';

@Component({
	selector: 'app-instrument-type-list',
	templateUrl: './instrument-type-list.component.html',
	styleUrls: ['./instrument-type-list.component.scss']
})
export class InstrumentTypeListComponent implements OnInit {

	@ViewChild('pagination') pagination:PaginationComponent;

	fabActionSubscriber:Subscription;

	isDataLoaded:boolean;
	loadingMessage:string;
	instrumentTypes:InstrumentType[];
	
	sort:Sort = {
		current: "id",
		reversed: false
	}

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

	showFilters:boolean = false;
	filteredRowsCount:number = 0;
	filteredInstrumentTypes:InstrumentType[] = [];
	filters:{text:string} = {
		text: ""
	};
	textFilterStatus:InputStatus = {
		valid: true,
		errorMessage: ""
	};

	paginfo:Paginfo = new Paginfo();
	paginatedInstrumentTypes:InstrumentType[] = [];

	showSideInfo:boolean = false;
	selectedInstrumentType:InstrumentType;

	constructor(
		private router:Router,
		private activatedRoute:ActivatedRoute,
		private instrumentTypeService:InstrumentTypeService,
		private dialog:MdDialog,
		private snackBar:MdSnackBar,
		private navigationService:NavigationService) {
			navigationService.getNavbarComponent().expanded = true;
			navigationService.getSideinfoComponent().setDirective(InstrumentTypeListSideinfoComponent, {instrumentType: null});
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
			this.instrumentTypes = data;
			this.applyFilters();
			this.isDataLoaded = true;
		});
	}

	openNewInstrumentTypeDialog() {
		let dialogConfig:MdDialogConfig = new MdDialogConfig();
		dialogConfig.width = '640px';
			//dialogConfig.height = '480px';
		let dialogRef:MdDialogRef<NewInstrumentTypeDialogComponent> = this.dialog.open(NewInstrumentTypeDialogComponent, dialogConfig);
		dialogRef.afterClosed().subscribe(result => {
			if (result) {
				this.snackBar.open("New equipment type added.", "OK", {duration: 2000});
				this.isDataLoaded = false;
				this.loadingMessage = "Reloading..."
				this.loadInstrumentTypes();
			}
		});
	}

	sortBy(sortBy:string) {
		SortUtils.sortAndUpdate(this.sort, sortBy, this.instrumentTypes, this.sortProperties[sortBy]);
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
		this.filteredInstrumentTypes = this.instrumentTypes.filter(o => true);

		this.paginfo.totalRows = this.filteredInstrumentTypes.length;
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
		this.paginatedInstrumentTypes = this.filteredInstrumentTypes.filter((o, i) => {
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