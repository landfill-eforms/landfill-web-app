import { ImeRepairData } from './../../../../model/server/persistence/entity/serviceemission/instantaneous/ime-repair-data.class';
import { User } from './../../../../model/server/persistence/entity/user/user.class';
import { MdDialogRef } from '@angular/material';
import { Component, OnChanges } from '@angular/core';

@Component({
	selector: 'app-ime-repair-dialog',
	templateUrl: './ime-repair-dialog.component.html',
	styleUrls: ['./ime-repair-dialog.component.scss']
})
export class ImeRepairDialogComponent {

	data:ImeRepairData = new ImeRepairData();

	constructor(public dialogRef:MdDialogRef<ImeRepairDialogComponent>) {

	}

	submit() {
		if (!this.data.dateTime) {
			this.data.dateTime = new Date().getTime();
		}
		
		// TODO Do this properly
		this.data.user = <any>{id:1};
		if (!this.data.soil) {
			this.data.soil = false;
		}
		if (!this.data.water) {
			this.data.water = false;
		}
		this.dialogRef.close(this.data);
	}

	cancel() {
		this.dialogRef.close();
	}

}