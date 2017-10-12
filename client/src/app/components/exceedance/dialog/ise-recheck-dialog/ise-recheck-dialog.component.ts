import { StringUtils } from './../../../../utils/string.utils';
import { MdSnackBar } from '@angular/material';
import { DateTimeUtils } from './../../../../utils/date-time.utils';
import { User } from './../../../../model/server/persistence/entity/user/user.class';
import { MdDialogRef } from '@angular/material';
import { IseData } from './../../../../model/server/persistence/entity/surfaceemission/integrated/ise-data.class';
import { Component, OnInit } from '@angular/core';

@Component({
	selector: 'app-ise-recheck-dialog',
	templateUrl: './ise-recheck-dialog.component.html'
})
export class IseRecheckDialogComponent implements OnInit {
	
	StringUtils = StringUtils;

	data: IseData;
	originalData: IseData;
	users: User[] = [];

	minDateTime: number;
	maxDateTime: number;
	dateTime: {date: number, time: number} = {
		date: 0,
		time: 0
	}

	constructor(
		private snackBar: MdSnackBar,
		public dialogRef: MdDialogRef<IseRecheckDialogComponent>) {

	}

	ngOnInit() {
		let defaultDateTime: number = new Date().getTime();
		this.data = new IseData();
		if (this.originalData) {
			this.data.dateTime = this.originalData.dateTime;
			this.data.description = this.originalData.description;
			this.data.methaneLevel = this.originalData.methaneLevel / 100;
			this.data.inspector = this.originalData.inspector ? this._findUserById(this.originalData.inspector.id) : null;
			defaultDateTime = this.originalData.dateTime;
		}
		else {
			if (this.minDateTime != null && defaultDateTime < this.minDateTime) {
				defaultDateTime = this.minDateTime;
			}
			if (this.maxDateTime != null && defaultDateTime > this.maxDateTime) {
				defaultDateTime = this.maxDateTime;
			}
			this.data.iseRepairData = [];
		}
		this.dateTime.date = defaultDateTime;
		this.dateTime.time = defaultDateTime;
	}

	dateChanged(event) {
		this.dateTime.date = event;
	}

	timeChanged(event) {
		this.dateTime.time = event;
	}

	submit() {
		this.data.dateTime = DateTimeUtils.mergeDateTime(this.dateTime.date, this.dateTime.time);
		if (this.minDateTime != null && this.data.dateTime < this.minDateTime) {
			this.snackBar.open("Date/time cannot be before the previous repair or recheck.", "OK", {duration: 5000});
			return;
		}
		else if (this.maxDateTime != null && this.data.dateTime > this.maxDateTime) {
			this.snackBar.open("Date/time cannot be after the next repair or recheck.", "OK", {duration: 5000});
			return;
		}
		if (!this.originalData) {
			this.data.methaneLevel *= 100;
			this.dialogRef.close(this.data);
		}
		else {
			this.originalData.dateTime = this.data.dateTime;
			this.originalData.description = this.data.description;
			this.originalData.methaneLevel = this.data.methaneLevel * 100;
			this.dialogRef.close();
		}
	}

	cancel() {
		this.dialogRef.close();
	}

	canSubmit(): boolean {
		return !!this.data.inspector && this.data.methaneLevel != null;
	}

	private _findUserById(id: number): User {
		for (let user of this.users) {
			if (user.id == id) {
				return user;
			}
		}
		return null;
	}

}