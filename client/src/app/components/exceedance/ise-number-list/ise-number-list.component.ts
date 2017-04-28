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

	@ViewChild('pagination') pagination:PaginationComponent;

	fabActionSubscriber:Subscription;

	showSideInfo:boolean = false;
	selectedData:IseNumber;

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
	}


	constructor(
		private router:Router,
		private activatedRoute:ActivatedRoute,
		private iseNumberService:IseNumberService,
		private dialog:MdDialog,
		private snackBar:MdSnackBar,
		private navigationService:NavigationService) {
			super();
			navigationService.getNavbarComponent().expanded = true;
			navigationService.getSideinfoComponent().setDirective(null, {iseNumber: null});
	}

	ngOnInit() {
		// Load data.
		this.loadIseNumbers();
	}

	ngOnDestroy() {
		this.navigationService.getSideinfoComponent().disable();
	}

	loadIseNumbers() {
		this.iseNumberService.getAll((data) => {
			console.log(data);
			this.data = data;
			this.applyFilters();
			this.isDataLoaded = true;
		})
	}

	applyFilters() {

	}

	resetFilters() {
		this.filters.text = "";
		this.applyFilters();
	}

}