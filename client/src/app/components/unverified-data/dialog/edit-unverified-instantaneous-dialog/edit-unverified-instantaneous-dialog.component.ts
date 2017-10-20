import { ImeNumberService } from './../../../../services/instantaneous/ime-number.service';
import { ImeNumber } from './../../../../model/server/persistence/entity/surfaceemission/instantaneous/ime-number.class';
import { MdDialogRef, MdSnackBar } from '@angular/material';
import { Instrument } from './../../../../model/server/persistence/entity/instrument/instrument.class';
import { UnverifiedInstantaneousData } from './../../../../model/server/persistence/entity/unverified/unverified-instantaneous-data.class';
import { Component, OnInit } from '@angular/core';


@Component({
	selector: 'app-edit-unverified-instantaneous-dialog',
	templateUrl: './edit-unverified-instantaneous-dialog.component.html'
})
export class EditUnverifiedInstantaneousDialogComponent implements OnInit {

	data:UnverifiedInstantaneousData;
	availableInstruments:Instrument[] = [];
	availableImeNumbers:ImeNumber[];
	imeNumbersLoaded:boolean;

	// Object containing the fields taht can be edited by this dialog.
	fields:{
		instrumentId?:number,
		barometricPressure?:number,
		methaneLevel?:number,
		startTime?:number,
		endTime?:number,
		imeNumbers?:ImeNumber[],
		grid?:string
	} = {}

	constructor(
		public dialogRef:MdDialogRef<EditUnverifiedInstantaneousDialogComponent>,
		private snackBar:MdSnackBar,
		private imeNumberService:ImeNumberService) {

	}

	ngOnInit() {

		// Get field values from the instantaneous data. 
		// We make a copy of the values so that the original object is not modified, in case the user wanys to cancel the edit.
		this.fields.instrumentId = this.data.instrument ? this.data.instrument.id : null;
		this.fields.barometricPressure = this.data.barometricPressure / 100;
		this.fields.methaneLevel = this.data.methaneLevel / 100;
		this.fields.startTime = this.data.startTime;
		this.fields.endTime = this.data.endTime;
		this.availableInstruments = this.availableInstruments.filter(i => i.instrumentType.instantaneous);
		this.fields.grid = this.data.monitoringPoint.name;

		// Load list of available IME numbers based on the instantaneous data's site and date.
		this.imeNumberService.getBySiteAndDateCode(this.data.monitoringPoint.site, this.imeNumberService.generateDateCodeFromLong(this.data.startTime),
			(data) => {

				// Find which IME numbers already belong to the instantaneous data, and mark them as selected.
				for (let imeNumber of data) {
					if (this.data.imeNumbers.filter(i => i.imeNumber == imeNumber.imeNumber).length > 0) {
						imeNumber["selected"] = true;
					}
				}

				// Sort list of IME numbers by the sequence number.
				this.availableImeNumbers = data.sort((a, b) => a.sequence - b.sequence);
			}
		);
	}

	/** Updates the start time. */
	updateStartTime(event) {
		this.fields.startTime = event;
	}

	/** Updates the end time. */
	updateEndTime(event) {
		this.fields.endTime = event;
	}

	/** Passes the field data back to the parent component, and closes the dialog. */
	confirm() {
		this.fields.imeNumbers = this.availableImeNumbers.filter(i => i["selected"]); // Include the selected IME numbers with the rest of field data.
		// this.fields.methaneLevel = this.fields.methaneLevel;
		this.dialogRef.close(this.fields);
	}

	/** Closes the dialog without passing any data back. */
	cancel() {
		this.dialogRef.close();
	}

	canSubmit(): boolean {
		return this.fields.methaneLevel != null && this.fields.barometricPressure != null && this.fields.instrumentId != null;
	}

	canAddIME(): boolean {
		return this.fields.methaneLevel <= 500;
	}
}