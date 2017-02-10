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

	ngOnChanges() {

	}

}