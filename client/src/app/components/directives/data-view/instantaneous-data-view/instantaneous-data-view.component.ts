import { DateTimeUtils } from './../../../../utils/date-time.utils';
import { Sort, SortUtils } from './../../../../utils/sort.utils';
import { StringUtils } from './../../../../utils/string.utils';
import { InstantaneousData } from './../../../../model/server/persistence/entity/serviceemission/instantaneous/instantaneous-data.class';
import { Component, OnInit, Input, OnChanges } from '@angular/core';

@Component({
	selector: 'app-instantaneous-data-view',
	templateUrl: './instantaneous-data-view.component.html',
	styleUrls: ['./instantaneous-data-view.component.scss']
})
export class InstantaneousDataViewComponent implements OnChanges {

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
		inspector: [
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
			"methaneLevel"
		]
	}

	ngOnChanges() {
		if (this.data) {
			this.sortBy("date");
		}
	}

	sortBy(sortBy:string) {
		SortUtils.sortAndUpdate(this.sort, sortBy, this.data, this.sortProperties[sortBy]);
	}

	getStatus(reading:number):string {
        reading /= 100;
        if (reading > 500) {
            return "hotspot";
        }
        else if (reading > 300) {
            return "warmspot"
        }
        return "";
    }

}