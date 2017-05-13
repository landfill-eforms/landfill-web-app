import { MdDialogRef, MdSnackBar } from '@angular/material';
import { Instrument } from './../../../../model/server/persistence/entity/instrument/instrument.class';
import { UnverifiedIntegratedData } from './../../../../model/server/persistence/entity/unverified/unverified-integrated-data.class';
import { Component, OnInit } from '@angular/core';


@Component({
	selector: 'app-edit-unverified-integrated-dialog',
	templateUrl: './edit-unverified-integrated-dialog.component.html'
})
export class EditUnverifiedIntegratedDialogComponent implements OnInit {

	data:UnverifiedIntegratedData;
	availableInstruments:Instrument[] = [];

	fields:{instrumentId?:number, barometricPressure?:number, methaneLevel?:number, bagNumber?:number, volume?:number} = {}

	constructor(
		public dialogRef:MdDialogRef<EditUnverifiedIntegratedDialogComponent>,
		private snackBar:MdSnackBar
	) {}

	ngOnInit() {
		this.fields.instrumentId = this.data.instrument ? this.data.instrument.id : null;
		this.fields.barometricPressure = this.data.barometricPressure / 100;
		this.fields.methaneLevel = this.data.methaneLevel / 100;
		this.fields.bagNumber = this.data.bagNumber;
		this.fields.volume = this.data.volume;
		this.availableInstruments = this.availableInstruments.filter(i => i.instrumentType.instantaneous);
	}

	confirm() {
		this.dialogRef.close(this.fields);
	}

	cancel() {
		this.dialogRef.close();
	}

}