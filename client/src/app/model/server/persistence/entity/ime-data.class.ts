import { User } from './user.class';
import { IMENumber } from './ime-number.class';

/**
 * This class was automatically generated from IMEData.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class IMEData {
	id:number;
	imeNumber:IMENumber;
	user:User;
	methaneLevel:number;
	dateTime:number;
	description:string;
}