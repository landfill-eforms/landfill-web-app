import { DateTimeUtils } from './../../../utils/date-time.utils';
import { MonitoringPoint } from './../../../model/server/persistence/enums/location/monitoring-point.enum';
import { Site } from './../../../model/server/persistence/enums/location/site.enum';
import { EnumUtils } from './../../../utils/enum.utils';
import { AbstractDataTableComponent } from './../../../model/client/abstract-components/abstract-data-table.component';
import { InputStatus } from './../../../utils/input.utils';
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

	filters:{text:string} = {
		text: ""
	};

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
			// navigationService.getSideinfoComponent().setDirective(null, {iseNumber: null});
			// navigationService.getSideinfoComponent().enable();
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
			this.applyFilters();
			this.paginfo.totalRows = this.data.length;
			this.navigationService.getSideinfoComponent().open();
			this.isDataLoaded = true;
		})
	}

	applyFilters() {

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
	}

	navigateToIseNumber(iseNumber:IseNumber) {
		this.router.navigate([iseNumber.iseNumber], {relativeTo: this.activatedRoute});
	}

}