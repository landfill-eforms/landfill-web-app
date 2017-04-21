import { ImeNumber } from './../../../model/server/persistence/entity/serviceemission/instantaneous/ime-number.class';
import { ImeNumberService } from './../../../services/instantaneous/ime-number.service';
import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'app-ime-report',
    templateUrl: './ime-report.component.html',
    styleUrls: ['./ime-report.component.scss']
})
export class ImeReportComponent implements OnInit {

    imeNumbers:ImeNumber[] = [];
	isDataLoaded:boolean = false;

	constructor(
		private imeNumberService:ImeNumberService
	) {}

	ngOnInit() {
        
		this.imeNumberService.getAll((data) => {
			console.log(data);
			this.imeNumbers = data;
			this.isDataLoaded = true;
		});

	}
    
}