import { UserPermission } from './../../../model/server/persistence/enums/user/user-permission.enum';
import { AuthService } from './../../../services/auth/auth.service';
import { DateTimeUtils } from './../../../utils/date-time.utils';
import { Site } from './../../../model/server/persistence/enums/location/site.enum';
import { InstrumentStatus } from './../../../model/server/persistence/enums/instrument/instrument-status.enum';
import { EnumUtils } from './../../../utils/enum.utils';
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

	DateTimeUtils = DateTimeUtils;

	@ViewChild('pagination') pagination:PaginationComponent;

	fabActionSubscriber:Subscription;

	isInstrumentTypesLoaded:boolean;
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

	filters:{text:string, status:number, site:number} = {
		text: "",
		status: -1,
		site: -1
	};

	statusFilterChoices:any[] = [
		{
			ordinal: -1,
			name: "Any"
		},
		...InstrumentStatus.values()
	];

	siteFilterChoices:any[] = [
		{
			ordinal: -1,
			name: "Any"
		},
		...Site.values()
	];

	showSideInfo:boolean = false;
	selectedInstrument:Instrument;

	constructor(
		private authService:AuthService,
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
		this.canEdit = this.authService.canAccess([UserPermission.EDIT_INSTRUMENTS]);
		if(this.authService.canAccess([UserPermission.CREATE_INSTRUMENTS])) {
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
						this.openInstrumentDialog(null);
					}
				});
		}
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
	
	private loadInstruments() {
		this.instrumentService.getAll((data) => {
			console.log(data);
			this.data = data;
			for (let instrument of this.data) {
				instrument.instrumentStatus = EnumUtils.convertToEnum(InstrumentStatus, instrument.instrumentStatus);
				instrument.site = EnumUtils.convertToEnum(Site, instrument.site);
			}
			this.applyFilters();
			this.navigationService.getSideinfoComponent().open();
			this.showSideInfo = true;
			this.isDataLoaded = true;
		});
	}

	private loadInstrumentTypes() {
		this.instrumentTypeService.getAll((data) => {
			console.log(data);
			this.instrumentTypes = data;
			this.isInstrumentTypesLoaded = true;
		});
	}

	openInstrumentDialog(instrument:Instrument) {
		if (!this.isDataLoaded || !this.isInstrumentTypesLoaded) {
			return;
		}
		let isNew:boolean = !instrument;
		let dialogConfig:MdDialogConfig = new MdDialogConfig();
		dialogConfig.width = '800px';
		let dialogRef:MdDialogRef<InstrumentDialogComponent> = this.dialog.open(InstrumentDialogComponent, dialogConfig);
		dialogRef.componentInstance.instrumentTypes = this.instrumentTypes;
		if (isNew) {
			dialogRef.componentInstance.isNew = true;
		}
		else {
			dialogRef.componentInstance.instrument = instrument;
		}
		dialogRef.afterClosed().subscribe(result => {
			if (result) {
				if (isNew) {
					this.snackBar.open("New equipment added.", "OK", {duration: 2000});
				}
				else {
					this.snackBar.open("Equipment updated.", "OK", {duration: 2000});
				}
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

		this.filteredData = this.data.filter(o => {
			let textMatch:boolean = true;
			if (this.filters.text) {
				let search:RegExp = new RegExp(this.filters.text, 'i');
				textMatch = search.test(o.serialNumber) || search.test(o.inventoryNumber) || search.test(o.description);
			}
			let statusMatch:boolean = true;
			if (this.filters.status >= 0) {
				statusMatch = o.instrumentStatus && (o.instrumentStatus.ordinal == this.filters.status);
			}
			let siteMatch:boolean = true;
			if (this.filters.site >= 0) {
				siteMatch = o.site && (o.site.ordinal == this.filters.site);
			}
			return textMatch && statusMatch && siteMatch;
		});

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
	
}