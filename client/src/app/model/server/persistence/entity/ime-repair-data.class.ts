import { IMENumber } from './ime-number.class';
import { User } from './user.class';

/**
 * This class was automatically generated from IMERepairData.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class IMERepairData {
	id:number;
	imeNumber:IMENumber;
	user:User;
	dateTime:number;
	water:boolean;
	soil:boolean;
	description:string;
	crew:string;
}