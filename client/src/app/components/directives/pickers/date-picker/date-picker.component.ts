import { Component, Output, EventEmitter, OnInit, Input } from '@angular/core';

@Component({
	selector: 'app-date-picker',
	templateUrl: './date-picker.component.html',
	styleUrls: ['./date-picker.component.scss']
})
export class DatePickerComponent implements OnInit {

	@Input() defaultDate:number;

	@Output() dateChanged = new EventEmitter<Date>();

	date:{year:number, month:number, date:number} = {
		year: 0,
		month: 0,
		date: 1
	};

	calendar:Day[][] = [];
	daysInMonth:number;

	// TODO Delete this.
	currentDate:Date;

	constructor() {

	}

	ngOnInit() {
		let defaultDate:Date = this.defaultDate == null ? new Date() : new Date(this.defaultDate);
		this.date.year = defaultDate.getFullYear();
		this.date.month = defaultDate.getMonth();
		this.date.date = defaultDate.getDate();
		console.log(this.date);
		this.navigate();
	}

	selectDate(date:number) {
		this.date.date = date;
		this.emitDateChange();
	}

	navigateToYear(year:number) {
		this.date.year = year;
		this.navigate();
	}

	navigateToMonth(month:number) {
		this.date.month = month;
		this.navigate();
	}

	private emitDateChange() {
		let date:Date = new Date(this.date.year, this.date.month, this.date.date);
		console.log(date);
		this.dateChanged.emit(date);
		this.currentDate = date; // TODO Delete this.
	}

	private navigate() {

		// Check if the previously selected date would be out of bounds in the new month/year.
		this.daysInMonth = this.getDaysInMonth(this.date.year, this.date.month);
		if (this.date.date > this.daysInMonth) {
			this.date.date = this.daysInMonth;
		}

		// Get first day of week of month.
		let temp:Date = new Date(this.date.year, this.date.month, 1);
		let offsetDaysCount:number = temp.getDay();

		// Regenerate the calendar.
		this.calendar = [];
		let currDate:number = 1;
		for (let w = 0; w < 6; w++) {
			let week:Day[] = [];
			for (let d = 0; d < 7; d++) {
				if ((w == 0 && d < offsetDaysCount) || currDate > this.daysInMonth) {
					week.push({date: -1, hidden: true});
					continue;
				}
				week.push({date: currDate++, hidden: false});
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