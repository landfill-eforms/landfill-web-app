import { IMERepairData } from './../../../../model/server/persistence/entity/instantaneous/ime-repair-data.class';
import { User } from './../../../../model/server/persistence/entity/user/user.class';
import { MdDialogRef } from '@angular/material';
import { Component, OnChanges } from '@angular/core';

@Component({
	selector: 'app-ime-repair-dialog',
	templateUrl: './ime-repair-dialog.component.html',
	styleUrls: ['./ime-repair-dialog.component.scss']
})
export class IMERepairDialogComponent {

	data:IMERepairData = new IMERepairData();

	constructor(public dialogRef:MdDialogRef<IMERepairDialogComponent>) {

	}

	submit() {
		if (!this.data.dateTime) {
			this.data.dateTime = new Date().getTime();
		}
		
		// TODO Do this properly
		this.data.user = <any>{id:1};

		this.dialogRef.close(this.data);
	}

	cancel() {
		this.dialogRef.close();
	}

}