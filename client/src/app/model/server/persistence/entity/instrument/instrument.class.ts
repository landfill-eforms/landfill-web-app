import { Site } from '../../enums/location/site.enum';
import { InstrumentType } from './instrument-type.class';
import { InstrumentStatus } from '../../enums/instrument/instrument-status.enum';

/**
 * This class was automatically generated from Instrument.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class Instrument {
	id:number;
	serialNumber:string;
	instrumentType:InstrumentType;
	instrumentStatus:InstrumentStatus;
	serviceDueDate:number;
	lastServiceDate:number;
	purchaseDate:number;
	site:Site;
	inventoryNumber:string;
	description:string;
}