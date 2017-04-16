import { Site } from './../../../../model/server/persistence/enums/location/site.enum';
import { InstrumentStatus } from './../../../../model/server/persistence/enums/instrument/instrument-status.enum';
import { InstrumentType } from './../../../../model/server/persistence/entity/instrument/instrument-type.class';
import { Instrument } from './../../../../model/server/persistence/entity/instrument/instrument.class';
import { InstrumentService } from './../../../../services/instrument/instrument.service';
import { Component, OnInit } from '@angular/core';
import { MdDialog, MdDialogRef } from "@angular/material";

@Component({
	selector: 'app-new-instrument-dialog',
	templateUrl: './new-instrument-dialog.component.html',
	styleUrls: ['./new-instrument-dialog.component.scss']
})
export class NewInstrumentDialogComponent implements OnInit {

	instrument:Instrument;
	instrumentTypes:InstrumentType[] = [];
	instrumentStatus:InstrumentStatus[] = InstrumentStatus.values();
	sites:Site[] = Site.values();

	constructor(
		public dialogRef:MdDialogRef<NewInstrumentDialogComponent>,
		private instrumentService:InstrumentService
	) {}

	ngOnInit() {
		this.instrument = new Instrument();
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

	cancel() {
		this.dialogRef.close();
	}
	
}