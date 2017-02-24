import { DateTimeUtils } from './../../../../utils/date-time.utils';
import { Sort, SortUtils } from './../../../../utils/sort.utils';
import { IMENumber } from './../../../../model/server/persistence/entity/instantaneous/ime-number.class';
import { Component, OnInit, Input, OnChanges } from '@angular/core';


@Component({
	selector: 'app-ime-list-view',
	templateUrl: './ime-list-view.component.html',
	styleUrls: ['./ime-list-view.component.scss']
})
export class IMEListViewComponent implements OnChanges {

	@Input() data:IMENumber[] = [];

	@Input() edit:boolean = false;

	dateTimeUtils = DateTimeUtils;

	sort:Sort = {
		current: "",
		reversed: false
	}

	sortProperties:any = {
		date: [
			"imeData.0.dateTime"
		],
		ime: [
			"imeNumber"
		],
		initMethaneLevel: [
			"imeData.0.methaneLevel"
		],
		lastMethaneLevel: [
			"imeData.0.methaneLevel" // Fix this
		]
	}

	ngOnChanges() {
		if (this.data) {
			this.sortBy("ime");
		}
	}

	sortBy(sortBy:string) {
		SortUtils.sortAndUpdate(this.sort, sortBy, this.data, this.sortProperties[sortBy]);
	}

}