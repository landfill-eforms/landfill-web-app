import { InstrumentType } from './instrument-type.class';
import { InstrumentStatus } from '../../enums/instrument/instrument-status.enum';
import { AbstractEntity } from '../abstract-entity.class';
import { Site } from '../../enums/location/site.enum';
import { User } from '../user/user.class';

/**
 * This class was automatically generated from Instrument.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class Instrument extends AbstractEntity {
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