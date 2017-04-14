import { IseNumber } from './../../../model/server/persistence/entity/integrated/ise-number.class';
import { IseNumberService } from './../../../services/integrated/ise-number.service';
import { IntegratedDataService } from './../../../services/integrated/integrated-data.service';
import { StringUtils } from './../../../utils/string.utils';
import { DateTimeUtils } from './../../../utils/date-time.utils';
import { ImeNumberService } from './../../../services/instantaneous/ime-number.service';
import { ImeNumber } from './../../../model/server/persistence/entity/instantaneous/ime-number.class';
import { IntegratedData } from './../../../model/server/persistence/entity/integrated/integrated-data.class';
import { InstantaneousData } from './../../../model/server/persistence/entity/instantaneous/instantaneous-data.class';
import { InstantaneousDataService } from './../../../services/instantaneous/instantaneous-data.service';
import { Site } from './../../../model/server/persistence/enums/site.enum';
import { Component } from '@angular/core';

@Component({
	selector: 'app-report-selector',
	templateUrl: './report-selector.component.html',
	styleUrls: ['./report-selector.component.scss']
})
export class ReportsComponent {

	dateTimeUtils = DateTimeUtils;
	stringUtils = StringUtils;

	reportType:number = 0;

	exceedanceType:number = 0;
	exceedanceTypes:{value:number, label:string}[] =  [
		{value: 0, label:"All"},
		{value: 1, label:"IME"},
		{value: 2, label:"ISE"},
		{value: 3, label:"Probe"},
	];

	location:Site;
	locations:Site[] = Site.values().filter(site => site.active);

	dateRange:any = {
		start: -1,
		end: -1
	}

	dataLoaded:number = 0;

	instantaneousData:InstantaneousData[] = [];
	integratedData:IntegratedData[] = [];
	exceedanceData:{date:string, landfill:string, type:string, number:string, grid:string, repairDesc:string, init:number, recheck:number}[] = [];

	constructor(private instantaneousDataService:InstantaneousDataService, 
		private integratedDataService:IntegratedDataService,
		private imeNumberService:ImeNumberService,
		private iseNumberService:IseNumberService) {

	}

	test() {
		if (this.reportType == 1) {
			this.instantaneousDataService.getBySiteAndDate(this.location.name.toUpperCase(), this.dateRange.start, this.dateRange.end, (data) => {
				this.instantaneousData = data;
				this.dataLoaded = 1;
				console.log(this.instantaneousData);
			});
		}
		if (this.reportType == 3) {
			this.integratedDataService.getBySiteAndDate(this.location.name.toUpperCase(), this.dateRange.start, this.dateRange.end, (data) => {
				this.integratedData = data;
				this.dataLoaded = 3;
				console.log(this.integratedDataService);
			});
		}
		if (this.reportType == 5) {
			if (this.exceedanceType < 2) {
				this.imeNumberService.getBySite(this.location, (data) => {
					let imeNumbers:ImeNumber[] = data;
					console.log(imeNumbers);
					imeNumbers.filter(i => {
						if (this.dateRange.start > 0 && this.dateToDateCode(this.dateRange.start) > i.dateCode) {
							return false;
						}
						if (this.dateRange.end > 0 && this.dateToDateCode(this.dateRange.end) < i.dateCode) {
							return false;
						}
						return true;
					})
					.forEach(i => {this.exceedanceData.push({
							date: DateTimeUtils.getDate(i.imeData[0].dateTime),
							landfill: this.location.name,
							type: "IME",
							number: i.imeNumber,
							grid: <any>i.instantaneousData[0].monitoringPoint,
							repairDesc: "",
							init: i.imeData[0].methaneLevel,
							recheck: 0
						}
					)});
					this.dataLoaded = 5;
					console.log(this.exceedanceData);
				});
			}
			if (this.exceedanceType == 0 || this.exceedanceType == 2) {
				this.iseNumberService.getBySite(this.location, (data) => {
					let iseNumbers:IseNumber[] = data;
					console.log(iseNumbers);
					iseNumbers.filter(i => {
						if (this.dateRange.start > 0 && this.dateToDateCode(this.dateRange.start) > i.dateCode) {
							return false;
						}
						if (this.dateRange.end > 0 && this.dateToDateCode(this.dateRange.end) < i.dateCode) {
							return false;
						}
						return true;
					})
					.forEach(i => {this.exceedanceData.push({
							date: DateTimeUtils.getDate(i.iseData[0].dateTime),
							landfill: this.location.name,
							type: "ISE",
							number: i.iseNumber,
							grid: "?",
							repairDesc: "",
							init: i.iseData[0].methaneLevel,
							recheck: 0
						}
					)});
					this.dataLoaded = 5;
					console.log(this.exceedanceData);
				});
			}
		}
	}

	onStartDateChange(event) {
		this.dateRange.start = event.target.valueAsNumber || -1;
	}

	onEndDateChange(event) {
		this.dateRange.end = event.target.valueAsNumber || -1;
	}

	private dateToDateCode(date:number):number {
		let d:Date = new Date(date);
		return (d.getFullYear() - 2000) * 100 + d.getMonth();
	}

}