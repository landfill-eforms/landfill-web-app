import { InstrumentType } from './../../../../model/server/persistence/entity/instrument/instrument-type.class';
import { InstrumentTypeService } from './../../../../services/instrument/instrument-type.service';
import { Component, OnInit } from '@angular/core';
import { MdDialog, MdDialogRef } from "@angular/material";

@Component({
	selector: 'app-instrument-type-dialog',
	templateUrl: './instrument-type-dialog.component.html'
})
export class InstrumentTypeDialogComponent implements OnInit {

	instrumentType:InstrumentType;

	constructor(
		public dialogRef:MdDialogRef<InstrumentTypeDialogComponent>,
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
		this.instrumentTypeService.create(this.instrumentType,
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