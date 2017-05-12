import { IseNumberListSideinfoComponent } from './../ise-number-list-sideinfo/ise-number-list-sideinfo.component';
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
import { IseNumberService } from './../../../services/integrated/ise-number.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Sort, SortUtils } from './../../../utils/sort.utils';
import { IseNumber } from './../../../model/server/persistence/entity/surfaceemission/integrated/ise-number.class';
import { Subscription } from 'rxjs/Subscription';
import { PaginationComponent, Paginfo } from './../../directives/pagination/pagination.component';
import { Component, OnInit, ViewChild, OnDestroy } from '@angular/core';

@Component({
	selector: 'app-ise-number-list',
	templateUrl: './ise-number-list.component.html'
})
export class IseNumberListComponent extends AbstractDataTableComponent<IseNumber> implements OnInit, OnDestroy {

	// Utilities
	DateTimeUtils = DateTimeUtils;

	@ViewChild('pagination') pagination:PaginationComponent;

	fabActionSubscriber:Subscription;

	// Initialize the sort properties.
	sortProperties = {
		iseNumber: [
			"iseNumber"
		],
		monitoringPoint: [
			"monitoringPoint.constantName"
		],
		discoveryDate: [
			"iseData.0.dateTime"
		],
		initialReading: [
			"iseData.0.methaneLevel",
			"iseNumber"
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
	selectedIseNumber:IseNumber;

	constructor(
		private router:Router,
		private activatedRoute:ActivatedRoute,
		private iseNumberService:IseNumberService,
		private dialog:MdDialog,
		private snackBar:MdSnackBar,
		private navigationService:NavigationService) {
			super();
			navigationService.getNavbarComponent().expanded = true;
			navigationService.getSideinfoComponent().setDirective(IseNumberListSideinfoComponent, {iseNumber: null});
			navigationService.getSideinfoComponent().enable();
	}

	ngOnInit() {
		// Load data.
		this.loadIseNumbers();
	}

	ngOnDestroy() {
		this.navigationService.getSideinfoComponent().disable();
	}

	loadIseNumbers() {
		this.iseNumberService.getAllVerified((data) => {
			console.log(data);
			this.data = data;
			for (let iseNumber of this.data) {
				iseNumber.site = EnumUtils.convertToEnum(Site, iseNumber.site);
				iseNumber.monitoringPoint = EnumUtils.convertToEnum(MonitoringPoint, iseNumber.monitoringPoint);
				iseNumber.status = EnumUtils.convertToEnum(ExceedanceStatus, iseNumber.status);
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
				textMatch = search.test(o.iseNumber);
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

	selectIseNumber(iseNumber:IseNumber) {
		if (!this.selectedIseNumber) {
			this.navigationService.getSideinfoComponent().open();
			this.showSideInfo = true;
		}
		this.selectedIseNumber = iseNumber;
		this.navigationService.getSideinfoComponent().subtitle = this.selectedIseNumber.iseNumber; 
		this.navigationService.getSideinfoComponent().getDirective().setData(this.selectedIseNumber);
	}

	deselectIseNumber() {
		this.selectedIseNumber = null;
		this.navigationService.getSideinfoComponent().subtitle = ""; 
		this.navigationService.getSideinfoComponent().getDirective().setData(null);
	}

	navigateToIseNumber(iseNumber:IseNumber) {
		this.router.navigate([iseNumber.iseNumber], {relativeTo: this.activatedRoute});
	}
	
	isNavDrawerOpen():boolean {
		return this.navigationService.isNavDrawerOpened();
	}

}