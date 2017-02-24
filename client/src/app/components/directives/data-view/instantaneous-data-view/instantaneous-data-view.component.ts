import { Sort } from './../../../../utils/sort.utils';
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
	sort:Sort = {
		current: "",
		reversed: false
	}

}