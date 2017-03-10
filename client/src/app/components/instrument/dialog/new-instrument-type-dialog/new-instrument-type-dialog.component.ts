import { InstrumentType } from './../../../../model/server/persistence/entity/instrument/instrument-type.class';
import { InstrumentTypeService } from './../../../../services/instrument/instrument-type.service';
import { Component, OnInit } from '@angular/core';
import { MdDialog, MdDialogRef } from "@angular/material";

@Component({
	selector: 'app-new-instrument-type-dialog',
	templateUrl: './new-instrument-type-dialog.component.html',
	styleUrls: ['./new-instrument-type-dialog.component.scss']
})
export class NewInstrumentTypeDialogComponent implements OnInit {

	instrumentType:InstrumentType;

	constructor(
		public dialogRef:MdDialogRef<NewInstrumentTypeDialogComponent>,
		private instrumentTypeService:InstrumentTypeService
	) {}

	ngOnInit() {
		this.instrumentType = new InstrumentType();

		// TODO Move this to back-end.
		this.instrumentType.instantaneous = false;
		this.instrumentType.probe = false;
		this.instrumentType.methanePercent = false;
		this.instrumentType.methanePpm = false;
		this.instrumentType.hydrogenSulfidePpm = false;
		this.instrumentType.oxygenPercent = false;
		this.instrumentType.carbonDioxidePercent = false;
		this.instrumentType.nitrogenPercent = false;
		this.instrumentType.pressure = false;
	}

	confirm() {
		// TODO Perform data verification before saving.
		this.instrumentTypeService.create((data) => {
			console.log(data);
			this.dialogRef.close(data);
		}, this.instrumentType);
	}

	cancel() {
		this.dialogRef.close();
	}
	
}