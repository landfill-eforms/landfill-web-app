import { User } from './../../../../model/server/persistence/entity/user/user.class';
import { MdDialogRef } from '@angular/material';
import { IMEData } from './../../../../model/server/persistence/entity/instantaneous/ime-data.class';
import { Component } from '@angular/core';

@Component({
	selector: 'app-ime-recheck-dialog',
	templateUrl: './ime-recheck-dialog.component.html',
	styleUrls: ['./ime-recheck-dialog.component.scss']
})
export class IMERecheckDialogComponent {

	data:IMEData = new IMEData();
	users:User[] = [];

	constructor(public dialogRef:MdDialogRef<IMERecheckDialogComponent>) {

	}

	submit() {
		if (!this.data.dateTime) {
			this.data.dateTime = new Date().getTime();
		}
		this.dialogRef.close(this.data);
	}

	cancel() {
		this.dialogRef.close();
	}

}