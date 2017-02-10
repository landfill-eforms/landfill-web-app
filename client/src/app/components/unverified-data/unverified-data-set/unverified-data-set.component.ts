import { DateTimeUtils } from './../../../utils/date-time.utils';
import { StringUtils } from './../../../utils/string.utils';
import { EnumUtils } from './../../../utils/enum.utils';
import { Site } from './../../../model/server/model/site.enum';
import { MonitoringPoint } from './../../../model/server/model/monitoring-point.enum';
import { MdSnackBar } from '@angular/material';
import { UnverifiedDataService } from './../../../services/unverified-data-set.service';
import { ActivatedRoute } from '@angular/router';
import { UnverifiedDataSet } from './../../../model/server/persistence/entity/unverified/unverified-data-set.class';
import { Component, OnInit } from '@angular/core';

@Component({
	selector: 'app-unverified-data-set',
	templateUrl: './unverified-data-set.component.html',
	styleUrls: ['./unverified-data-set.component.scss']
})
export class UnverifiedDataSetComponent implements OnInit {

	StringUtils = StringUtils;
	DateTimeUtils = DateTimeUtils;

	isDataLoaded:boolean = false;
	dataSetId:string;
	dataSet:UnverifiedDataSet;
	sort:any = {
		current: "",
		reversed: false
	}

	constructor(
		private activatedRoute:ActivatedRoute,
		private unverifiedDataService:UnverifiedDataService,
		private snackBar:MdSnackBar,
	) {}

	ngOnInit() {
		this.dataSetId = this.activatedRoute.params['_value']['id'];
		this.unverifiedDataService.getById((data) => {
			console.log(data);
			data["site"] = EnumUtils.convertToEnum(Site, data["site"]);
			for (let j = 0; j < data.unverifiedInstantaneousData.length; j++) {
				data.unverifiedInstantaneousData[j]["monitoringPoint"] = EnumUtils.convertToEnum(MonitoringPoint, data.unverifiedInstantaneousData[j]["monitoringPoint"]);
			}
			this.dataSet = this.unverifiedDataService.checkForErrors(data);
			this.isDataLoaded = true;
			this.sortByGrid();
		}, this.dataSetId);
	}

	sortByGrid() {
		if (this.sort.current === "grid") {
			this.sort.reversed = !this.sort.reversed;
		}
		else {
			this.sort.current = "grid";
			this.sort.reversed = false;
		}
		this.dataSet.unverifiedInstantaneousData.sort((a, b) => {
			let compareGrid = (a.monitoringPoint.ordinal - b.monitoringPoint.ordinal) * (this.sort.reversed ? -1 : 1)
			if (compareGrid != 0) {
				return compareGrid;
			}
			return (a.startTime - b.startTime) * (this.sort.reversed ? -1 : 1);
		});
	}

	sortByIme() {
		if (this.sort.current === "ime") {
			this.sort.reversed = !this.sort.reversed;
		}
		else {
			this.sort.current = "ime";
			this.sort.reversed = false;
		}
		this.dataSet.unverifiedInstantaneousData.sort((a, b) => {
			if (a.imeNumber && !b.imeNumber) {
				return 1 * (this.sort.reversed ? -1 : 1);
			}
			else if (b.imeNumber && !a.imeNumber) {
				return -1 * (this.sort.reversed ? -1 : 1);
			}
			else if (!a.imeNumber && !b.imeNumber) {
				var compareGrid = 0;
			}
			else {
				var compareGrid = this.stringSortFunction(a.imeNumber.imeNumber, b.imeNumber.imeNumber, this.sort.reversed);
			}
			if (compareGrid != 0) {
				return compareGrid;
			}
			return this.stringSortFunction(a.monitoringPoint.name, b.monitoringPoint.name, this.sort.reversed);
		});
	}

	sortByMethaneLevel() {
		if (this.sort.current === "methaneLevel") {
			this.sort.reversed = !this.sort.reversed;
		}
		else {
			this.sort.current = "methaneLevel";
			this.sort.reversed = false;
		}
		this.dataSet.unverifiedInstantaneousData.sort((a, b) => (a.methaneLevel - b.methaneLevel) * (this.sort.reversed ? -1 : 1));
	}

	// TODO Move this to a util class.
	private stringSortFunction(a:string, b:string, reversed:boolean):number {
		if (a > b) return reversed ? -1 : 1;
		if (a == b) return 0;
		if (a < b) return reversed ? 1 : -1;
	}

}