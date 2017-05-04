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

	fields:{instrumentId?:number, barometricPressure?:number, methaneLevel?:number} = {}

	constructor(
		public dialogRef:MdDialogRef<EditUnverifiedInstantaneousDialogComponent>,
		private snackBar:MdSnackBar
	) {}

	ngOnInit() {
		this.fields.instrumentId = this.data.instrument ? this.data.instrument.id : null;
		this.fields.barometricPressure = this.data.barometricPressure / 100;
		this.fields.methaneLevel = this.data.methaneLevel / 100;
		this.availableInstruments = this.availableInstruments.filter(i => i.instrumentType.instantaneous);
	}

	confirm() {
		this.dialogRef.close(this.fields);
	}

	cancel() {
		this.dialogRef.close();
	}

}