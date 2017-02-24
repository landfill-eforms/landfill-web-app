import { DateTimeUtils } from './../../../../utils/date-time.utils';
import { Sort, SortUtils } from './../../../../utils/sort.utils';
import { StringUtils } from './../../../../utils/string.utils';
import { InstantaneousData } from './../../../../model/server/persistence/entity/instantaneous/instantaneous-data.class';
import { Component, OnInit, Input } from '@angular/core';

@Component({
	selector: 'app-instantaneous-data-view',
	templateUrl: './instantaneous-data-view.component.html',
	styleUrls: ['./instantaneous-data-view.component.scss']
})
export class InstantaneousDataViewComponent {

	@Input() data:InstantaneousData[] = [];

	stringUtils = StringUtils;
	dateTimeUtils = DateTimeUtils;

	sort:Sort = {
		current: "",
		reversed: false
	}

	sortProperties:any = {
		date: [
			"startTime"
		],
		user: [
			"inspector.lastname",
			"inspector.firstname",
			"startTime"
		],
		grid: [
			"monitoringPoint.site",
			"monitoringPoint.name"
		],
		ime: [
			"imeNumber.imeNumber",
			"monitoringPoint.name"
		],
		methaneLevel: [

		]
	}

	sortBy(sortBy:string) {
		SortUtils.sortAndUpdate(this.sort, sortBy, this.data, this.sortProperties[sortBy]);
	}

}