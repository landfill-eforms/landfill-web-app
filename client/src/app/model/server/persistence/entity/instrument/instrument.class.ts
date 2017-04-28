import { InstrumentStatus } from '../../enums/instrument/instrument-status.enum';
import { User } from '../user/user.class';
import { InstrumentType } from './instrument-type.class';
import { Site } from '../../enums/location/site.enum';

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
	createdBy:User;
	createdDate:number;
	modifiedBy:User;
	modifiedDate:number;
}