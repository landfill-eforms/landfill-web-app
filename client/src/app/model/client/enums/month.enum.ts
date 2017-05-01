export class Month {

	static readonly JANUARY:Month = {
		ordinal: 0,
		shortName: "Jan",
		name: "January"
	};

	static readonly FEBRUARY:Month = {
		ordinal: 1,
		shortName: "Jan",
		name: "February"
	};

	static readonly MARCH:Month = {
		ordinal: 2,
		shortName: "Mar",
		name: "March"
	};

	static readonly APRIL:Month = {
		ordinal: 3,
		shortName: "Apr",
		name: "April"
	};

	static readonly MAY:Month = {
		ordinal: 4,
		shortName: "May",
		name: "May"
	};

	static readonly JUNE:Month = {
		ordinal: 5,
		shortName: "Jun",
		name: "June"
	};

	static readonly JULY:Month = {
		ordinal: 6,
		shortName: "Jul",
		name: "July"
	};

	static readonly AUGUST:Month = {
		ordinal: 7,
		shortName: "Aug",
		name: "August"
	};

	static readonly SEPTEMBER:Month = {
		ordinal: 8,
		shortName: "Sep",
		name: "September"
	};

	static readonly OCTOBER:Month = {
		ordinal: 9,
		shortName: "Oct",
		name: "October"
	};

	static readonly NOVEMBER:Month = {
		ordinal: 10,
		shortName: "Nov",
		name: "November"
	};

	static readonly DECEMBER:Month = {
		ordinal: 11,
		shortName: "Dec",
		name: "December"
	};

	readonly ordinal:number;
	readonly shortName:string;
	readonly name:string;

	static values():Month[] {
		return [
			Month.JANUARY,
			Month.FEBRUARY,
			Month.MARCH,
			Month.APRIL,
			Month.MAY,
			Month.JUNE,
			Month.JULY,
			Month.AUGUST,
			Month.SEPTEMBER,
			Month.OCTOBER,
			Month.NOVEMBER,
			Month.DECEMBER
		];
	}

}