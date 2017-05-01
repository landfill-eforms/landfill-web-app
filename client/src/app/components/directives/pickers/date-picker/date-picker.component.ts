import { Component, Output, EventEmitter, OnInit, Input, OnChanges } from '@angular/core';

@Component({
	selector: 'app-date-picker',
	templateUrl: './date-picker.component.html',
	styleUrls: ['./date-picker.component.scss']
})
export class DatePickerComponent implements OnChanges {

	@Input() date:number;

	@Output() dateChanged = new EventEmitter<number>();

	currDate:{year:number, month:number, date:number} = {
		year: 0,
		month: 0,
		date: 1
	};

	calendar:Day[][] = [];
	daysInMonth:number;

	constructor() {

	}

	ngOnChanges() {
		let date:Date = this.date == null ? new Date() : new Date(this.date);
		this.currDate.year = date.getFullYear();
		this.currDate.month = date.getMonth();
		this.currDate.date = date.getDate();
		console.log(this.currDate);
		this.navigate();
	}

	selectDate(date:number) {
		this.currDate.date = date;
		this.emitDateChange();
	}

	navigateToYear(year:number) {
		this.currDate.year = year;
		this.navigate();
	}

	navigateToMonth(month:number) {
		this.currDate.month = month;
		this.navigate();
	}

	private emitDateChange() {
		let date:Date = new Date(this.currDate.year, this.currDate.month, this.currDate.date);
		console.log("Date Changed:", date);
		this.dateChanged.emit(date.getTime());
	}

	private navigate() {

		// Check if the previously selected date would be out of bounds in the new month/year.
		this.daysInMonth = this.getDaysInMonth(this.currDate.year, this.currDate.month);
		if (this.currDate.date > this.daysInMonth) {
			this.currDate.date = this.daysInMonth;
		}

		// Get first day of week of month.
		let temp:Date = new Date(this.currDate.year, this.currDate.month, 1);
		let offsetDaysCount:number = temp.getDay();

		// Regenerate the calendar.
		this.calendar = [];
		let dayOfMonth:number = 1;
		for (let w = 0; w < 6; w++) {
			let week:Day[] = [];
			for (let d = 0; d < 7; d++) {
				if ((w == 0 && d < offsetDaysCount) || dayOfMonth > this.daysInMonth) {
					week.push({date: -1, hidden: true});
					continue;
				}
				week.push({date: dayOfMonth++, hidden: false});
			}
			this.calendar.push(week);
		}

		// Broadcast date change.
		this.emitDateChange();
	}

	private getDaysInMonth(year:number, month:number) {
		if (month == 1) {
			if (year % 4 != 0) {
				return 28;
			}
			if (year % 100 != 0) {
				return 29;
			}
			if (year % 400 != 0) {
				return 28;
			}
			return 29;
		}
		if (month == 0 || month == 2 || month == 4 || month == 6 || month == 7 || month == 9 || month == 11) {
			return 31;
		}
		return 30;
	}

}

class Day {
	date:number;
	hidden:boolean;
}