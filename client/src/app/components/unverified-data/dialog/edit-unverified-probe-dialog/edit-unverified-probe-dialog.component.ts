import { UserGroup } from './../../../../model/server/persistence/entity/user/user-group.class';
import { StringUtils } from './../../../../utils/string.utils';
import { User } from './../../../../model/server/persistence/entity/user/user.class';
import { MdDialogRef, MdSnackBar } from '@angular/material';
import { Instrument } from './../../../../model/server/persistence/entity/instrument/instrument.class';
import { UnverifiedProbeData } from './../../../../model/server/persistence/entity/unverified/unverified-probe-data.class';
import { Component, OnInit } from '@angular/core';


@Component({
	selector: 'app-edit-unverified-probe-dialog',
	templateUrl: './edit-unverified-probe-dialog.component.html'
})
export class EditUnverifiedProbeDialogComponent implements OnInit {
	StringUtils = StringUtils;
	data:UnverifiedProbeData;
	availableInstruments:Instrument[] = [];
	availableInspectors:User[] = [];
	inspectorsWrapped:{inspector:User, selected?:boolean}[] = [];

	fields:{description?:string, barometricPressure?:number, methaneLevel?:number, instrumentId?:number, inspectorIds?:number[]} = {}

	constructor(
		public dialogRef:MdDialogRef<EditUnverifiedProbeDialogComponent>,
		private snackBar:MdSnackBar
	) {}

	ngOnInit() {
		this.fields.instrumentId = this.data.instrument ? this.data.instrument.id : null;
		//this.fields.inspectorId = this.data.inspectors ? this.data.inspectors.id : null;
		this.fields.barometricPressure = this.data.barometricPressure / 100;
		this.fields.methaneLevel = this.data.methaneLevel / 100;
		this.fields.description = this.data.description;
		for (let inspector of this.availableInspectors) {
			this.inspectorsWrapped.push({inspector: inspector});
		}
		for (let inspector of this.data.inspectors) {
			for(let inspectorWrapped of this.inspectorsWrapped) {
				if (inspectorWrapped.inspector.id == inspector.id) {
					inspectorWrapped.selected = true;
				}
			}
		}
	}

	confirm() {
		this.fields.inspectorIds = [];
		for (let inspectorsWrapped of this.inspectorsWrapped) {
			if (inspectorsWrapped.selected) {
				this.fields.inspectorIds.push(inspectorsWrapped.inspector.id);
			}
		}
		this.dialogRef.close(this.fields);
	}

	cancel() {
		this.dialogRef.close();
	}

}