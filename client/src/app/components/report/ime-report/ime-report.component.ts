import { IMENumber } from './../../../model/server/persistence/entity/instantaneous/ime-number.class';
import { IMENumberService } from './../../../services/instantaneous/ime-number.service';
import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'app-ime-report',
    templateUrl: './ime-report.component.html',
    styleUrls: ['./ime-report.component.scss']
})
export class ImeReportComponent implements OnInit {

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