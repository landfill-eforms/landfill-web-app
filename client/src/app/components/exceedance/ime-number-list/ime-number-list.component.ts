import { ImeNumber } from './../../../model/server/persistence/entity/surfaceemission/instantaneous/ime-number.class';
import { ImeNumberService } from './../../../services/instantaneous/ime-number.service';
import { ImeNumberListSideinfoComponent } from './../ime-number-list-sideinfo/ime-number-list-sideinfo.component';
import { ExceedanceStatus } from './../../../model/server/persistence/enums/exceedance/exceedance-status.enum';
import { DateTimeUtils } from './../../../utils/date-time.utils';
import { MonitoringPoint } from './../../../model/server/persistence/enums/location/monitoring-point.enum';
import { Site } from './../../../model/server/persistence/enums/location/site.enum';
import { EnumUtils } from './../../../utils/enum.utils';
import { AbstractDataTableComponent } from './../../../model/client/abstract-components/abstract-data-table.component';
import { InputStatus, InputUtils } from './../../../utils/input.utils';
import { NavigationService } from './../../../services/app/navigation.service';
import { MdSnackBar } from '@angular/material';
import { MdDialog } from '@angular/material';
import { Router, ActivatedRoute } from '@angular/router';
import { Sort, SortUtils } from './../../../utils/sort.utils';
import { Subscription } from 'rxjs/Subscription';
import { PaginationComponent, Paginfo } from './../../directives/pagination/pagination.component';
import { Component, OnInit, ViewChild, OnDestroy } from '@angular/core';

@Component({
	selector: 'app-ime-number-list',
	templateUrl: './ime-number-list.component.html'
})
export class ImeNumberListComponent extends AbstractDataTableComponent<ImeNumber> implements OnInit, OnDestroy {

	@ViewChild('pagination') pagination:PaginationComponent;

	fabActionSubscriber:Subscription;

	// Initialize the sort properties.
	sortProperties = {
		imeNumber: [
			"imeNumber"
		],
		monitoringPoint: [
			"monitoringPoint.constantName"
		],
		discoveryDate: [
			"imeData.0.dateTime"
		],
		initialReading: [
			"imeData.0.methaneLevel",
			"imeNumber"
		],
		// TODO add latest reading
	};

	filters:{text:string, site:number} = {
		text: "",
		site: -1
	};

	siteFilterChoices:any[] = [
		{
			ordinal: -1,
			name: "Any"
		},
		...Site.values().filter(site => site.active)
	];

	showSideInfo:boolean = false;
	selectedImeNumber:ImeNumber;

	constructor(
		private router:Router,
		private activatedRoute:ActivatedRoute,
		private imeNumberService:ImeNumberService,
		private dialog:MdDialog,
		private snackBar:MdSnackBar,
		private navigationService:NavigationService) {
			super();
			navigationService.getNavbarComponent().expanded = true;
			navigationService.getSideinfoComponent().setDirective(ImeNumberListSideinfoComponent, {imeNumber: null});
			navigationService.getSideinfoComponent().enable();
	}

	ngOnInit() {
		// Load data.
		this.loadImeNumbers();
	}

	ngOnDestroy() {
		this.navigationService.getSideinfoComponent().disable();
	}

	loadImeNumbers() {
		this.imeNumberService.getAllVerified((data) => {
			console.log(data);
			this.data = data;
			for (let imeNumber of this.data) {
				imeNumber.site = EnumUtils.convertToEnum(Site, imeNumber.site);
				for (let i = 0; i < imeNumber.monitoringPoints.length; i++) {
					imeNumber.monitoringPoints[i] = EnumUtils.convertToEnum(MonitoringPoint, imeNumber.monitoringPoints[i]);
				}
				imeNumber.status = EnumUtils.convertToEnum(ExceedanceStatus, imeNumber.status);
			}
			this.applyFilters();
			this.paginfo.totalRows = this.data.length;
			this.navigationService.getSideinfoComponent().open();
			this.showSideInfo = true;
			this.isDataLoaded = true;
		})
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
				textMatch = search.test(o.imeNumber);
			}
			let siteMatch:boolean = true;
			if (this.filters.site >= 0) {
				siteMatch = o.site && (o.site.ordinal == this.filters.site);
			}
			return textMatch && siteMatch;
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

	selectImeNumber(imeNumber:ImeNumber) {
		if (!this.selectedImeNumber) {
			this.navigationService.getSideinfoComponent().open();
			this.showSideInfo = true;
		}
		this.selectedImeNumber = imeNumber;
		this.navigationService.getSideinfoComponent().subtitle = this.selectedImeNumber.imeNumber; 
		this.navigationService.getSideinfoComponent().getDirective().setData(this.selectedImeNumber);
	}

	deselectImeNumber() {
		this.selectedImeNumber = null;
		this.navigationService.getSideinfoComponent().subtitle = ""; 
		this.navigationService.getSideinfoComponent().getDirective().setData(null);
	}

	navigateToImeNumber(imeNumber:ImeNumber) {
		this.router.navigate([imeNumber.imeNumber], {relativeTo: this.activatedRoute});
	}
	
	isNavDrawerOpen():boolean {
		return this.navigationService.isNavDrawerOpened();
	}

	listGrids(imeNumber:ImeNumber):string {
		return this.imeNumberService.listGrids(imeNumber);
	}

}