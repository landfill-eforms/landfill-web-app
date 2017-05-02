import { Month } from './../../../../model/client/enums/month.enum';
import { DayOfWeek } from './../../../../model/client/enums/day-of-week.enum';
import { Component, Output, EventEmitter, OnInit, Input, OnChanges } from '@angular/core';

@Component({
	selector: 'app-date-picker',
	templateUrl: './date-picker.component.html',
	styleUrls: ['./date-picker.component.scss']
})
export class DatePickerComponent implements OnChanges {

	@Input() width:string;
	@Input() date:number;

	@Output() dateChanged = new EventEmitter<number>();

	readonly daysOfWeek:DayOfWeek[] = DayOfWeek.values();
	readonly months:Month[] = Month.values();

	readonly today:DayMonthYear;

	dateIsSelected:boolean = false;
	selectedDate:DayMonthYear = {
		year: 0,
		month: 0,
		date: 1,
		dayOfWeek: 0
	};

	currCalendar:Calendar;
	destCalendar:Calendar;
	daysInMonth:number;

	transforming:boolean = false;

	constructor() {
		let date = new Date();
		this.today = {
			year: date.getFullYear(),
			month: date.getMonth(),
			date: date.getDate(),
			dayOfWeek: date.getDay()
		}
	}

	ngOnChanges() {
		this.dateIsSelected = this.date ? true : false;
		let date:Date = this.date == null ? new Date() : new Date(this.date);
		this.selectedDate.year = date.getFullYear();
		this.selectedDate.month = date.getMonth();
		this.selectedDate.date = date.getDate();
		this.selectedDate.dayOfWeek = date.getDay();
		console.log(this.selectedDate);
		this.navigate(this.selectedDate.month, this.selectedDate.year);
	}

	selectDate(date:number, month:number, year:number) {
		if (this.transforming) {
			return;
		}
		this.selectedDate.date = date;
		this.selectedDate.month = month;
		this.selectedDate.year = year;
		this.emitDateChange();
	}

	isSelected(date:number, month:number, year:number):boolean {
		return this.dateIsSelected && this.selectedDate.date == date && this.selectedDate.month == month && this.selectedDate.year == year;
	}

	isToday(date:number, month:number, year:number) {
		return this.today.year == year && this.today.month == month && this.today.date == date;
	}

	navigateToNextYear() {
		if (this.transforming) {
			return;
		}
		this.navigate(this.currCalendar.month, this.currCalendar.year + 1);
	}

	navigateToPrevYear() {
		if (this.transforming) {
			return;
		}
		this.navigate(this.currCalendar.month, this.currCalendar.year - 1);
	}

	navigateToNextMonth() {
		if (this.transforming) {
			return;
		}
		let month:number = this.currCalendar.month;
		let year:number = this.currCalendar.year;
		if (month == 11) {
			month = 0;
			year += 1;
		}
		else {
			month += 1;
		}
		this.navigate(month, year);
	}

	navigateToPrevMonth() {
		if (this.transforming) {
			return;
		}
		let month:number = this.currCalendar.month;
		let year:number = this.currCalendar.year;
		if (month == 0) {
			month = 11;
			year -= 1;
		}
		else {
			month -= 1;
		}
		this.navigate(month, year);
	}

	private emitDateChange() {
		let date:Date = new Date(this.selectedDate.year, this.selectedDate.month, this.selectedDate.date);
		console.log("Date Changed:", date);
		this.dateChanged.emit(date.getTime());
	}

	private navigate(month:number, year:number) {

		if (this.currCalendar && this.currCalendar.year == year && this.currCalendar.month == month) {
			return;
		}

		let newCalendar = this.generateCalendar(year, month);

		if (this.currCalendar == null) {
			this.currCalendar = newCalendar;
			return;
		}
		
		if (newCalendar.year * 100 + newCalendar.month < this.currCalendar.year * 100 + this.currCalendar.month) {
			newCalendar.transform = -200;
		}

		this.destCalendar = newCalendar;

		console.log("Starting transform");
		this.transforming = true;
		setTimeout(() => {
			if (this.destCalendar.transform < 0) {
				this.destCalendar.transform = -100;
				this.currCalendar.transform = 100;
			}
			else {
				this.destCalendar.transform = -100;
				this.currCalendar.transform = -100;
			}
			setTimeout(() => {
				this.transforming = false;
				this.currCalendar = this.destCalendar;
				this.currCalendar.transform = 0;
				this.destCalendar = null;
				console.log("Finished transform");
			}, 500);
		}, 10);

	}

	private generateCalendar(year:number, month:number):Calendar {

		// Initialize calendar.
		let calendar:Calendar = {
			year: year,
			month: month,
			daysInMonth: this.getDaysInMonth(year, month),
			calendar: [],
			transform: 0
		};

		// Get first day of week of month.
		let temp:Date = new Date(year, month, 1);
		let offsetDaysCount:number = temp.getDay();

		// Generate the calendar.
		let dayOfMonth:number = 1;
		for (let w = 0; w < 6; w++) {
			let week:Day[] = [];
			for (let d = 0; d < 7; d++) {
				if ((w == 0 && d < offsetDaysCount) || dayOfMonth > calendar.daysInMonth) {
					week.push({date: -1, hidden: true});
					continue;
				}
				week.push({date: dayOfMonth++, hidden: false});
			}
			calendar.calendar.push(week);
		}

		return calendar;
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

class Calendar {
	year:number;
	month:number;
	daysInMonth:number;
	calendar:Day[][] = [];
	transform:number;
}

class DayMonthYear {
	date:number;
	month:number;
	year:number;
	dayOfWeek:number;
}