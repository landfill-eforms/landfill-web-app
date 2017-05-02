import { Site } from './../../../../model/server/persistence/enums/location/site.enum';
import { InstrumentStatus } from './../../../../model/server/persistence/enums/instrument/instrument-status.enum';
import { InstrumentType } from './../../../../model/server/persistence/entity/instrument/instrument-type.class';
import { Instrument } from './../../../../model/server/persistence/entity/instrument/instrument.class';
import { InstrumentService } from './../../../../services/instrument/instrument.service';
import { Component, OnInit } from '@angular/core';
import { MdDialog, MdDialogRef, MdSnackBar } from "@angular/material";

@Component({
	selector: 'app-instrument-dialog',
	templateUrl: './instrument-dialog.component.html'
})
export class InstrumentDialogComponent implements OnInit {

	isNew:boolean;
	instrument:Instrument;
	instrumentTypes:InstrumentType[] = [];
	instrumentStatus:InstrumentStatus[] = InstrumentStatus.values();
	sites:Site[] = Site.values();

	serviceDueDateString:string;
	lastServiceDateString:string;
	purchaseDateString:string;

	constructor(
		public dialogRef:MdDialogRef<InstrumentDialogComponent>,
		private snackBar:MdSnackBar,
		private instrumentService:InstrumentService
	) {}

	ngOnInit() {
		if (!this.instrument) {
			this.instrument = new Instrument();
			this.instrument.instrumentType = new InstrumentType();
		}
		else {
			// Clone instrument.
			let clone:Instrument = new Instrument();
			for (let key of Object.keys(this.instrument)) {
				clone[key] = this.instrument[key];
			}
			if (!clone.instrumentType) {
				clone.instrumentType = new InstrumentType();
			}
			this.instrument = clone;
		}
		console.log(this.instrument)
	}

	confirm() {
		// TODO Perform data verification before saving.
		if (this.isNew) {
			this.instrumentService.create(this.instrument,
				(data) => {
					console.log(data);
					this.dialogRef.close(data);
				},
				(err) => {
					this.snackBar.open(JSON.parse(err.text()).message, "OK", {duration: 5000});
				}
			);
		}
		else {
			this.instrumentService.update(this.instrument,
				(data) => {
					console.log(data);
					this.dialogRef.close(data);
				},
				(err) => {
					this.snackBar.open(JSON.parse(err.text()).message, "OK", {duration: 5000});
				}
			);
		}
	}

	updateDate(property:string, date:number) {
		this.instrument[property] = date;
	}

	cancel() {
		this.dialogRef.close();
	}
	
}