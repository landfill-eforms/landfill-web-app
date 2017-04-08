import { IseRepairData } from './ise-repair-data.class';
import { IseNumber } from './ise-number.class';
import { User } from '../user/user.class';

/**
 * This class was automatically generated from IseData.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class IseData {
	id:number;
	iseNumber:IseNumber;
	inspector:User;
	methaneLevel:number;
	dateTime:number;
	description:string;
	iseRepairData:IseRepairData[];
}