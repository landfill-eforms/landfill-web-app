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

	constructor(
		private instrumentTypeService:InstrumentTypeService,
		private activatedRoute:ActivatedRoute,
		private snackBar:MdSnackBar,
	) {}
	
	ngOnInit() {
		this.instrumentTypeId = this.activatedRoute.params['_value']['id'];
		console.log(this.instrumentTypeId);
		this.instrumentTypeService.getById((data) => {
			console.log(data);
			this.instrumentType = data;
			this.isDataLoaded = true;
		}, this.instrumentTypeId);
	}

	save() {
		this.instrumentTypeService.update((data) => {
			console.log(data);
			this.snackBar.open("Equipment type saved.", "OK", {duration: 2000});
		}, this.instrumentType);
	}

}