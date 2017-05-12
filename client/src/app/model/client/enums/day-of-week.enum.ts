export class DayOfWeek {

	static readonly SUNDAY:DayOfWeek = {
		ordinal: 0,
		initial: 'S',
		shortName: "Sun",
		name: "Sunday"
	};

	static readonly MONDAY:DayOfWeek = {
		ordinal: 1,
		initial: 'M',
		shortName: "Mon",
		name: "Monday"
	};

	static readonly TUESDAY:DayOfWeek = {
		ordinal: 2,
		initial: 'T',
		shortName: "Tue",
		name: "Tuesday"
	};

	static readonly WEDNESDAY:DayOfWeek = {
		ordinal: 3,
		initial: 'W',
		shortName: "Wed",
		name: "Wednesday"
	};

	static readonly THURSDAY:DayOfWeek = {
		ordinal: 4,
		initial: 'T',
		shortName: "Thu",
		name: "Thursday"
	};

	static readonly FRIDAY:DayOfWeek = {
		ordinal: 5,
		initial: 'F',
		shortName: "Fri",
		name: "Friday"
	};

	static readonly SATURDAY:DayOfWeek = {
		ordinal: 6,
		initial: 'S',
		shortName: "Sat",
		name: "Saturday"
	};

	readonly ordinal:number;
	readonly initial:string;
	readonly shortName:string;
	readonly name:string;

	static values():DayOfWeek[] {
		return [
			DayOfWeek.SUNDAY,
			DayOfWeek.MONDAY,
			DayOfWeek.TUESDAY,
			DayOfWeek.WEDNESDAY,
			DayOfWeek.THURSDAY,
			DayOfWeek.FRIDAY,
			DayOfWeek.SATURDAY
		];
	}

}