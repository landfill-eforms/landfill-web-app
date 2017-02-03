/**
 * This enum was automatically generated from Site.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class Site {

	static readonly BISHOPS:Site = new Site(0, "Bishops", "BC", "", true);
	static readonly BRANFORD:Site = new Site(1, "Branford", "BR", "", false);
	static readonly CLARTS:Site = new Site(2, "CLARTS", "CL", "", false);
	static readonly GAFFEY:Site = new Site(3, "Gaffey", "GA", "", true);
	static readonly GRIFFITH_PARK:Site = new Site(4, "Griffith Park", "GP", "", false);
	static readonly LCEC:Site = new Site(5, "LCEC", "LC", "", false);
	static readonly LOPEZ:Site = new Site(6, "Lopez", "LC", "", true);
	static readonly POLY_HIGH:Site = new Site(7, "PolyHigh", "PH", "", false);
	static readonly SHELDON:Site = new Site(8, "Sheldon", "SH", "", true);
	static readonly TOYON:Site = new Site(9, "Toyon", "TC", "", true);

	readonly ordinal:number;
	readonly name:string;
	readonly shortName:string;
	readonly type:string;
	readonly active:boolean;

	private constructor(ordinal:number, name:string, shortName:string, type:string, active:boolean) {
		this.ordinal = ordinal;
		this.name = name;
		this.shortName = shortName;
		this.type = type;
		this.active = active;
	}

	static readonly values:Site[] = [
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