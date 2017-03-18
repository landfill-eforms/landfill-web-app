import { Sort, SortUtils } from './../../../utils/sort.utils';
import { InstrumentTypeService } from './../../../services/instrument/instrument-type.service';
import { InstrumentType } from './../../../model/server/persistence/entity/instrument/instrument-type.class';
import { MdSnackBar } from '@angular/material';
import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'app-instrument-type',
    templateUrl: './instrument-type.component.html',
    styleUrls: ['./instrument-type.component.scss']
})
export class InstrumentTypeComponent implements OnInit {

	isDataLoaded:boolean;
	instrumentTypeId:string;
	instrumentType:InstrumentType = new InstrumentType();

	sort:Sort = {
		current: "id",
		reversed: false
	}

	sortProperties:any = {
        serialNumber: [
            "serialNumber"
        ],
        instrumentStatus: [
            "instrumentStatus",
            "serialNumber"
        ]
	}

	constructor(
		private instrumentTypeService:InstrumentTypeService,
		private activatedRoute:ActivatedRoute,
		private snackBar:MdSnackBar,
	) {}
	
	ngOnInit() {
		this.instrumentTypeId = this.activatedRoute.params['_value']['id'];
		console.log(this.instrumentTypeId);
		this.instrumentTypeService.getById(this.instrumentTypeId,
			(data) => {
				console.log(data);
				this.instrumentType = data;
				this.isDataLoaded = true;
			}
		);
	}

	save() {
		this.instrumentTypeService.update(this.instrumentType,
			(data) => {
				console.log(data);
				this.snackBar.open("Equipment type saved.", "OK", {duration: 2000});
			}
		);
	}

	sortBy(sortBy:string) {
		SortUtils.sortAndUpdate(this.sort, sortBy, this.instrumentType.instruments, this.sortProperties[sortBy]);
	}

}