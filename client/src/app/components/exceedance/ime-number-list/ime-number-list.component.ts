import { ImeNumberListSideinfoComponent } from './../ime-number-list-sideinfo/ime-number-list-sideinfo.component';
import { ImeNumberService } from './../../../services/instantaneous/ime-number.service';
import { ImeNumber } from './../../../model/server/persistence/entity/surfaceemission/instantaneous/ime-number.class';
import { DateTimeUtils } from './../../../utils/date-time.utils';
import { MonitoringPoint } from './../../../model/server/persistence/enums/location/monitoring-point.enum';
import { Site } from './../../../model/server/persistence/enums/location/site.enum';
import { EnumUtils } from './../../../utils/enum.utils';
import { AbstractDataTableComponent } from './../../../model/client/abstract-components/abstract-data-table.component';
import { InputStatus } from './../../../utils/input.utils';
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

	// Utilities
	DateTimeUtils = DateTimeUtils;

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

	filters:{text:string} = {
		text: ""
	};

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
	}

	ngOnInit() {
		// Load data.
		this.loadImeNumbers();
	}

	ngOnDestroy() {
		this.navigationService.getSideinfoComponent().disable();
	}

	loadImeNumbers() {
		this.imeNumberService.getAll((data) => {
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
	}

	navigateToImeNumber(imeNumber:ImeNumber) {
		this.router.navigate([imeNumber.imeNumber], {relativeTo: this.activatedRoute});
	}

}