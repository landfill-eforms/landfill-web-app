/**
 * This enum was automatically generated from Site.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class Site {

	static readonly BISHOPS: Site = {
		ordinal: 0,
		constantName: "BISHOPS",
		name: "Bishops",
		shortName: "BC",
		type: "",
		active: true
	};

	static readonly BRANFORD: Site = {
		ordinal: 1,
		constantName: "BRANFORD",
		name: "Branford",
		shortName: "BR",
		type: "",
		active: false
	};

	static readonly CLARTS: Site = {
		ordinal: 2,
		constantName: "CLARTS",
		name: "CLARTS",
		shortName: "CL",
		type: "",
		active: false
	};

	static readonly GAFFEY: Site = {
		ordinal: 3,
		constantName: "GAFFEY",
		name: "Gaffey",
		shortName: "GA",
		type: "",
		active: true
	};

	static readonly GRIFFITH_PARK: Site = {
		ordinal: 4,
		constantName: "GRIFFITH_PARK",
		name: "Griffith Park",
		shortName: "GP",
		type: "",
		active: false
	};

	static readonly LCEC: Site = {
		ordinal: 5,
		constantName: "LCEC",
		name: "LCEC",
		shortName: "LC",
		type: "",
		active: false
	};

	static readonly LOPEZ: Site = {
		ordinal: 6,
		constantName: "LOPEZ",
		name: "Lopez",
		shortName: "LC",
		type: "",
		active: true
	};

	static readonly POLY_HIGH: Site = {
		ordinal: 7,
		constantName: "POLY_HIGH",
		name: "PolyHigh",
		shortName: "PH",
		type: "",
		active: false
	};

	static readonly SHELDON: Site = {
		ordinal: 8,
		constantName: "SHELDON",
		name: "Sheldon",
		shortName: "SH",
		type: "",
		active: true
	};

	static readonly TOYON: Site = {
		ordinal: 9,
		constantName: "TOYON",
		name: "Toyon",
		shortName: "TC",
		type: "",
		active: true
	};

	readonly ordinal:number;
	readonly constantName:string;
	readonly name: string;
	readonly shortName: string;
	readonly type: string;
	readonly active: boolean;

	static values():Site[] {
		return [
			Site.BISHOPS,
			Site.BRANFORD,
			Site.CLARTS,
			Site.GAFFEY,
			Site.GRIFFITH_PARK,
			Site.LCEC,
			Site.LOPEZ,
			Site.POLY_HIGH,
			Site.SHELDON,
			Site.TOYON
		];
	}

}