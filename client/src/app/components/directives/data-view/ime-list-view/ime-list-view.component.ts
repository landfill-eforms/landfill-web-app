import { DateTimeUtils } from './../../../../utils/date-time.utils';
import { Sort, SortUtils } from './../../../../utils/sort.utils';
import { IMENumber } from './../../../../model/server/persistence/entity/instantaneous/ime-number.class';
import { Component, OnInit, Input, OnChanges } from '@angular/core';


@Component({
	selector: 'app-ime-list-view',
	templateUrl: './ime-list-view.component.html',
	styleUrls: ['./ime-list-view.component.scss']
})
export class IMEListComponent implements OnChanges {

	@Input() data:IMENumber[] = [];

	@Input() edit:boolean = false;

	dateTimeUtils = DateTimeUtils;

	sort:Sort = {
		current: "",
		reversed: false
	}

	sortProperties:any = {
		date: [
			"discoveryDate"
		],
		ime: [
			"imeNumber"
		],
		initMethaneLevel: [
			"imeData.0.imeNumber"
		],
		lastMethaneLevel: [
			"imeData.0.imeNumber" // Fix this
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