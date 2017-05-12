import { DateTimeUtils } from './../../../../utils/date-time.utils';
import { Component, Input, EventEmitter, Output, OnChanges } from '@angular/core';

@Component({
	selector: 'app-time-picker-input',
	templateUrl: './time-picker-input.component.html'
})
export class TimePickerInputComponent implements OnChanges {

	@Input() placeholder:string;
	@Input() initialDate:number;

	@Output() dateChanged = new EventEmitter<number>();

	dateString:string;
	actualDate:number;

	
	init:boolean = false;

	ngOnChanges() {
		this.actualDateChanged(this.initialDate);
	}

	checkInput() {
		if (!this.dateString) {
			this.actualDate = 0;
			return;
		}
		let d = DateTimeUtils.parseTime(this.dateString);
		if (!isNaN(d)) {
			this.actualDateChanged(d);
		}
		else {
			this.dateString = DateTimeUtils.getTime(this.actualDate);
		}
		console.log(this.dateString, this.actualDate);
	}

	private actualDateChanged(date:number) {
		date = DateTimeUtils.filterTimeOnly(date);
		if (this.actualDate === date) {
			return;
		}
		let emit:boolean = this.actualDate != null; // Need this to avoid the "expression has changed after it was checked" exception.
		this.actualDate = date;
		this.dateString = DateTimeUtils.getTime(this.actualDate);
		if (emit) {
			this.dateChanged.emit(!this.actualDate ? null : this.actualDate);
		}
	}
	
}