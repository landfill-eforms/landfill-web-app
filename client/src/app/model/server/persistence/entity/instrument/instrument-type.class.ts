import { AbstractEntity } from '../abstract-entity.class';
import { Instrument } from './instrument.class';
import { User } from '../user/user.class';

/**
 * This class was automatically generated from InstrumentType.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class InstrumentType extends AbstractEntity {
	type:string;
	manufacturer:string;
	description:string;
	instantaneous:boolean;
	probe:boolean;
	methanePercent:boolean;
	methanePpm:boolean;
	hydrogenSulfidePpm:boolean;
	oxygenPercent:boolean;
	carbonDioxidePercent:boolean;
	nitrogenPercent:boolean;
	pressure:boolean;
	instruments:Instrument[];
	createdBy:User;
	createdDate:number;
	modifiedBy:User;
	modifiedDate:number;
}