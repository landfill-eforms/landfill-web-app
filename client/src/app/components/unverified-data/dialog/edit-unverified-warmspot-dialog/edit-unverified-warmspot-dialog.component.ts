import { UnverifiedWarmspotData } from './../../../../model/server/persistence/entity/unverified/unverified-warmspot-data.class';
import { MdDialogRef, MdSnackBar } from '@angular/material';
import { Instrument } from './../../../../model/server/persistence/entity/instrument/instrument.class';
import { Component, OnInit } from '@angular/core';


@Component({
	selector: 'app-edit-unverified-warmspot-dialog',
	templateUrl: './edit-unverified-warmspot-dialog.component.html'
})
export class EditUnverifiedWarmspotDialogComponent implements OnInit {

	data:UnverifiedWarmspotData;
	availableInstruments:Instrument[] = [];

	fields:{
		instrumentId?:number, 
		methaneLevel?:number, 
		size?:string, 
		description?:string,
		grid?:string
	} = {}

	constructor(
		public dialogRef:MdDialogRef<EditUnverifiedWarmspotDialogComponent>,
		private snackBar:MdSnackBar
	) {}

	ngOnInit() {
		this.fields.instrumentId = this.data.instrument ? this.data.instrument.id : null;
		this.fields.methaneLevel = this.data.methaneLevel / 100;
		this.fields.size = this.data.size;
		this.fields.description = this.data.description;
		this.availableInstruments = this.availableInstruments.filter(i => i.instrumentType.instantaneous);
		this.fields.grid = this.data.monitoringPoint.name;
	} 

	confirm() {
		this.dialogRef.close(this.fields);
	}

	cancel() {
		this.dialogRef.close();
	}

	canSubmit(): boolean{
		return this.fields.grid != null && this.fields.instrumentId != null && this.fields.methaneLevel != null && !!this.fields.size;
	}
}