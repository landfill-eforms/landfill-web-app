import { AuthService } from './../../../services/auth/auth.service';
import { RestrictedRoute } from './../../../routes/restricted.route';
import { AppConstant } from './../../../app.constant';
import { AbstractDataTableComponent } from './../../../model/client/abstract-components/abstract-data-table.component';
import { UnverifiedIntegratedData } from './../../../model/server/persistence/entity/unverified/unverified-integrated-data.class';
import { UnverifiedDataSetListSideinfoComponent } from './../unverified-data-set-list-sideinfo/unverified-data-set-list-sideinfo.component';
import { InputStatus, InputUtils } from './../../../utils/input.utils';
import { NavigationService } from './../../../services/app/navigation.service';
import { MdSnackBar } from '@angular/material';
import { MdDialog } from '@angular/material';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { PaginationComponent, Paginfo } from './../../directives/pagination/pagination.component';
import { Sort, SortUtils } from './../../../utils/sort.utils';
import { MonitoringPoint } from './../../../model/server/persistence/enums/location/monitoring-point.enum';
import { EnumUtils } from './../../../utils/enum.utils';
import { Site } from './../../../model/server/persistence/enums/location/site.enum';
import { DateTimeUtils } from './../../../utils/date-time.utils';
import { StringUtils } from './../../../utils/string.utils';
import { UnverifiedDataService } from './../../../services/unverified/unverified-data-set.service';
import { UnverifiedDataSet } from './../../../model/server/persistence/entity/unverified/unverified-data-set.class';
import { OnInit, Component, OnDestroy, ViewChild } from '@angular/core';


@Component({
	selector: 'app-unverified-data-set-list',
	templateUrl: './unverified-data-set-list.component.html',
	styleUrls: ['./unverified-data-set-list.component.scss']
})
export class UnverifiedDataSetListComponent extends AbstractDataTableComponent<UnverifiedDataSet> implements OnInit, OnDestroy {

	// Utilities
	StringUtils = StringUtils;
	DateTimeUtils = DateTimeUtils;

	@ViewChild('pagination') pagination:PaginationComponent;

	fabActionSubscriber:Subscription;

	isDataLoaded:boolean = false;
	loadingMessage:string = "Loading Data..."

	sortProperties:any = {
		site: [
			"site.constantName",
			"createdDate",
			"filename"
		],
		createdBy: [
			"createdBy",
			"createdDate",
			"filename"
		],
		createdDate: [
			"createdDate",
			"filename"
		],
		modifiedBy: [
			"modifiedBy",
			"modifiedDate",
			"filename"
		],
		modifiedDate: [
			"modifiedDate",
			"filename"
		]
	}

	filters:{text:string, site:number} = {
		text: "",
		site: -1
	};

	siteFilterChoices:any[] = [
		{
			ordinal: -1,
			name: "Any"
		},
		...Site.values()
	];

	showSideInfo:boolean = false;
	selectedUnverifiedDataSet:UnverifiedDataSet;

	constructor(
		private authService:AuthService,
		private router:Router,
		private activatedRoute:ActivatedRoute,
		private unverifiedDataService:UnverifiedDataService,
		private dialog:MdDialog,
		private snackBar:MdSnackBar,
		private navigationService:NavigationService) {
			super();
			navigationService.getNavbarComponent().expanded = true;
			navigationService.getSideinfoComponent().setDirective(UnverifiedDataSetListSideinfoComponent, {unverifiedDataSet: null});
			navigationService.getSideinfoComponent().enable();
	}

	ngOnInit() {
		if (this.authService.canAccess(RestrictedRoute.MOBILE_UPLOAD.data["permissions"])) {
			this.navigationService.getNavbarComponent().setFabInfo({
				icon: "file_upload",
				tooltip: "Upload"
			});
			this.fabActionSubscriber = this.navigationService
				.getNavbarComponent()
				.getFabActionSource()
				.asObservable()
				.subscribe((event) => {
					console.log(event)
					if (event instanceof MouseEvent) {
						this.router.navigate([AppConstant.RESTRICTED_ROUTE_BASE + '/' + RestrictedRoute.MOBILE_UPLOAD.path]);
					}
				});
		}
		this.loadUnverifiedDataSets();
	}

	ngOnDestroy() {
		this.navigationService.getSideinfoComponent().disable();
		if (this.fabActionSubscriber) {
			this.navigationService.getNavbarComponent().resetFabInfo();
			this.navigationService.getNavbarComponent().resetFabActionSource();
			this.fabActionSubscriber.unsubscribe();
		}
	}

	loadUnverifiedDataSets() {
		this.unverifiedDataService.getAll((data) => {
			for (let i = 0; i < data.length; i++) {
				let dataSet = data[i];
				dataSet["site"] = EnumUtils.convertToEnum(Site, dataSet["site"]);

				// Why did I have to do this again?
				for (let j = 0; j < dataSet.unverifiedInstantaneousData.length; j++) {
					dataSet.unverifiedInstantaneousData[j]["monitoringPoint"] = EnumUtils.convertToEnum(MonitoringPoint, dataSet.unverifiedInstantaneousData[j]["monitoringPoint"]);
				}
				for (let j = 0; j < dataSet.unverifiedIntegratedData.length; j++) {
					dataSet.unverifiedIntegratedData[j]["monitoringPoint"] = EnumUtils.convertToEnum(MonitoringPoint, dataSet.unverifiedIntegratedData[j]["monitoringPoint"]);
				}
				this.data.push(this.unverifiedDataService.checkForErrors(dataSet));
			}
			console.log(this.data);
			this.applyFilters();
			this.navigationService.getSideinfoComponent().open();
			this.showSideInfo = true;
			this.isDataLoaded = true;
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
				textMatch = search.test(o.filename);
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

	selectUnverifiedDataSet(unverifiedDataSet:UnverifiedDataSet) {
		if (!this.selectedUnverifiedDataSet) {
			this.navigationService.getSideinfoComponent().open();
			this.showSideInfo = true;
		}
		this.selectedUnverifiedDataSet = unverifiedDataSet;
		this.navigationService.getSideinfoComponent().subtitle = this.selectedUnverifiedDataSet.site.name + " " + DateTimeUtils.getDate(this.selectedUnverifiedDataSet.createdDate); 
		this.navigationService.getSideinfoComponent().getDirective().setData(this.selectedUnverifiedDataSet);
	}

	deselectUnverifiedDataSet() {
		this.selectedUnverifiedDataSet = null;
	}

	navigateToUnverifiedDataSet(unverifiedDataSet:UnverifiedDataSet) {
		this.router.navigate([unverifiedDataSet.id], {relativeTo: this.activatedRoute});
	}

}