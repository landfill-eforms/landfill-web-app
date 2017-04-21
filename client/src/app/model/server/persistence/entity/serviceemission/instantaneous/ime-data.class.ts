import { ImeRepairData } from './ime-repair-data.class';
import { User } from '../../user/user.class';
import { ImeNumber } from './ime-number.class';

/**
 * This class was automatically generated from ImeData.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class ImeData {
	id:number;
	imeNumber:ImeNumber;
	inspector:User;
	methaneLevel:number;
	dateTime:number;
	description:string;
	imeRepairData:ImeRepairData[];
}