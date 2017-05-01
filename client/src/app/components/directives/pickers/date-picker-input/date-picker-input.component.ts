import { DateTimeUtils } from './../../../../utils/date-time.utils';
import { Component, Input, EventEmitter, Output, OnChanges } from '@angular/core';

@Component({
	selector: 'app-date-picker-input',
	templateUrl: './date-picker-input.component.html',
	styleUrls: ['./date-picker-input.component.scss']
})
export class DatePickerInputComponent implements OnChanges {

	@Input() placeholder:string;
	@Input() initialDate:number;

	@Output() dateChanged = new EventEmitter<number>();

	dateString:string;
	actualDate:number;

	ngOnChanges() {
		this.actualDateChanged(this.initialDate);
	}

	checkInput() {
		let d = Date.parse(this.dateString);
		console.log(this.dateString, d);
		if (!isNaN(d)) {
			this.actualDateChanged(d);
		}
		else {
			this.dateString = DateTimeUtils.getDate(this.actualDate);
		}
	}

	datePickerChanged(event) {
		this.actualDateChanged(event);
	}

	private actualDateChanged(date:number) {
		if (this.actualDate == date) {
			return;
		}
		this.actualDate = date;
		this.dateString = DateTimeUtils.getDate(this.actualDate);
		this.dateChanged.emit(this.actualDate);
	}
	
}