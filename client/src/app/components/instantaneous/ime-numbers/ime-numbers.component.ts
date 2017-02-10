import { IMENumber } from './../../../model/server/persistence/entity/instantaneous/ime-number.class';
import { IMENumberService } from './../../../services/ime-number.service';
import { Component, OnInit } from '@angular/core';


@Component({
    selector: 'app-ime-numbers',
    templateUrl: './ime-numbers.component.html',
    styleUrls: ['./ime-numbers.component.scss']
})
export class IMENumbersComponent implements OnInit {

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