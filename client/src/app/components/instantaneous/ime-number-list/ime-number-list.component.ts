import { IMENumber } from './../../../model/server/persistence/entity/instantaneous/ime-number.class';
import { IMENumberService } from './../../../services/instantaneous/ime-number.service';
import { Component, OnInit } from '@angular/core';


@Component({
    selector: 'app-ime-number-list',
    templateUrl: './ime-number-list.component.html',
    styleUrls: ['./ime-number-list.component.scss']
})
export class IMENumberListComponent implements OnInit {

	imeNumbers:IMENumber[] = [];
	isDataLoaded:boolean = false;

	constructor(
		private imeNumberService:IMENumberService
	) {}

	ngOnInit() {

		this.imeNumberService.getAll((data) => {
			console.log(data);
			this.imeNumbers = data;
			this.isDataLoaded = true;
		});

	}

}