import { AbstractEntity } from '../abstract-entity.class';
import { InstrumentType } from './instrument-type.class';
import { User } from '../user/user.class';
import { InstrumentStatus } from '../../enums/instrument/instrument-status.enum';

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
	inventoryNumber:string;
	description:string;
	createdBy:User;
	createdDate:number;
	modifiedBy:User;
	modifiedDate:number;
}