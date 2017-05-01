import { Site } from './../../../../model/server/persistence/enums/location/site.enum';
import { InstrumentStatus } from './../../../../model/server/persistence/enums/instrument/instrument-status.enum';
import { InstrumentType } from './../../../../model/server/persistence/entity/instrument/instrument-type.class';
import { Instrument } from './../../../../model/server/persistence/entity/instrument/instrument.class';
import { InstrumentService } from './../../../../services/instrument/instrument.service';
import { Component, OnInit } from '@angular/core';
import { MdDialog, MdDialogRef } from "@angular/material";

@Component({
	selector: 'app-instrument-dialog',
	templateUrl: './instrument-dialog.component.html'
})
export class InstrumentDialogComponent implements OnInit {

	instrument:Instrument;
	instrumentTypes:InstrumentType[] = [];
	instrumentStatus:InstrumentStatus[] = InstrumentStatus.values();
	sites:Site[] = Site.values();

	serviceDueDateString:string;
	lastServiceDateString:string;
	purchaseDateString:string;

	constructor(
		public dialogRef:MdDialogRef<InstrumentDialogComponent>,
		private instrumentService:InstrumentService
	) {}

	ngOnInit() {
		if (!this.instrument) {
			this.instrument = new Instrument();
		}
	}

	confirm() {
		// TODO Perform data verification before saving.
		this.instrumentService.create(this.instrument,
			(data) => {
				console.log(data);
				this.dialogRef.close(data);
			}
		);
	}

	updateServiceDueDate(date) {
		console.log(date);
		let d = Date.parse(date);
		// this.serviceDueDateString = Date.
	}

	cancel() {
		this.dialogRef.close();
	}
	
}