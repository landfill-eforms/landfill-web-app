import { MdSnackBar } from '@angular/material';
import { DateTimeUtils } from './../../../../utils/date-time.utils';
import { ImeRepairData } from './../../../../model/server/persistence/entity/surfaceemission/instantaneous/ime-repair-data.class';
import { User } from './../../../../model/server/persistence/entity/user/user.class';
import { MdDialogRef } from '@angular/material';
import { Component, OnChanges, OnInit } from '@angular/core';

@Component({
	selector: 'app-ime-repair-dialog',
	templateUrl: './ime-repair-dialog.component.html'
})
export class ImeRepairDialogComponent implements OnInit {

	data:ImeRepairData = new ImeRepairData();
	originalData:ImeRepairData;

	minDateTime:number;
	maxDateTime:number;
	dateTime:{date:number, time:number} = {
		date: 0,
		time: 0
	}

	constructor(private snackBar:MdSnackBar,
		public dialogRef:MdDialogRef<ImeRepairDialogComponent>) {

	}

	ngOnInit() {
		let defaultDateTime:number = new Date().getTime();
		this.data = new ImeRepairData();
		if (this.originalData) {
			for (let key of Object.keys(this.originalData)) {
				this.data[key] = this.originalData[key];
			}
			defaultDateTime = this.originalData.dateTime;
		}
		else {
			if (this.minDateTime != null && defaultDateTime < this.minDateTime) {
				defaultDateTime = this.minDateTime;
			}
			if (this.maxDateTime != null && defaultDateTime > this.maxDateTime) {
				defaultDateTime = this.maxDateTime;
			}
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
			this.snackBar.open("Date/time cannot be before the current recheck.", "OK", {duration: 5000});
			return;
		}
		else if (this.maxDateTime != null && this.data.dateTime > this.maxDateTime) {
			this.snackBar.open("Date/time cannot be after the next recheck.", "OK", {duration: 5000});
			return;
		}
		if (!this.originalData) {
			this.data.soil = !!this.data.soil;
			this.data.water = !!this.data.water;
			this.dialogRef.close(this.data);
		}
		else {
			this.originalData.dateTime = this.data.dateTime;
			this.originalData.description = this.data.description;
			this.originalData.crew = this.data.crew
			this.originalData.water = this.data.water;
			this.originalData.soil = this.data.soil;
			this.dialogRef.close();
		}
	}

	cancel() {
		this.dialogRef.close();
	}

}