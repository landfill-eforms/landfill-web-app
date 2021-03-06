import { DateTimeUtils } from './../../../../utils/date-time.utils';
import { Component, Input, EventEmitter, Output, OnChanges } from '@angular/core';

@Component({
	selector: 'app-date-picker-input',
	templateUrl: './date-picker-input.component.html'
})
export class DatePickerInputComponent implements OnChanges {

	@Input() disabled:boolean;
	@Input() placeholder:string;
	@Input() initialDate:number;

	@Output() dateChanged = new EventEmitter<number>();

	dateString:string;
	actualDate:number;

	ngOnChanges() {
		this.actualDateChanged(this.initialDate);
	}

	checkInput() {
		if (!this.dateString) {
			this.actualDate = 0;
			return;
		}
		let d = Date.parse(this.dateString);
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
		if (this.actualDate === date) {
			return;
		}
		this.actualDate = date;
		this.dateString = DateTimeUtils.getDate(this.actualDate);
		this.dateChanged.emit(!this.actualDate ? null : this.actualDate);
	}
	
}