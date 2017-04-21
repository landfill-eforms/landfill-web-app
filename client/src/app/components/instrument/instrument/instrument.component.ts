import { InstrumentTypeService } from './../../../services/instrument/instrument-type.service';
import { Site } from './../../../model/server/persistence/enums/location/site.enum';
import { InstrumentStatus } from './../../../model/server/persistence/enums/instrument/instrument-status.enum';
import { InstrumentType } from './../../../model/server/persistence/entity/instrument/instrument-type.class';
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
	instrumentTypes:InstrumentType[] = [];
	instrumentStatus:InstrumentStatus[] = InstrumentStatus.values();
	sites:Site[] = Site.values();

	constructor(
		private instrumentService:InstrumentService,
		private instrumentTypeService:InstrumentTypeService,
		private activatedRoute:ActivatedRoute,
		private snackBar:MdSnackBar,
	) {}
	
	ngOnInit() {
		this.instrumentId = this.activatedRoute.params['_value']['id'];
		console.log(this.instrumentId);
		this.instrumentService.getById(this.instrumentId,
			(data) => {
				console.log(data);
				this.instrument = data;
				//this.isDataLoaded = true;
			}
		);
		this.loadInstrumentTypes();
	}


	loadInstrumentTypes() {
        this.instrumentTypeService.getAll((data) => {
            console.log(data);
            this.instrumentTypes = data;
            this.isDataLoaded = true;
        });
    }

	save() {
		this.instrumentService.update(this.instrument,
			(data) => {
				console.log(data);
				this.snackBar.open("Equipment type saved.", "OK", {duration: 2000});
			}
		);
	}
	
}