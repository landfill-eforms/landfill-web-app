import { ImeNumberStatus } from './../../../model/server/persistence/enums/ime-number-status.enum';
import { Site } from './../../../model/server/persistence/enums/site.enum';
import { ImeNumber } from './../../../model/server/persistence/entity/instantaneous/ime-number.class';
import { ImeNumberService } from './../../../services/instantaneous/ime-number.service';
import { UnverifiedInstantaneousData } from './../../../model/server/persistence/entity/unverified/unverified-instantaneous-data.class';
import { MdDialogRef } from '@angular/material';
import { Component, OnInit } from '@angular/core';

@Component({
	selector: 'app-assign-ime-number-dialog',
	templateUrl: './assign-ime-number-dialog.component.html',
	styleUrls: ['./assign-ime-number-dialog.component.scss']
})
export class AssignImeNumberDialogComponent {

	site:Site;
	data:UnverifiedInstantaneousData;
	existingImeNumbers:ImeNumber[];
	createdImeNumbers:ImeNumber[]; // IME numbers created during this session.
	action:any = {
		useExisting: null,
		newSeries: null,
		newDate: null,
		newImeNumberString: null
	}

	constructor(
		public dialogRef:MdDialogRef<AssignImeNumberDialogComponent>,
		private imeNumberService:ImeNumberService
	) {}

	createImeNumber() {
		let imeNumber:ImeNumber = new ImeNumber();
		imeNumber.site = <any>this.site.constantName;
		imeNumber.dateCode = Number(this.action.newImeNumberString.substring(3, 7));  // TODO Make this better.
		imeNumber.sequence = this.action.newSeries;
		imeNumber.status = <any>ImeNumberStatus.UNVERIFIED.constantName;
		imeNumber.imeNumber = this.action.newImeNumberString;
		this.imeNumberService.create((data) => {
			imeNumber.id = data;
			this.data.imeNumbers.push(imeNumber);
			this.dialogRef.close(data);
		}, imeNumber);
		// this.createdImeNumbers.push(imeNumber);
	}

	addToImeNumber(imeNumber:ImeNumber) {
		this.data.imeNumbers.push(imeNumber);
		this.dialogRef.close(imeNumber);
	}

	onDateChange(event) {
		console.log("date changed");
		this.action.newDate = event.target.valueAsNumber  + 1000 * 60 * 60 * 24; // Date picker is off by one day.
		if (this.action.newDate) {
			this.action.newSeries = this.findMaxIMESeries(this.action.newDate) + 1;
			this.action.newImeNumberString = this.imeNumberService.getStringFromImeNumber(<any>{
				site: this.site, 
				dateCode: this.action.newDate, 
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
		for (let i = 0; i < this.existingImeNumbers.length; i++) {
			let imeNumber:ImeNumber = this.existingImeNumbers[i];
			if (imeNumber.dateCode == date && imeNumber.sequence > max) {
				max = imeNumber.sequence;
			}
		}
		// for (let i = 0; i < this.createdImeNumbers.length; i++) {
		// 	let imeNumber:ImeNumber = this.createdImeNumbers[i];
		// 	if (imeNumber.dateCode == date && imeNumber.sequence > max) {
		// 		max = imeNumber.sequence;
		// 	}
		// }
		return max;
	}
	

}