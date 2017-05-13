import { MdDialogRef, MdSnackBar } from '@angular/material';
import { Instrument } from './../../../../model/server/persistence/entity/instrument/instrument.class';
import { UnverifiedProbeData } from './../../../../model/server/persistence/entity/unverified/unverified-probe-data.class';
import { Component, OnInit } from '@angular/core';


@Component({
	selector: 'app-edit-unverified-probe-dialog',
	templateUrl: './edit-unverified-probe-dialog.component.html'
})
export class EditUnverifiedProbeDialogComponent implements OnInit {

	data:UnverifiedProbeData;
	availableInstruments:Instrument[] = [];

	fields:{description?:string, barometricPressure?:number, methaneLevel?:number} = {}

	constructor(
		public dialogRef:MdDialogRef<EditUnverifiedProbeDialogComponent>,
		private snackBar:MdSnackBar
	) {}

	ngOnInit() {
		this.fields.barometricPressure = this.data.barometricPressure / 100;
		this.fields.methaneLevel = this.data.methaneLevel / 100;
		this.fields.description = this.data.description;
	}

	confirm() {
		this.dialogRef.close(this.fields);
	}

	cancel() {
		this.dialogRef.close();
	}

}