/**
 * This enum was automatically generated from Site.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class Site {

	static readonly BISHOPS:Site = new Site(0, "BISHOPS", "BC", "BC", "", true);
	static readonly BRANFORD:Site = new Site(1, "BRANFORD", "BR", "BR", "", false);
	static readonly CLARTS:Site = new Site(2, "CLARTS", "CL", "CL", "", false);
	static readonly GAFFEY:Site = new Site(3, "GAFFEY", "GA", "GA", "", true);
	static readonly GRIFFITH_PARK:Site = new Site(4, "GRIFFITH_PARK", "GP", "GP", "", false);
	static readonly LCEC:Site = new Site(5, "LCEC", "LC", "LC", "", false);
	static readonly LOPEZ:Site = new Site(6, "LOPEZ", "LC", "LC", "", true);
	static readonly POLY_HIGH:Site = new Site(7, "POLY_HIGH", "PH", "PH", "", false);
	static readonly SHELDON:Site = new Site(8, "SHELDON", "SH", "SH", "", true);
	static readonly TOYON:Site = new Site(9, "TOYON", "TC", "TC", "", true);

	readonly ordinal:number;
	readonly constantName:string;
	readonly name:string;
	readonly shortName:string;
	readonly type:string;
	readonly active:boolean;

	private constructor(ordinal:number, constantName:string, name:string, shortName:string, type:string, active:boolean) {
		this.ordinal = ordinal;
		this.constantName = constantName;
		this.name = name;
		this.shortName = shortName;
		this.type = type;
		this.active = active;
	}

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