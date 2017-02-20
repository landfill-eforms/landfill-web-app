import { IMENumberStatus } from './../../../model/server/model/ime-number-status.enum';
import { Site } from './../../../model/server/model/site.enum';
import { IMENumber } from './../../../model/server/persistence/entity/instantaneous/ime-number.class';
import { IMENumberService } from './../../../services/ime-number.service';
import { UnverifiedInstantaneousData } from './../../../model/server/persistence/entity/unverified/unverified-instantaneous-data.class';
import { MdDialogRef } from '@angular/material';
import { Component, OnInit } from '@angular/core';


@Component({
	selector: 'app-assign-ime-number-dialog',
	templateUrl: './assign-ime-number-dialog.component.html',
	styleUrls: ['./assign-ime-number-dialog.component.scss']
})
export class AssignIMENumberDialogComponent {

	site:Site;
	data:UnverifiedInstantaneousData;
	existingIMENumbers:IMENumber[];
	createdIMENumbers:IMENumber[]; // IME numbers created during this session.
	action:any = {
		useExisting: null,
		newSeries: null,
		newDate: null,
		newImeNumberString: null
	}

	constructor(
		public dialogRef:MdDialogRef<AssignIMENumberDialogComponent>,
		private imeNumberService:IMENumberService
	) {}

	createImeNumber() {
		let imeNumber:IMENumber = new IMENumber();
		imeNumber.site = <any>this.site.constantName;
		imeNumber.discoveryDate = this.action.newDate;
		imeNumber.sequence = this.action.newSeries;
		imeNumber.status = <any>IMENumberStatus.UNVERIFIED.constantName;
		imeNumber.imeNumber = this.action.newImeNumberString;
		this.imeNumberService.create((data) => {
			imeNumber.id = data;
			this.data.imeNumber = imeNumber;
			this.dialogRef.close(data);
		}, imeNumber);
		// this.createdIMENumbers.push(imeNumber);
	}

	addToImeNumber(imeNumber:IMENumber) {
		this.data.imeNumber = imeNumber;
		this.dialogRef.close(imeNumber);
	}

	onDateChange(event) {
		console.log("date changed");
		this.action.newDate = event.target.valueAsNumber  + 1000 * 60 * 60 * 24; // Date picker is off by one day.
		if (this.action.newDate) {
			this.action.newSeries = this.findMaxIMESeries(this.action.newDate) + 1;
			this.action.newImeNumberString = this.imeNumberService.getStringFromImeNumber(<any>{
				site: this.site, 
				discoveryDate: this.action.newDate, 
				sequence: this.action.newSeries
			});
		}
		else {
			this.action.newSeries = null;
		}
	}

	resetNewDate() {
		this.action.newSeries = null;
		this.action.newDate = null;
		this.action.newImeNumberString = null;
	}

	cancel() {
		this.dialogRef.close();
	}

	private findMaxIMESeries(date:number):number {
		let max:number = 0;
		for (let i = 0; i < this.existingIMENumbers.length; i++) {
			let imeNumber:IMENumber = this.existingIMENumbers[i];
			if (imeNumber.discoveryDate == date && imeNumber.sequence > max) {
				max = imeNumber.sequence;
			}
		}
		// for (let i = 0; i < this.createdIMENumbers.length; i++) {
		// 	let imeNumber:IMENumber = this.createdIMENumbers[i];
		// 	if (imeNumber.discoveryDate == date && imeNumber.sequence > max) {
		// 		max = imeNumber.sequence;
		// 	}
		// }
		return max;
	}
	

}