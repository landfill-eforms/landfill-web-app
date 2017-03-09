import { MdSnackBar } from '@angular/material';
import { InstrumentService } from './../../../services/instrument/instrument.service';
import { ActivatedRoute } from '@angular/router';
import { Instrument } from './../../../model/server/persistence/entity/instrument/instrument.class';
import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'app-instrument',
    templateUrl: './instrument.component.html',
    styleUrls: ['./instrument.component.scss']
})
export class InstrumentComponent implements OnInit {

	isDataLoaded:boolean;
	instrumentId:string;
	instrument:Instrument = new Instrument();

	constructor(
		private instrumentService:InstrumentService,
		private activatedRoute:ActivatedRoute,
		private snackBar:MdSnackBar,
	) {}
	
	ngOnInit() {
		this.instrumentId = this.activatedRoute.params['_value']['id'];
		console.log(this.instrumentId);
		this.instrumentService.getById((data) => {
			console.log(data);
			this.instrument = data;
			this.isDataLoaded = true;
		}, this.instrumentId);
	}

	save() {
		this.instrumentService.update((data) => {
			console.log(data);
			this.snackBar.open("Equipment type saved.", "OK", {duration: 2000});
		}, this.instrument);
	}
	
}